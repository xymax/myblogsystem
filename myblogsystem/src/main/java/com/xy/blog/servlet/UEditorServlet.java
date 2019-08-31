package com.xy.blog.servlet;

import com.xy.blog.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = UEditorServlet.class.getClassLoader()
                .getResource("config.json").getPath();
        MyActionEnter actionEnter = new MyActionEnter(req, path);

        String exec = actionEnter.exec();
        resp.getWriter().write(exec);
    }
}
