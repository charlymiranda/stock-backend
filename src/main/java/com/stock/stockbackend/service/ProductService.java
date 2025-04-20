package com.stock.stockbackend.service;

import com.stock.stockbackend.dto.ProductDTO;
import com.stock.stockbackend.mapper.ProductMapper;
import com.stock.stockbackend.model.Product;
import com.stock.stockbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(p -> new ProductDTO(
                p.getId(),
                p.getCode(),
                p.getName(),
                p.getStock(),
                p.getSize(),
                p.getColor(),
                p.getNetPrice(),
                p.getListPrice(),
                p.getTransferPrice(),
                p.getCashPrice()
            ))
            .toList();
    }


    public Product create(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return productRepository.save(product);
    }

    public Product update(Long id, ProductDTO dto) {
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductMapper.updateEntity(existing, dto);

        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
