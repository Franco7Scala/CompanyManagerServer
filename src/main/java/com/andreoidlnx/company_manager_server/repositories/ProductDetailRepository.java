package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.keys.ProductDetailPK;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailPK> {

    //void create(ProductDetail productDetail);

    //void edit(ProductDetail productDetail);

    //void remove(ProductDetail productDetail);

    //List<ProductDetail> findByProductDetailPK(ProductDetailPK productDetailPK);

    List<ProductDetail> findAll();
    
    @Query("SELECT p FROM ProductDetail p WHERE p.productDetailPK.idProduct = :idProduct")
    ProductDetail findByIdProduct(@Param("idProduct") int idProduct);

    @Query("SELECT p FROM ProductDetail p WHERE p.productDetailPK.year = :year")
    List<ProductDetail> findByYear(@Param("year") int year);

    List<ProductDetail> findByQuantity(Integer quantity);

    //List<ProductDetail> findByProductDetailBetween(int[] range);

    long count();

    @Query("SELECT p FROM ProductDetail p WHERE LOWER(p.product.name) LIKE LOWER(:name) ORDER BY p.productDetailPK.year DESC")
    List<ProductDetail> findByProductName(@Param("name") String name);

    @Query("SELECT p FROM ProductDetail p ORDER BY p.product.name, p.productDetailPK.year DESC")
    List<ProductDetail> findAllOrdered();

    @Query("SELECT p FROM ProductDetail p WHERE p.quantity > 0 AND LOWER(p.product.name) LIKE LOWER(:name) ORDER BY p.productDetailPK.year DESC")
    List<ProductDetail> findByProductNameAvailable(@Param("name") String name);
    
}
