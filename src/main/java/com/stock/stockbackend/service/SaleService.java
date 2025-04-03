package com.stock.stockbackend.service;

import com.stock.stockbackend.dto.SaleRequestDTO;
import com.stock.stockbackend.model.Product;
import com.stock.stockbackend.model.Sale;
import com.stock.stockbackend.model.SaleItem;
import com.stock.stockbackend.repository.ProductRepository;
import com.stock.stockbackend.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Sale createSale(SaleRequestDTO request) {
        List<SaleItem> saleItems = new ArrayList<>();
        double total = 0.0;

        for (SaleRequestDTO.ItemDTO itemDTO : request.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + itemDTO.getProductId()));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }

            // Descontar stock
            product.setStock(product.getStock() - itemDTO.getQuantity());

            double itemTotal = product.getPrice() * itemDTO.getQuantity();
            total += itemTotal;

            SaleItem saleItem = SaleItem.builder()
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .price(product.getPrice())
                    .build();

            saleItems.add(saleItem);
        }

        Sale sale = Sale.builder()
                .customer(request.getCustomer())
                .paymentMethod(request.getPaymentMethod())
                .date(LocalDateTime.now())
                .total(total)
                .build();

        // Asociar items a la venta
        for (SaleItem item : saleItems) {
            item.setSale(sale);
        }

        sale.setItems(saleItems);

        return saleRepository.save(sale);
    }
}
