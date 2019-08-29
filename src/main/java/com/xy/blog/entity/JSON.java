package com.xy.blog.entity;

import lombok.Data;

@Data
public class JSON {

    private boolean success;//是否成功

    private String code;//错误编码

    private String message;//错误信息

    private Object data;

}
