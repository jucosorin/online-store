package com.jucosorin.online.store.api;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class ProductDto {

    private UUID id;
    private String name;
    private ProductTypeDto productType;
    private int initialStock;
    private String price;

}
