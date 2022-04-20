package com.jucosorin.online.store.services;

import com.jucosorin.online.store.exception.ProductNotFoundException;
import com.jucosorin.online.store.model.Product;
import com.jucosorin.online.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.jucosorin.online.store.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static com.jucosorin.online.store.exception.ProductNotFoundException.PRODUCT_NOT_FOUND_MESSAGE;

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

    @Transactional
    public Product updatePrice(UUID productId, String price) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.with(PRODUCT_NOT_FOUND_MESSAGE, PRODUCT_NOT_FOUND, productId));

        product.setPrice(new BigDecimal(price));

        return productRepository.save(product);
    }
}
