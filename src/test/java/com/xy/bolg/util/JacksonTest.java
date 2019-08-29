package com.xy.bolg.util;

import com.xy.blog.entity.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JacksonTest {

    public void testJackson() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId(1);
        article.setTitle("我的博客");
        article.setContent("内容");
        article.setCreateTime(new Date());
        articles.add(article);
    }

}
