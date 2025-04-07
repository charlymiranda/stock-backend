package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.*;
import com.stock.stockbackend.mapper.SaleMapper;
import com.stock.stockbackend.model.Sale;
import com.stock.stockbackend.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public SaleResponseDTO  createSale(@RequestBody SaleRequestDTO request) {
        Sale sale = saleService.createSale(request);
        return SaleMapper.toDTO(sale);
    }

    @GetMapping
    public List<Sale> getSales(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return saleService.getSalesBetween(from, to);
    }

    @GetMapping("/report/products")
    public List<ProductSalesReportDTO> getProductSalesReport(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return saleService.getProductSalesReport(from, to);
    }

    @GetMapping("/report/daily")
    public List<DailySalesReportDTO> getDailySalesReport(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return saleService.getDailySalesReport(from, to);
    }

    @GetMapping("/report/users")
    public List<UserSalesReportDTO> getSalesByUser(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return saleService.getSalesByUser(from, to);
    }

    @GetMapping("/report/full")
    public List<SaleResponseDTO> getSalesFull(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return saleService.getSalesAsDTO(from, to);
    }


}
