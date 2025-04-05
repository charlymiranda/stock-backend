package com.stock.stockbackend.mapper;

import com.stock.stockbackend.dto.SaleResponseDTO;
import com.stock.stockbackend.model.Sale;

import java.util.stream.Collectors;

public class SaleMapper {

    public static SaleResponseDTO toDTO(Sale sale) {
        return new SaleResponseDTO(
            sale.getId(),
            sale.getDate(),
            sale.getCustomer(),
            sale.getPaymentMethod(),
            sale.getTotal(),
            sale.getUser() != null ? sale.getUser().getEmail() : null,
            sale.getItems().stream().map(item ->
                new SaleResponseDTO.SaleItemDTO(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getPrice()
                )
            ).collect(Collectors.toList())
        );
    }
}
