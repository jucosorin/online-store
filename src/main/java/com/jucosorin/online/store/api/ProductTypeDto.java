package com.jucosorin.online.store.api;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class ProductTypeDto {

    private UUID id;
    private String name;
}
