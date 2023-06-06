package com.andreoidlnx.company_manager_server.controllers;

import java.util.Date;
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
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.entities.WorkingDay;
import com.andreoidlnx.company_manager_server.services.WorkShiftService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/workShifts")
public class WorkShiftController {

    @Autowired
    private WorkShiftService workShiftService;

    @PostMapping
    public ResponseEntity<?> addWorkingDay(@Valid @RequestBody WorkingDay workingDay){
        try {
            workShiftService.addWorkingDay(workingDay);
            return new ResponseEntity<>("WORKING_DAY_CREATED", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("WORKING_DAY_NOT_CREATED", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeWorkingDay")
    public void removeWorkingDay(@Valid @RequestBody WorkingDay workingDay){
        workShiftService.removeWorkingDay(workingDay);
    }

    @GetMapping("/getWorkingDaysForUser")
    public List<WorkingDay> getWorkingDaysForUser(@Valid @RequestBody User user){
       return workShiftService.getWorkingDaysForUser(user);
    }

    @GetMapping("/getQuantityWorkingDaysThisMonth")
    public int getQuantityWorkingDaysThisMonth(@Valid @RequestBody User user){
        return workShiftService.getQuantityWorkingDaysThisMonth(user);
    }

    @GetMapping("/getQuantityWorkingDaysPreviousMonth")
    public int getQuantityWorkingDaysPreviousMonth(@Valid @RequestBody User user){
        return workShiftService.getQuantityWorkingDaysPreviousMonth(user);
    }

    @GetMapping("/getUsersInDay/{date}")
    public List<User> getUsersInDay(@PathVariable Date date){
        return workShiftService.getUsersInDay(date);
    }

    /* 
    @GetMapping("/")
    public List<WorkingDay> getWorkingDaysCurrentUser(@Valid @RequestBody User user) {
        return WorkShiftService.getWorkingDaysForUser(user);
    }
    */

}
