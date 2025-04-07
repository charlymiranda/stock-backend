package com.stock.stockbackend.mapper;

import com.stock.stockbackend.dto.ProductDTO;
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

    public static void updateEntity(Product product, ProductDTO dto) {
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setProvider(dto.getProvider());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
    }

}
