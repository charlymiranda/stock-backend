package com.stock.stockbackend.repository;

import com.stock.stockbackend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :start AND :end")
    List<Sale> findByDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = "SELECT CAST(date AS DATE) as saleDate, COUNT(*) as totalSalesCount, SUM(total) as totalAmount " +
        "FROM sales " +
        "WHERE date BETWEEN :start AND :end " +
        "GROUP BY CAST(date AS DATE)", nativeQuery = true)
    List<Object[]> getDailySalesReport(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
