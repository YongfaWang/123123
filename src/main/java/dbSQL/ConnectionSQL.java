package main.java.dbSQL;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSQL {
    static  String JDBC_DRIVER = "";
    static  String DB_URL = "";
    static  String USER = "";
    static String PASS = "";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    static {
        Properties pro = new Properties();
        try {
            InputStream fis = ConnectionSQL.class.getClassLoader().getResourceAsStream("database.properties");
            pro.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        JDBC_DRIVER = pro.getProperty("jdbc.driver") ;
        String host = pro.getProperty("jdbc.host") ;
        String port = pro.getProperty("jdbc.port") ;
        String importDatabaseName = pro.getProperty("jdbc.importDatabaseName") ;
        DB_URL  = "jdbc:mysql://" + host + ":" + port+ "/" +importDatabaseName + "?useUnicode=true&characterEncoding=UTF-8";
        USER = pro.getProperty("jdbc.username") ;
        PASS = pro.getProperty("jdbc.password") ;
        System.out.println(DB_URL);
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 链接数据库
    public void connectDB() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("数据库链接成功");
        } catch (SQLException e) {
            System.out.println("数据库链接失败");
            e.printStackTrace();
        }
    }

    // 关闭资源
    public void close() {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
