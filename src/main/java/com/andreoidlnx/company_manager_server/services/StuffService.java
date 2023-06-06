package com.andreoidlnx.company_manager_server.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.andreoidlnx.company_manager_server.entities.Stuff;
import com.andreoidlnx.company_manager_server.entities.StuffTransition;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.repositories.StuffRepository;
import com.andreoidlnx.company_manager_server.repositories.StuffTransitionRepository;
import com.andreoidlnx.company_manager_server.supports.Constants;
import com.andreoidlnx.company_manager_server.supports.exceptions.ExistStuffNameException;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    @Autowired 
    private StuffTransitionRepository stuffTransitionRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addStuff(Stuff stuff, User user) throws ExistStuffNameException {
        Stuff verificationStuff = stuffRepository.findSingleByName(stuff.getName());
        if(verificationStuff != null) {
            if(!verificationStuff.isVisible()) {
                if(stuff.getBarCodeSingle() != null && stuff.getBarCodeSingle().equals("") ) {
                    stuff.setBarCodeSingle(null);
                }
                if(stuff.getBarCodePackage() != null && stuff.getBarCodePackage().equals("") ) {
                    stuff.setBarCodePackage(null);
                }
                stuff.setId(verificationStuff.getId());
                stuffRepository.save(stuff);
            }
            else {
                throw new ExistStuffNameException();
            }
        }
        else {
            if(stuff.getBarCodeSingle() != null && stuff.getBarCodeSingle().equals("")) {
                stuff.setBarCodeSingle(null);
            }
            if(stuff.getBarCodePackage() != null && stuff.getBarCodePackage().equals("")) {
                stuff.setBarCodePackage(null);
            }
            stuff.setVisible(true);
            stuffRepository.save(stuff);
        }
        StuffTransition transition = new StuffTransition();
        transition.setIdStuff(stuff);
        transition.setIdUser(user);
        transition.setQuantity(stuff.getQuantity());
        transition.setTransitionDate(new Date());
        transition.setType(Constants.TYPE_LOAD);
        stuffTransitionRepository.save(transition);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Stuff> getAllStuffs() {
        return stuffRepository.findByVisibleTrue();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<StuffTransition> getAllStuffTransitions() {
        return stuffTransitionRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Stuff> getStuffsByName(String name) {
        return stuffRepository.findByName(name);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Stuff> getStuffsByBarCodeSingle(String barCode) {
        return stuffRepository.findByBarCodeSingle(barCode);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStuff(Stuff stuff) {
        stuff.setVisible(false);
        if ( stuff.getBarCodeSingle() != null && stuff.getBarCodeSingle().equals("") ) {
            stuff.setBarCodeSingle(null);
        }
        if ( stuff.getBarCodePackage() != null && stuff.getBarCodePackage().equals("") ) {
            stuff.setBarCodePackage(null);
        }
        stuffRepository.save(stuff);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editStuff(Stuff stuff) {
        if ( stuff.getBarCodeSingle() != null && stuff.getBarCodeSingle().equals("") ) {
            stuff.setBarCodeSingle(null);
        }
        if ( stuff.getBarCodePackage() != null && stuff.getBarCodePackage().equals("") ) {
            stuff.setBarCodePackage(null);
        }
        stuffRepository.save(stuff);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Stuff> getFullStuffsByName(String name) {
        return stuffRepository.findFullByName(name);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void addStuffTransition(StuffTransition transition) {
        Stuff stuff = transition.getIdStuff();
        stuff.setQuantity(stuff.getQuantity() + transition.getQuantity());
        stuffRepository.save(stuff);
        stuffTransitionRepository.save(transition);
    }

}
