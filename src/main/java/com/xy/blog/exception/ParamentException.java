package com.xy.blog.exception;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Getter
@Setter
public class ParamentException extends RuntimeException{
    private String code;





    public ParamentException(String message) {
        super("客户端错误"+message);
        code="400";
    }

    public ParamentException(String message, Throwable cause) {
        super(message, cause);
    }

}
