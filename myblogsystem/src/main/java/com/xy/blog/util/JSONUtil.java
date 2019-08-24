package com.xy.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JSONUtil {
    public static String format(Object obj){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return objectMapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
