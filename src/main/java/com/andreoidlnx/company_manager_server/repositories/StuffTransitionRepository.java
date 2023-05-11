package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andreoidlnx.company_manager_server.entities.StuffTransition;

public interface StuffTransitionRepository extends JpaRepository<StuffTransition, Integer>{

    List<StuffTransition> findAll();

    Optional<StuffTransition> findById(Integer id);

    List<StuffTransition> findByQuantity(Float quantity);

    List<StuffTransition> findByType(String type);

    List<StuffTransition> findByStuffTransitionBetween(int[] range);

    long count();
    
}
