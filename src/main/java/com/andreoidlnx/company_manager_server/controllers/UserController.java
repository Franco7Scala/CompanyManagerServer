package com.andreoidlnx.company_manager_server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andreoidlnx.company_manager_server.entities.Capability;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.services.UserService;
import com.andreoidlnx.company_manager_server.supports.exceptions.UserNotExistException;
import com.andreoidlnx.company_manager_server.supports.exceptions.UsernameAlreadyExistException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody User user){
        try {
            userService.addUser(user);
            return new ResponseEntity<>("USER_CREATED", HttpStatus.OK);
        } catch (UsernameAlreadyExistException e){
            return new ResponseEntity<>("ERROR_USERNAME_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@Valid @RequestBody User user){
        try{
            userService.editUser(user);
            return new ResponseEntity<>("USER_EDITED", HttpStatus.OK);
        } catch(UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        try{
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }
        catch (UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllVisibleUsers();
    }

    @GetMapping("/getAllUsersExceptAdministrator")
    public List<User> getAllUsersExceptAdministrator() {
        List<User> result = userService.getAllVisibleUsers();
        User toRemove = null;
        for ( User user : result ) {
            if ( user.getId() == 1 ) {
                toRemove = user;
                break;
            }
        }
        result.remove(toRemove);
        return result;
    }

    @GetMapping("/getAllCapabilities")
    public List<Capability> getAllCapabilities() {
        return userService.getAllCapabilities();
    }

    @PutMapping("/addCapabilityToUser")
    public ResponseEntity<?> addCapabilityToUser(@Valid @RequestBody User user, @Valid @RequestBody Capability capability) {
        try{
            userService.addCapability(user, capability);
            return new ResponseEntity<>("USER_CAPABILITY_ADDED", HttpStatus.OK);
        } catch (UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeCapabilityToUser")
    public ResponseEntity<?> removeCapabilityToUser(@Valid @RequestBody User user, @Valid @RequestBody Capability capability) {
        try{
            userService.removeCapability(user, capability);
            return new ResponseEntity<>("USER_CAPABILITY_REMOVED", HttpStatus.OK);
        } catch (UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeUser")
    public ResponseEntity<?> removeUser(@Valid @RequestBody User user) {
        try{
            userService.removeUser(user);
            return new ResponseEntity<>("USER_REMOVED", HttpStatus.OK);
        } catch (UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUsersWithCapability")
    public List<User> getUsersWithCapability(@Valid @RequestBody Capability capability){
        return userService.getUsersWithCapability(capability);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody User user){
        try{
            userService.deleteUser(user);
            return new ResponseEntity<>("USER_DELETED", HttpStatus.OK);
        } catch(UserNotExistException e){
            return new ResponseEntity<>("ERROR_USER_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

}
