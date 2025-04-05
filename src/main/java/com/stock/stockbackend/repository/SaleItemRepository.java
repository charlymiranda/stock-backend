package com.stock.stockbackend.repository;

import com.stock.stockbackend.dto.ProductSalesReportDTO;
import com.stock.stockbackend.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Query("SELECT new com.stock.stockbackend.dto.ProductSalesReportDTO(" +
        "si.product.id, si.product.name, SUM(si.quantity), SUM(si.price * si.quantity)) " +
        "FROM SaleItem si " +
        "WHERE si.sale.date BETWEEN :start AND :end " +
        "GROUP BY si.product.id, si.product.name")
    List<ProductSalesReportDTO> getProductSalesReport(
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end);

}
