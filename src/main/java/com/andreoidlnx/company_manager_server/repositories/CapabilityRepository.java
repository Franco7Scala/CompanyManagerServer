package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andreoidlnx.company_manager_server.entities.Capability;

public interface CapabilityRepository extends JpaRepository<Capability, String> {

    Optional<Capability> findById(String id);

    List<Capability> findAll();

    List<Capability> findByName(String name);

    List<Capability> findByValue(String value);

    List<Capability> findByCapabilityBetween(int[] range);

    long count();
    
}
