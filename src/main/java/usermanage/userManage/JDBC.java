package main.java.usermanage.userManage;


import java.sql.*;
 
public class JDBC{
 
    public static void main(String[] args) throws Exception {
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // 打开链接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC", "root", "z123456");
 
            // 执行查询
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name, sex FROM user";
            ResultSet rs = stmt.executeQuery(sql);
 
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
 
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("  NAME: " + name);
                System.out.print("  SEX: " + sex);
                System.out.print("\n");
            }
            // 释放资源
            rs.close();
            stmt.close();
            conn.close();
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 
    }
}
