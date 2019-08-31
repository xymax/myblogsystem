package com.xy.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException(String message) {
        super("事物异常"+message);
        code="401";
    }

    public BusinessException(String message, Throwable cause) {
        super("业务异常"+message, cause);
    }
}
