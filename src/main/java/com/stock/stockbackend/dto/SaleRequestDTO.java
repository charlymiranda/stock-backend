package com.stock.stockbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequestDTO {
    private String paymentMethod;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private Long productId;
        private Integer quantity;
    }
}
