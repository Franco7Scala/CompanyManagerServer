package com.andreoidlnx.company_manager_server.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.entities.WorkingDay;
import com.andreoidlnx.company_manager_server.entities.keys.WorkingDayPK;

public interface WorkingDayRepository extends JpaRepository<WorkingDay, WorkingDayPK> {
    
    Optional<WorkingDay> findById(WorkingDayPK id);

    List<WorkingDay> findAll();

    @Query("SELECT w FROM WorkingDay w WHERE w.workingDayPK.idUser = :idUser AND w.visible = 1 ORDER BY w.workingDayPK.workDate DESC")
    List<WorkingDay> findByIdUser(@Param("idUser") int idUser);

    @Query("SELECT w FROM WorkingDay w WHERE w.workingDayPK.workDate = :workDate")
    List<WorkingDay> findByWorkDate(@Param("workDate") Date workDate);

    List<WorkingDay> findByInTime(Date inTime);

    List<WorkingDay> findByOutTime(Date outTime);

    List<WorkingDay> findByDescription(String description);

    List<WorkingDay> findByWorkingDayBetween(int[] range);

    long count();

    @Query("SELECT w FROM WorkingDay w WHERE w.user.id = :idUser")
    List<WorkingDay> getWorkingDaysForUser(int idUser);

    @Query("SELECT COUNT(w) FROM WorkingDay w WHERE w.visible = 1 AND w.workingDayPK.workDate >= :start AND w.workingDayPK.workDate < :end AND w.workingDayPK.idUser = :idUser")
    int getWorkingDaysInPeriod(Date start, Date end, Integer idUser);

    @Query("SELECT w.user FROM WorkingDay w WHERE w.visible = 1 AND w.workingDayPK.workDate >= :start AND w.workingDayPK.workDate < :end")
    List<User> getUsersInPeriod(Date start, Date end);
    
}
