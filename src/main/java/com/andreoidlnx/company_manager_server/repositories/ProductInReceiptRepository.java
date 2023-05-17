package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.ProductInReceipt;
import com.andreoidlnx.company_manager_server.entities.keys.ProductInReceiptPK;

public interface ProductInReceiptRepository extends JpaRepository<ProductInReceipt, ProductInReceiptPK> {
    
    Optional<ProductInReceipt> findById(ProductInReceiptPK id);

    @Query("SELECT p FROM ProductInReceipt p WHERE p.productInReceiptPK.receiptId = :receiptId")
    ProductInReceipt findByReceiptId(@Param("receiptId") int receiptId);

    @Query("SELECT p FROM ProductInReceipt p WHERE p.productInReceiptPK.productId = :productId")
    ProductInReceipt findByProductId(@Param("productId") int productId);

    @Query("SELECT p FROM ProductInReceipt p WHERE p.productInReceiptPK.productYear = :productYear")
    List<ProductInReceipt> findByProductYear(@Param("productYear") int productYear);

    List<ProductInReceipt> findByQuantity(int quantity);

    List<ProductInReceipt> findByPrice(float price);

    List<ProductInReceipt> findAll();

    long count();
    
}
