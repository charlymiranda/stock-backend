package com.stock.stockbackend.mapper;

import com.stock.stockbackend.dto.ProductDTO;
import com.stock.stockbackend.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductDTO dto) {
        return Product.builder()
            .id(dto.getId())
            .code(dto.getCode())
            .name(dto.getName())
            .size(dto.getSize())
            .color(dto.getColor())
            .stock(dto.getStock())
            .netPrice(dto.getNetPrice())
            .listPrice(dto.getListPrice())
            .transferPrice(dto.getTransferPrice())
            .cashPrice(dto.getCashPrice())
            .build();
    }

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
            .id(product.getId())
            .code(product.getCode())
            .name(product.getName())
            .size(product.getSize())
            .color(product.getColor())
            .stock(product.getStock())
            .netPrice(product.getNetPrice())
            .listPrice(product.getListPrice())
            .transferPrice(product.getTransferPrice())
            .cashPrice(product.getCashPrice())
            .build();
    }

    public static void updateEntity(Product product, ProductDTO dto) {
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setSize(dto.getSize());
        product.setColor(dto.getColor());
        product.setStock(dto.getStock());
        product.setNetPrice(dto.getNetPrice());
        product.setListPrice(dto.getListPrice());
        product.setTransferPrice(dto.getTransferPrice());
        product.setCashPrice(dto.getCashPrice());
    }
}
