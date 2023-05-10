package com.andreoidlnx.company_manager_server.services.useres;

import java.util.ArrayList;
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
import com.andreoidlnx.company_manager_server.services.supports.exceptions.UsernameAlreadyExistException;

@Service
public class AccountingManagementService {

    @Autowired
    private UserRepository userRepositoryCapabilityRepository;
    @Autowired
    private CapabilityRepository capabilityRepositoryCapabilityRepository;
    @Autowired
    private SessionContext context;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) throws UsernameAlreadyExistException {
        if ( userRepositoryCapabilityRepository.existUsername(user.getUsername()) ) {
            throw new UsernameAlreadyExistException();
        }
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(Constants.USER_CONFIRMED));
        user.setRoleList(roles);
        userRepositoryCapabilityRepository.create(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editUser(User user) {
        userRepositoryCapabilityRepository.edit(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getAllVisibleUsers() {
        return userRepositoryCapabilityRepository.findAllVisible();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCapability(User user, Capability capability) {
        user.getCapabilityList().add(capability);
        userRepositoryCapabilityRepository.edit(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCapability(User user, Capability capability) {
        user.getCapabilityList().remove(capability);
        userRepositoryCapabilityRepository.edit(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUser(User user) {
        user.setVisible(false);
        user.setRoleList(new LinkedList<Role>());
        userRepositoryCapabilityRepository.edit(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Capability> getAllCapabilities() {
        return capabilityRepositoryCapabilityRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User getCurrentUser() {
        Principal callerPrincipal = context.getCallerPrincipal();
        return userRepositoryCapabilityRepository.findByUsername(callerPrincipal.getName());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getUsersWithCapability(Capability capability) {
        List<User> result = userRepositoryCapabilityRepository.findAll();
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
