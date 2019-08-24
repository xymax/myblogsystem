package com.xy.blog.servlet;

import com.xy.blog.entity.JSON;
import com.xy.blog.util.JSONUtil;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");


        JSON result=new JSON();
        try {
            Object data=process(req,resp);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);
            data = process(req,resp);


        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage("服务器错误");
        }
        resp.getWriter().write(JSONUtil.format(result));

    }


    public abstract Object process(HttpServletRequest req,
                                   HttpServletResponse resp) throws Exception;

}



