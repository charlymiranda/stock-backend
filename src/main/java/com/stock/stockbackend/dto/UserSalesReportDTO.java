package com.stock.stockbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSalesReportDTO {
    private String email;
    private Long totalSalesCount;
    private Double totalAmount;
}
