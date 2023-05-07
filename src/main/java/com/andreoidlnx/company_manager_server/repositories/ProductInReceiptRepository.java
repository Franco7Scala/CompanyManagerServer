package com.andreoidlnx.company_manager_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.ProductInReceipt;
import com.andreoidlnx.company_manager_server.entities.ProductInReceiptPK;

@Repository
public interface ProductInReceiptRepository extends JpaRepository<ProductInReceipt, ProductInReceiptPK> {
    
}
