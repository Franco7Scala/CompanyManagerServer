package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.andreoidlnx.company_manager_server.entities.ProductTransition;
import java.util.Date;


public interface ProductTransitionRepository extends JpaRepository<ProductTransition, Integer> {

    //void create(ProductTransition productTransition);

    //void edit(ProductTransition productTransition);

    //void remove(ProductTransition productTransition);

    //ProductTransition find(Object id);

    List<ProductTransition> findAll();

    @Query("SELECT p FROM ProductTransition p ORDER BY p.transitionDate DESC")
    List<ProductTransition> findAllOrdered();

    Optional<ProductTransition> findById(Integer id);

    List<ProductTransition> findByTransitionDate(Date transitionDate);

    List<ProductTransition> findByDescription(String description);

    List<ProductTransition> findByQuantity(Integer quantity);

    //List<ProductTransition> findByProductTransitionBetween(int[] range);

    long count();
    
}
