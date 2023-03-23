package main.java.dbSQL;

import main.java.login.dataClass.Account;

import java.sql.SQLException;

public class MainLogInSQL {
    ConnectionSQL loginCon = new ConnectionSQL();

    public int checkUserId(Account u) {
        int count = 0;
        try{
            loginCon.connectDB();
            String sql = "select COUNT(*) from user where id='" + u.getId() + "'";
            loginCon.statement = loginCon.connection.prepareStatement(sql);
            loginCon.resultSet = loginCon.statement.executeQuery(sql);
            if(loginCon.resultSet .next()){
                count = loginCon.resultSet .getInt("COUNT(*)") ;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            loginCon.close();
        }
        return count;
    }

    public int checkUserPsw(Account u) {
        int count = 0;
        try{
            loginCon.connectDB();
            String sql = "select COUNT(*) from user where id='" + u.getId() + "' and password ='" + u.getPsw() + "'";
            loginCon.statement = loginCon.connection.prepareStatement(sql);
            loginCon.resultSet = loginCon.statement.executeQuery(sql);
            if(loginCon.resultSet .next()){
                count = loginCon.resultSet .getInt("COUNT(*)") ;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            loginCon.close();
        }
        return count;
    }

    public int getType(Account u) {
        int type = 0;
        try{
            loginCon.connectDB();
            String sql = "select * from user where id='" + u.getId() + "' and password='" + u.getPsw() + "'";
            loginCon.statement = loginCon.connection.prepareStatement(sql);
            loginCon.resultSet = loginCon.statement.executeQuery(sql);
            if(loginCon.resultSet .next()){
                type = loginCon.resultSet .getInt("type") ;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            loginCon.close();
        }
        return type;
    }
}
