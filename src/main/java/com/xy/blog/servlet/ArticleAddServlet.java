package com.xy.blog.servlet;

import com.xy.blog.entity.Article;
import com.xy.blog.exception.BusinessException;
import com.xy.blog.exception.ParamentException;
import com.xy.blog.util.DBUtil;
import com.xy.blog.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticleAddServlet extends BaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

/*        String userAccount=req.getParameter("userAccount");
        String title=req.getParameter("title");
        String content=req.getParameter("content");*/
        //获取Json 类型的请求数据
        Article article=JSONUtil.strFormat(req, Article.class);


//                处理业务及数据库操作
        try{
            connection = DBUtil.getConnection();
            String sql = "insert into article (title,content,user_id,create_time) select\n" +
                    "    ?,?,user.id,now() from user where user.name=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setString(3,article.getUserAccout());
            int r = preparedStatement.executeUpdate();
            if (r>0){

                return r;

            }else {
                throw new BusinessException("没有该用户"+article.getUserAccout());

            }
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }
}
