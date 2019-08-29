package com.xy.bolg.util;

import com.xy.blog.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.smartcardio.CommandAPDU;
import java.sql.Connection;

public class DBUtilTest {
    @Test
    public void testConnection() {
        Connection connection = DBUtil.getConnection();
//        System.out.println(connection);
        Assert.assertNotNull(connection);//断言他是否为空

    }
}
