package com.xy.blog.servlet;

import com.xy.blog.entity.Article;
import com.xy.blog.exception.ParamentException;
import com.xy.blog.util.DBUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ArticleListServlet extends BaseServlet {

    @Override

    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Article> articles = new ArrayList<>();
        String sid=req.getParameter("id");
        Integer id=null;
        //处理前端请求数据
        try{
           id =Integer.parseInt(sid);
        }catch (NumberFormatException e){
            throw new ParamentException("id错误("+sid+")");
        }
        //处理业务及数据库操作

        try{

            connection = DBUtil.getConnection();
            String sql = "select a.id,a.title,a.content,a.create_time from article a,user u where a.user_id=u.id and u.id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,  // TODO  处理前端异常
                    id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
//                article.setUser_Id(resultSet.getInt("user_Id"));
                article.setCreateTime(resultSet.getTimestamp("create_time"));
                articles.add(article);
            }

            System.out.println(articles);
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
            return articles;
    }
}