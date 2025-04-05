package com.stock.stockbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSalesReportDTO {
    private Long productId;
    private String productName;
    private Long totalQuantity;
    private Double totalSales;
}
