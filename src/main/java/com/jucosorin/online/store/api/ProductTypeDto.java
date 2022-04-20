package com.jucosorin.online.store.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.jucosorin.online.store.api.views.ProductViews;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
@JsonView({ProductViews.CreateProduct.class, ProductViews.DisplayProduct.class})
public class ProductTypeDto {

    private UUID id;
    private String name;
}
