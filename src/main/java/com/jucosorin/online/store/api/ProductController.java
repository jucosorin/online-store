package com.jucosorin.online.store.api;

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

@RestController
@RequestMapping("api/{api_version}/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    public static final String API_VERSION = "api_version";
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> addProduct(@PathVariable(API_VERSION) String apiVersion,
                                              @RequestBody ProductDto productDto) {
        final var product = productService.addProduct(productMapper.toProduct(productDto));

        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable(API_VERSION) String apiVersion) {

        final var products = productService.getAllProducts();

        return ResponseEntity.ok(productMapper.toProductDtoList(products));
    }
}
