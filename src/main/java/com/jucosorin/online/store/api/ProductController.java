package com.jucosorin.online.store.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.jucosorin.online.store.api.views.ProductViews;
import com.jucosorin.online.store.exception.ProductNotFoundException;
import com.jucosorin.online.store.mapper.ProductMapper;
import com.jucosorin.online.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.jucosorin.online.store.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static com.jucosorin.online.store.exception.ProductNotFoundException.PRODUCT_NOT_FOUND_MESSAGE;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    public static final String PRODUCT_ID = "productId";
    public static final String PRICE = "price";

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        final var product = productService.addProduct(productMapper.toProduct(productDto));

        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        log.info("Add new product with id {}", product.getId());

        return ResponseEntity.created(location).build();
    }

    @JsonView(ProductViews.CreateProduct.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        final var products = productService.getAllProducts();

        return ResponseEntity.ok(productMapper.toProductDtoList(products));
    }

    @JsonView(ProductViews.DisplayProduct.class)
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProduct(@PathVariable(PRODUCT_ID) UUID productId) {
        final var product = productService.findProduct(productId)
                .orElseThrow(() -> ProductNotFoundException.with(PRODUCT_NOT_FOUND_MESSAGE, PRODUCT_NOT_FOUND, productId));
        return ResponseEntity.ok(productMapper.toProductDto(product));
    }

    @JsonView(ProductViews.DisplayProduct.class)
    @PostMapping(value = "/{productId}/updatePrice/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updatePrice(@PathVariable(PRODUCT_ID) UUID productId, @PathVariable(PRICE) String price) {

        var product = productService.updatePrice(productId, price);

        log.info("Update price to {} for product with id {}", price, productId);

        return ResponseEntity.ok(productMapper.toProductDto(product));
    }
}
