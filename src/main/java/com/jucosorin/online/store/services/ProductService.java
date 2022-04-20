package com.jucosorin.online.store.services;

import com.jucosorin.online.store.model.Product;
import com.jucosorin.online.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public long getQuantityByProductType(String name) {
        return productRepository.contByProductType(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProduct(UUID productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
