package com.andreoidlnx.company_manager_server.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

    //void create(Receipt receipt);

    //void edit(Receipt receipt);

    //void remove(Receipt receipt);

    //Receipt find(Object id);

    @Query("SELECT MAX(r.dailyNumber) FROM Receipt r WHERE r.receiptDate >= :startTime AND r.receiptDate < :endTime")
    Integer findMaxTodayId(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    
    @Query("SELECT r FROM Receipt r ORDER BY r.receiptDate DESC")
    List<Receipt> findAllOrderedByDate();

    Optional<Receipt> findById(Integer id);

    List<Receipt> findByCashRegisterNumber(Integer cashRegisterNumber);

    List<Receipt> findByCashRegisterFresher(String cashRegisterFresher);

    List<Receipt> findByReceiptDate(Date receiptDate);

    List<Receipt> findByDailyNumber(Integer dailyNumber);

    List<Receipt> findByTermsOfPayment(String termsOfPayment);

    List<Receipt> findAll();

    //List<Receipt> findByReceiptBetween(int[] range);

    long count();

    //int getLatestTodayNumber(Date date);
    
}
