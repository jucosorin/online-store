package com.jucosorin.online.store.exception;

public class ProductNotFoundException extends BusinessException{

    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product with id %s was not found";

    public ProductNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public static ProductNotFoundException with(String message, ErrorCode errorCode, Object... formatParams) {
        return new ProductNotFoundException(String.format(message, formatParams), errorCode);
    }
}
