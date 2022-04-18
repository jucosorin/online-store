package com.jucosorin.online.store.services;

import com.jucosorin.online.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public long getQuantityByProductType(String name) {
        return productRepository.contByProductType(name);
    }
}
