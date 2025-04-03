package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.ProductDTO;
import com.stock.stockbackend.dto.ProductMapper;
import com.stock.stockbackend.model.Product;
import com.stock.stockbackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
        Product saved = productService.create(ProductMapper.toEntity(dto));
        return ProductMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        Product updated = productService.update(id, ProductMapper.toEntity(dto));
        return ProductMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}

