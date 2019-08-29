package com.xy.blog.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.xy.blog.exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/blogdemo";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";


    private static volatile DataSource DATASOURCE;

    private DBUtil() {

    }

    public static DataSource getDATASOURCE() {
        if (DATASOURCE == null) {
//          synchronized (DATASOURCE){
//         synchronized (DATASOURCE.getClass()) {
            synchronized (DBUtil.class) {

                if (DATASOURCE == null) {
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATASOURCE).setURL(URL);
                    ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
                    ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);

                }
            }
        }
        return DATASOURCE;
    }

    public static Connection getConnection() {
        try {
            return getDATASOURCE().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();

//            TODO 处理数据库异常
            throw new SystemException("数据库出错误");

        }


    }


}
