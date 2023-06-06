package com.andreoidlnx.company_manager_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.State;
import com.andreoidlnx.company_manager_server.services.StateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<?> addState(@Valid @RequestBody State state){
        try{
            stateService.addState(state);
            return new ResponseEntity<>("STATE_ADDED", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("STATE_NOT_ADDED", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/setPreferredState")
    public ResponseEntity<?> setPreferredState(@Valid @RequestBody State state){
        try{
            stateService.setPreferredState(state);
            return new ResponseEntity<>("SET_PREFERRED_STATE", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("NOT_SET_PREFERRED_STATE", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/setDownloadableState")
    public void setDownloadableState(@Valid @RequestBody State state, boolean value){
        stateService.setDownloadableState(state, value);
    }

    @PutMapping("/deleteState")
    public void deleteState(@Valid @RequestBody State state){
        stateService.deleteState(state);
    }

    @GetMapping("/getPreferredState")
    public List<State> getPreferredState() {
        return stateService.getPreferredState();
    }

    @GetMapping("/getQuantityForState")
    public int getQuantityForState(@Valid @RequestBody ProductDetail productDetail, @Valid @RequestBody State  state){
        return stateService.getQuantityForState(productDetail, state);
    }

    @GetMapping("/getAllVisibleState")
    public List<State> getAllVisibleState(){
        return stateService.getAllVisibleStates();
    }

    @GetMapping("/getAllDownloadables")
    public List<State> getAllDownloadables(){
        return stateService.getAllDownloadables();
    }

    @GetMapping("/getAllUndownloadables")
    public List<State> getAllUndownloadables(){
        return stateService.getAllUndownloadables();
    }

}
