package com.jucosorin.online.store.mapper;

import com.jucosorin.online.store.api.ProductDto;
import com.jucosorin.online.store.api.ProductTypeDto;
import com.jucosorin.online.store.model.Product;
import com.jucosorin.online.store.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "stock", source = "initialStock")
    Product toProduct(ProductDto dto);

    ProductDto toProductDto(Product product);

    ProductType toProductType(ProductTypeDto dto);

    ProductTypeDto toProductTypeDto(ProductType productType);

    List<ProductDto> toProductDtoList(List<Product> products);

}
