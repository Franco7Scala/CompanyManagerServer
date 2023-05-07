package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.ProductDetailPK;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailPK> {
    
}
