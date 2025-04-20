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
    private Integer stock;
    private String size;  // 👈 talle (ej: S, M, L)
    private String color; // 👈 color (ej: rojo, negro)
    private Double netPrice;      // 👈 precio de costo
    private Double listPrice;     // 👈 precio al público
    private Double transferPrice; // 👈 15% descuento
    private Double cashPrice;     // 👈 20% descuento
}

