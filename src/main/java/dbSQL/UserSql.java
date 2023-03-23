package main.java.dbSQL;

import main.java.usermanage.userManage.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSql {
	//这里是MySQLDemo 类
	/*
	*java连接mysql数据库
	*1、加载驱动程序
	*2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?"
	*3、数据库登录名
	*3、数据库登录密码
	*/
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";
//
//
//    // 数据库的用户名与密码，需要根据自己的设置
//    static final String USER = "root";
//    static final String PASS = "z123456";
//
//    private Connection connection = null;
//	private PreparedStatement pStatement = null;
//	private ResultSet rSet = null;

	ConnectionSQL userCon = new ConnectionSQL();

	// 加载驱动
		// 静态初始化块（只执行一次）
//	static {
//		try {
//			Class.forName(JDBC_DRIVER);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void  connectDB() {
//		try {
//			connection = DriverManager.getConnection(DB_URL,USER,PASS);
//			System.out.println("数据库链接成功");
//		} catch (SQLException e) {
//			System.out.println("数据库链接失败");
//			e.printStackTrace();
//		}
//	}
	
	// 关闭资源
//	public void close() {
//		if(rSet != null) {
//			try {
//				rSet.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		if(pStatement != null) {
//			try {
//				pStatement.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		if(connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
	public  void addUser(String id, String nameString, String sexString, int entryyear,String passwordString) throws SQLException {

		// try finally 无论是否抛出异常都将执行 finally 中的语句
		try {
			// 先链接到数据库
			userCon.connectDB();
			
			// sql 语句
			// 静态 sql 语句需要进行字符串拼接
			// 动态 sql 语句
			String addsql = "insert into user(id,name,sex,entryyear,password) values(?, ?, ?, ?, ?)";

			userCon.statement = userCon.connection.prepareStatement(addsql);

			userCon.statement.setString(1, id);
			userCon.statement.setString(2, nameString);
			userCon.statement.setString(3, sexString);
			userCon.statement.setInt(4, entryyear);
			userCon.statement.setString(5, passwordString);

			userCon.statement.executeUpdate();
			
		} finally {

			userCon.close();
			
		}
		
	}
	
	public void changeUser(String id, String nameString, String sexString, int entryyear, String passwordString) throws SQLException {
					
		try {
			userCon.connectDB();
			
			String changesql = "update user set name = ?, sex = ?, entryyear = ?, password = ? where id = ?";

			userCon.statement = userCon.connection.prepareStatement(changesql);

			userCon.statement.setString(1, nameString);
			userCon.statement.setString(2, sexString);
			userCon.statement.setInt(3, entryyear);
			userCon.statement.setString(4, passwordString);
			userCon.statement.setString(5, id);

			userCon.statement.executeUpdate();
			
		} finally {
			userCon.close();
		}	
 
	}
 
	public void deleteUser(String id) throws SQLException {
				
		try {

			userCon.connectDB();
			
			String deleteString = "select * from user where id = ?";

			userCon.statement = userCon.connection.prepareStatement(deleteString);

			userCon.statement.setString(1, id);

			userCon.statement.execute();
 
		} finally {
			userCon.close();
		}
		
	}
	
	public User queryUser(String id) throws SQLException {
		try {
			userCon.connectDB();
			
			String querysql = "select * from user where id = ?";
			userCon.statement = userCon.connection.prepareStatement(querysql);
			userCon.statement.setString(1, id);
			// 三种方法执行
			// execute()	都可以使用，返回true或false
			// executeQuery() 专门用于查询，返回结果集
			// executeUpdate() 专门用于删除、更新
			userCon.resultSet = userCon.statement.executeQuery();
			
			if(userCon.resultSet.next()) {
				
				String nameString = userCon.resultSet.getString(2);
				String sexString = userCon.resultSet.getString(3);
				int entryyearString = userCon.resultSet.getInt(4);
				String passwordString = userCon.resultSet.getString(5);
				
				// 查询到用户信息返回结果集
				return new User(id,nameString, sexString,entryyearString, passwordString);
			} else {
				// 没有查询到信息，返回null
				return null;
			}
 
		} finally {
			userCon.close();
		}
 
	}
 
}

