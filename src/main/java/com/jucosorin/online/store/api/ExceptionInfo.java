package com.jucosorin.online.store.api;

import com.jucosorin.online.store.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionInfo {
    private String path;
    private String error;
    private ErrorCode errorCode;
}
