package com.stock.stockbackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// src/main/java/com/tuempresa/stock/dto/ProductDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String category;
    private String provider;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;
}
