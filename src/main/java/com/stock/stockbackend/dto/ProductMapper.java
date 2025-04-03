package com.stock.stockbackend.dto;

import com.stock.stockbackend.model.Product;

public class ProductMapper {
    public static Product toEntity(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .category(dto.getCategory())
                .provider(dto.getProvider())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .provider(product.getProvider())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
