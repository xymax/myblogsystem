package com.xy.blog.servlet;

import com.xy.blog.entity.Article;
import com.xy.blog.util.DBUtil;
import com.xy.blog.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ArticleListServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "select a.id,a.title,a.content,a.create_time from article a" +
                ",user u where a.user_id=u.id and u.id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(req.getParameter("id")));
            ResultSet resultSet = ps.executeQuery();
            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle("tlte");
                article.setContent("content");
                article.setCreatetime(resultSet.getTimestamp("create_time"));
                articles.add(article);
            }
            return articles;
    }
}

