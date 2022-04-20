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
public class ProductDto {

    @JsonView(ProductViews.DisplayProduct.class)
    private UUID id;
    @JsonView({ProductViews.CreateProduct.class, ProductViews.DisplayProduct.class})
    private String name;
    @JsonView({ProductViews.CreateProduct.class, ProductViews.DisplayProduct.class})
    private ProductTypeDto productType;
    @JsonView(ProductViews.CreateProduct.class)
    private int initialStock;
    @JsonView(ProductViews.DisplayProduct.class)
    private int stock;
    @JsonView({ProductViews.CreateProduct.class, ProductViews.DisplayProduct.class})
    private String price;

}
