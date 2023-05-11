package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andreoidlnx.company_manager_server.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

    List<Product> findAll();
 
    List<Product> findByCode(String code);

    List<Product> findByName(String name);

    Product findByBarCodeSingle(String barCodeSingle);

    Product findByBarCodePackage(String barCodePackage);

    List<Product> findByDescription(String description);

    List<Product> findByProductBetween(int[] range);

    long count();
    
}
