package com.xy.blog.servlet;

import com.xy.blog.entity.Article;
import com.xy.blog.exception.BusinessException;
import com.xy.blog.exception.ParamentException;
import com.xy.blog.util.DBUtil;
import com.xy.blog.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String ids = req.getParameter("ids");
        int[] intIds=null;
        try {
            String[] idArray = ids.split(",");
            intIds = new int[idArray.length];

            for (int i = 0; i < idArray.length; i++) {
                intIds[i] = Integer.parseInt(idArray[i]);
            }
        } catch (Exception e) {
            throw new ParamentException("请求参数异常" + ids);
        }


       // 处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");
            for (int i = 0; i < intIds.length; i++) {
                if (i==0){
                    sql.append("?");
                }else {
                    sql.append(",?");
                }
            }
            sql.append(")");
            System.out.println("sql="+sql);

            preparedStatement=connection.prepareStatement(sql.toString());
            for (int i = 0; i < intIds.length; i++) {
                preparedStatement.setInt(i+1,intIds[i]);
            }
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return null;
    }
}
