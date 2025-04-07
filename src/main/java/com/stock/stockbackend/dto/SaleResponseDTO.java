package com.stock.stockbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SaleResponseDTO {
    private Long id;
    private LocalDateTime date;
    private String customer;
    private String paymentMethod;
    private Double total;
    private String sellerEmail;
    private List<SaleItemDTO> items;

    @Data
    @AllArgsConstructor
    public static class SaleItemDTO {
        private String productName;
        private Integer quantity;
        private Double price;
    }
}
