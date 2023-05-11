package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.Stuff;

public interface StuffRepository extends JpaRepository<Stuff, Integer>{

    List<Stuff> findAll();

    List<Stuff> findByStuffBetween(int[] range);

    long count();

    List<Stuff> findByVisibleTrue(); //findAllVisible

    List<Stuff> findByName(String name);

    @Query("SELECT s FROM Stuff s WHERE LOWER(s.name) LIKE LOWER(:name) AND s.visible = 1 AND s.quantity > 0 ORDER BY s.name")
    List<Stuff> findFullByName(@Param("name") String name);

    //findByExactName

    Optional<Stuff> findById(Integer id);

    List<Stuff> findByQuantity(float quantity);

    List<Stuff> findByUnitMeasure(String unitMeasure);

    List<Stuff> findByDescription(String description);

    List<Stuff> findByBarCodeSingle(String barCodeSingle);

    List<Stuff> findByBarCodePackage(String barCodePackage);

    //boolean existName(String name);

    @Query("SELECT s FROM Stuff s WHERE s.name = :name")
    Stuff findSingleByName(@Param("name") String name);
    
}
