package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, String>{
    
}
