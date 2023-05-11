package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.ProductState;

public interface ProductStateRepository extends JpaRepository<ProductState, Integer> {
    
    Optional<ProductState> findById(Integer id);

    @Query("SELECT p FROM ProductState p WHERE p.productDetail.productDetailPK.idProduct = :idProduct AND p.productDetail.productDetailPK.year = :yearProduct AND p.state.name = :state")
    List<ProductState> findByIdAndYear(@Param("idProduct") int idProduct, @Param("yearProduct") int yearProduct, @Param("state") String state);

    List<ProductState> findAll();

    List<ProductState> findbyProductStateBetween(int[] range);

    long count();

    @Query("SELECT p.quantity FROM ProductState p WHERE p.productDetail.productDetailPK.idProduct = :idProduct AND p.productDetail.productDetailPK.year = :yearProduct AND p.state.name = :state")
    int getQuantityForState(@Param("idProduct") int idProduct, @Param("yearProduct") int yearProduct, @Param("state") String state);
    
    ProductState findByProductIdAndYearAndState(int productId, int year, String state);
    
}
