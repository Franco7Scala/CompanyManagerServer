package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.WorkingDay;
import com.andreoidlnx.company_manager_server.entities.WorkingDayPK;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, WorkingDayPK> {
    
}
