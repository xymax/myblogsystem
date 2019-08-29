package com.xy.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.blog.entity.Article;
import com.xy.blog.exception.SystemException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

public class JSONUtil {
    public static String format(Object obj){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return objectMapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SystemException("JSON解析错误"+obj);

        }
    }

    public static<T>T  strFormat(HttpServletRequest request,Class<T> clazz) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return objectMapper.readValue(request.getInputStream(), clazz);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("JSON反序列化失败");

        }
    }


}
