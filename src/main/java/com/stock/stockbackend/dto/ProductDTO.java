package com.stock.stockbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String code;
    private String name;
    private String category;
    private String provider;
    private Double price;
    private Integer stock;
}

