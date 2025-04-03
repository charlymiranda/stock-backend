package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.SaleRequestDTO;
import com.stock.stockbackend.model.Sale;
import com.stock.stockbackend.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public Sale createSale(@RequestBody SaleRequestDTO request) {
        return saleService.createSale(request);
    }
}
