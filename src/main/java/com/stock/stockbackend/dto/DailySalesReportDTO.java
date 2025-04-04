package com.stock.stockbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailySalesReportDTO {
    private LocalDate date;
    private Long totalSalesCount;
    private Double totalAmount;
}
