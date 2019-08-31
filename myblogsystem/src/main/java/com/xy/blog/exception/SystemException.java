package com.xy.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends RuntimeException{
    private String code;

    public SystemException(String message) {
        super("服务器端错误"+message);
        code="500";
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        code="501";
    }
}
