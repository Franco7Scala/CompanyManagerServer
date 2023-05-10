package com.andreoidlnx.company_manager_server.services.workshifts;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.entities.WorkingDay;
import com.andreoidlnx.company_manager_server.repositories.WorkingDayRepository;

@Service
public class WorkShiftsManagmentService {

    @Autowired
    private WorkingDayRepository wrkingDayRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addWorkingDay(WorkingDay day) throws Exception {
        if ( wrkingDayRepository.find(day.getWorkingDayPK()) == null ) {
            wrkingDayRepository.create(day);
        }
        else {
            day.setVisible(true);
            wrkingDayRepository.edit(day);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeWorkingDay(WorkingDay day) {
        day.setVisible(false);
        wrkingDayRepository.edit(day);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<WorkingDay> getWorkingDaysForUser(User user) {
        return wrkingDayRepository.getWorkingDaysForUser(user.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getQuantityWorkingDaysThisMonth(User user) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, -1);
        Date start = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, 1);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date end = calendar.getTime();
        try {
            return wrkingDayRepository.getWorkingDaysInPeriod(start, end, user.getId());
        } catch ( Exception e ) {
            return 0;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getQuantityWorkingDaysPreviousMonth(User user) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, -1);
        Date start = calendar.getTime();
        calendar.add(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -2);
        calendar.add(Calendar.SECOND, 1);
        Date end = calendar.getTime();
        try {
            return wrkingDayRepository.getWorkingDaysInPeriod(start, end, user.getId());
        } catch ( Exception e ) {
            return 0;
        }
    }

    public List<User> getUsersInDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, -1);
        Date start = calendar.getTime();
        calendar.add(Calendar.HOUR, 23);
        Date end = calendar.getTime();
        return wrkingDayRepository.getUsersInPeriod(start, end);
    }
    
}
