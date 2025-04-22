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

    public List<ProductDTO> getAll() {
        return productRepository.findAll()
            .stream()
            .map(ProductMapper::toDTO)
            .toList();
    }

    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ProductMapper.toDTO(product);
    }

    public ProductDTO create(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return ProductMapper.toDTO(productRepository.save(product));
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        ProductMapper.updateEntity(product, dto);
        return ProductMapper.toDTO(productRepository.save(product));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
