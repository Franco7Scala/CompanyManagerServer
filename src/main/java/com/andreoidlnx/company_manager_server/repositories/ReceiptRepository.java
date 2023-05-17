package com.andreoidlnx.company_manager_server.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.andreoidlnx.company_manager_server.entities.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

    @Query("SELECT MAX(r.dailyNumber) FROM Receipt r WHERE r.receiptDate >= :startTime AND r.receiptDate < :endTime")
    Integer findMaxTodayId(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    
    @Query("SELECT r FROM Receipt r ORDER BY r.receiptDate DESC")
    List<Receipt> findAllOrderedByDate();

    Optional<Receipt> findById(int id);

    List<Receipt> findByCashRegisterNumber(int cashRegisterNumber);

    List<Receipt> findByCashRegisterFresher(String cashRegisterFresher);

    List<Receipt> findByReceiptDate(Date receiptDate);

    List<Receipt> findByDailyNumber(int dailyNumber);

    List<Receipt> findByTermsOfPayment(String termsOfPayment);

    List<Receipt> findAll();

    long count();

    @Query(value = "SELECT r.receipt_date FROM Receipt r WHERE r.receipt_date = :date", nativeQuery = true)
    int getLatestTodayNumber(@Param("date") Date date);
    
}
