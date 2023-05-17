package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.ProductState;

public interface ProductStateRepository extends JpaRepository<ProductState, Integer> {
    
    Optional<ProductState> findById(int id);

    @Query("SELECT p FROM ProductState p WHERE p.productDetail.productDetailPK.idProduct = :idProduct AND p.productDetail.productDetailPK.year = :yearProduct AND p.state.name = :state")
    List<ProductState> findByIdAndYear(@Param("idProduct") int idProduct, @Param("yearProduct") int yearProduct, @Param("state") String state);

    List<ProductState> findAll();

    long count();

    @Query("SELECT p.quantity FROM ProductState p WHERE p.productDetail.productDetailPK.idProduct = :idProduct AND p.productDetail.productDetailPK.year = :yearProduct AND p.state.name = :state")
    int getQuantityForState(@Param("idProduct") int idProduct, @Param("yearProduct") int yearProduct, @Param("state") String state);
    
    @Query(
        value = "SELECT p FROM ProducState p WHERE p.productDetail.productDetailPK.idProduct = :productId AND p.productDetail.productDetailPK.year = :year AND p.state.name = :state",
        nativeQuery = true
    )
    ProductState findByProductIdAndYearAndState(@Param("productId") int productId, @Param("year") int year, @Param("state") String state);
    
}
