package com.stock.stockbackend.service;

import com.stock.stockbackend.dto.*;
import com.stock.stockbackend.model.Product;
import com.stock.stockbackend.model.Sale;
import com.stock.stockbackend.model.SaleItem;
import com.stock.stockbackend.model.User;
import com.stock.stockbackend.repository.ProductRepository;
import com.stock.stockbackend.repository.SaleItemRepository;
import com.stock.stockbackend.repository.SaleRepository;
import com.stock.stockbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;
    private final UserRepository userRepository;

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

            double selectedPrice = getPriceByPaymentMethod(product, request.getPaymentMethod());

            double itemTotal = selectedPrice * itemDTO.getQuantity();
            total += itemTotal;

            SaleItem saleItem = SaleItem.builder()
                .product(product)
                .quantity(itemDTO.getQuantity())
                .price(selectedPrice)
                .build();

            saleItems.add(saleItem);
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sale sale = Sale.builder()
            .paymentMethod(request.getPaymentMethod())
            .date(LocalDateTime.now())
            .total(total)
            .user(user)
            .build();

        for (SaleItem item : saleItems) {
            item.setSale(sale);
        }

        sale.setItems(saleItems);
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesBetween(LocalDateTime start, LocalDateTime end) {
        return saleRepository.findByDateBetween(start, end);
    }

    public List<ProductSalesReportDTO> getProductSalesReport(LocalDateTime start, LocalDateTime end) {
        return saleItemRepository.getProductSalesReport(start, end);
    }

    public List<DailySalesReportDTO> getDailySalesReport(LocalDateTime start, LocalDateTime end) {
        List<Object[]> results = saleRepository.getDailySalesReport(start, end);
        List<DailySalesReportDTO> report = new ArrayList<>();
        for (Object[] row : results) {
            // row[0] es saleDate (java.sql.Date), row[1] es totalSalesCount, row[2] es totalAmount
            DailySalesReportDTO dto = new DailySalesReportDTO(
                ((Date) row[0]).toLocalDate(),
                ((Number) row[1]).longValue(),
                ((Number) row[2]).doubleValue()
            );
            report.add(dto);
        }
        return report;
    }

    public List<UserSalesReportDTO> getSalesByUser(LocalDateTime from, LocalDateTime to) {
        return saleRepository.getSalesByUserBetween(from, to);
    }

    public List<SaleResponseDTO> getSalesAsDTO(LocalDateTime from, LocalDateTime to) {
        return saleRepository.findByDateBetween(from, to).stream().map(sale ->
            new SaleResponseDTO(
                sale.getId(),
                sale.getDate(),
                sale.getPaymentMethod(),
                sale.getTotal(),
                sale.getUser().getEmail(),
                sale.getItems().stream().map(item ->
                    new SaleResponseDTO.SaleItemDTO(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                    )
                ).toList()
            )
        ).toList();
    }

    private double getPriceByPaymentMethod(Product product, String paymentMethod) {
        return switch (paymentMethod) {
            case "Transferencia" -> product.getTransferPrice();
            case "Efectivo" -> product.getCashPrice();
            default -> product.getListPrice();
        };
    }


}
