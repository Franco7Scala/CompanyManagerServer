package com.andreoidlnx.company_manager_server.services;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.andreoidlnx.company_manager_server.entities.Capability;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.repositories.CapabilityRepository;
import com.andreoidlnx.company_manager_server.repositories.UserRepository;
import com.andreoidlnx.company_manager_server.supports.exceptions.UsernameAlreadyExistException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) throws UsernameAlreadyExistException {
        if(userRepository.existsByUsername(user.getUsername()))
            throw new UsernameAlreadyExistException();
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getAllVisibleUsers() {
        return userRepository.findByVisibleTrue();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCapability(User user, Capability capability) {
        user.getCapabilityList().add(capability);
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCapability(User user, Capability capability) {
        user.getCapabilityList().remove(capability);
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUser(User user) {
        user.setVisible(false);
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Capability> getAllCapabilities() {
        return capabilityRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getUsersWithCapability(Capability capability) {
        List<User> result = userRepository.findAll();
        List<User> toDelete = new LinkedList<>();
        for ( User user : result ) {
            if ( !user.getCapabilityList().contains(capability) ) {
                toDelete.add(user);
            }
        }
        for ( User user : toDelete ) {
            result.remove(user);
        }
        return result;
    }
    
}
