package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.ProductState;

@Repository
public interface ProductStateRepository extends JpaRepository<ProductState, Integer> {
    
}
