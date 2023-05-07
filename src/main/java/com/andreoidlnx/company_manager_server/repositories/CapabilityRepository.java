package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.Capability;

@Repository
public interface CapabilityRepository extends JpaRepository<Capability, String> {
    
}
