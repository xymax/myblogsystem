package com.xy.blog.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Article {

    private Integer id;

    private String title;

    private String content;

    private Integer user_Id;

    private Date createTime;

    private String userAccout;



}
