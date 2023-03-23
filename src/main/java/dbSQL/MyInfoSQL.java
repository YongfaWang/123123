package main.java.dbSQL;

import java.sql.SQLException;

public class MyInfoSQL {
    ConnectionSQL myCon = new ConnectionSQL();
    public String getPsw(String id) {

        try{
            myCon.connectDB();
            String sql = "select password from user where id='" + id + "'";
            System.out.println(sql);
            myCon.statement = myCon.connection.prepareStatement(sql);
            myCon.resultSet = myCon.statement.executeQuery(sql);
            String psw;
            if(myCon.resultSet.next()) {
                psw = myCon.resultSet.getString("password");
                // 查询到用户信息返回结果集
                return psw;
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            myCon.close();
        }
        return null;
    }

    public int pswChange(String id, String psw) {
        int ret = 0;
        try{
            myCon.connectDB();
            String sql = "update user set password='" + psw + "' where id='" + id + "'";
            System.out.println(sql);
            myCon.statement = myCon.connection.prepareStatement(sql);
            ret = myCon.statement.executeUpdate(sql);
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            myCon.close();
        }
        return ret;
    }

}
