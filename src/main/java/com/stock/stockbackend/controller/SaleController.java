package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.DailySalesReportDTO;
import com.stock.stockbackend.dto.ProductSalesReportDTO;
import com.stock.stockbackend.dto.SaleRequestDTO;
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
    public Sale createSale(@RequestBody SaleRequestDTO request) {
        return saleService.createSale(request);
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

}
