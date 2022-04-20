package com.jucosorin.online.store.api;

import com.jucosorin.online.store.exception.ErrorCode;
import com.jucosorin.online.store.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ExceptionInfo> handleBadRequest(Exception ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ProductNotFoundException pnfex) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleProductNotFoundException(pnfex, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    protected ResponseEntity<ExceptionInfo> handleProductNotFoundException(ProductNotFoundException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        log.error(ex.getLocalizedMessage(), ex);

        return new ResponseEntity<>(ExceptionInfo.builder()
                .path(request.getRequestURI())
                .errorCode(ex.getErrorCode())
                .error(ex.getLocalizedMessage())
                .build(), headers, status);
    }

    protected ResponseEntity<ExceptionInfo> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        log.error(ex.getLocalizedMessage(), ex);

        return new ResponseEntity<>(ExceptionInfo.builder()
                .path(request.getRequestURI())
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .error(ex.getLocalizedMessage())
                .build(), headers, status);
    }
}
