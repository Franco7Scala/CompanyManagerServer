package com.andreoidlnx.company_manager_server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andreoidlnx.company_manager_server.entities.Stuff;
import com.andreoidlnx.company_manager_server.entities.StuffTransition;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.services.StuffService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stuffs")
public class StuffController {

    @Autowired
    private StuffService stuffService;

    @PostMapping
    public ResponseEntity<?> addStuff(@Valid @RequestBody Stuff stuff, @Valid @RequestBody User user){
        try {
            stuffService.addStuff(stuff, user);
            return new ResponseEntity<>("STUFF_CREATED", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("STUFF_NOT_CREATED", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Stuff> getAllStuffs() {
        return stuffService.getAllStuffs();
    }

    @GetMapping("/getAllStuffTransitions")
    public List<StuffTransition> getAllStuffTransitions() {
        return stuffService.getAllStuffTransitions();
    }

    @GetMapping("/getStuffsByName/{name}")
    public List<Stuff> getStuffsByName(@PathVariable String name) {
        return stuffService.getStuffsByName(name);
    }

    @GetMapping("/getStuffsByBarCodeSingle/{barCode}")
    public List<Stuff> getStuffsByBarCodeSingle(@PathVariable String barCode) {
        return stuffService.getStuffsByBarCodeSingle(barCode);
    }

    @PutMapping("/deleteStuff")
    public void deleteStuff(@Valid @RequestBody Stuff stuff) {
        stuffService.deleteStuff(stuff);
    }

    @PutMapping("/editStuff")
    public void editStuff(@Valid @RequestBody Stuff stuff) {
        stuffService.editStuff(stuff);
    }

    @GetMapping("/getFullStuffsByName/{name}")
    public List<Stuff> getFullStuffsByName(@PathVariable String name) {
        return stuffService.getFullStuffsByName(name);
    }

    @PutMapping("/addStuffTransition")
    public void addStuffTransition(@Valid @RequestBody StuffTransition stuffTransition) {
        stuffService.addStuffTransition(stuffTransition);
    }

    //vedi dal prof --> ho visto, secondo me non devo aggiungere nulla

}
