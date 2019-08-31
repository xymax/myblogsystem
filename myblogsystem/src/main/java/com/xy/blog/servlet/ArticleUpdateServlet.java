package com.xy.blog.servlet;

import com.xy.blog.entity.Article;
import com.xy.blog.exception.BusinessException;
import com.xy.blog.util.DBUtil;
import com.xy.blog.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        Article article = JSONUtil.strFormat(req, Article.class);

//                处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            String sql = "update article set title=?,content=?where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setInt(3, article.getId());
            int r = preparedStatement.executeUpdate();
            if (r > 0) {
                return r;
            } else {
                throw new BusinessException("文章不存在" + article.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
