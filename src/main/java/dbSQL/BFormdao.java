package main.java.dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.dbSQL.ConnectionSQL;
import main.java.librarian.dataClass.Book;
import main.java.reader.bean.Rd;
import main.java.reader.util.DBUtil;

public class
BFormdao {
//	Connection conn= null;
//	ResultSet rs = null;
//	PreparedStatement pstmt =null;
	ConnectionSQL Bfcon = new ConnectionSQL();



	int n=0;
	Book b2=null;
	List<Book> aList=null;

	Rd r2=new Rd();
	public List<Book> select_by_id(Book bf) {
		System.out.println("select_by_id...");
		aList=new ArrayList();
		try {
			Bfcon.connectDB();
//			conn= DBUtil.getConnection();
			String sql="select * from books where bookname like ?";
//			pstmt=conn.prepareStatement(sql);
			Bfcon.statement =Bfcon.connection.prepareStatement(sql);
//			pstmt.setString(1, "%"+bf.getBookName()+"%");
			Bfcon.statement.setString(1, "%"+bf.getBookName()+"%");
			Bfcon.resultSet=Bfcon.statement.executeQuery();
			while(Bfcon.resultSet.next()){
				b2=new Book();
				b2.setISBN(Bfcon.resultSet.getString("ISBN"));
				b2.setBookName(Bfcon.resultSet.getString("bookname"));
				b2.setPublisher(Bfcon.resultSet.getString("publisher"));
				b2.setAuthor(Bfcon.resultSet.getString("author"));
				b2.setSum(Bfcon.resultSet.getInt("stock"));
				b2.setBorrow(Bfcon.resultSet.getInt("borrow"));
			    b2.setPrice(Bfcon.resultSet.getDouble("price"));
				aList.add(b2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Bfcon.close();
		}
		//创建一条通路		
		return aList;
	}
	public List<Book> select_by_isbn(Book bf) {
		System.out.println("select_by_isbn...");
		aList=new ArrayList();
		try {
			Bfcon.connectDB();
//			conn=DBUtil.getConnection();
			String sql="select * from books where ISBN like ?";
			Bfcon.statement=Bfcon.connection.prepareStatement(sql);
			Bfcon.statement.setString(1, "%"+bf.getISBN()+"%");
			Bfcon.resultSet=Bfcon.statement.executeQuery();
			while(Bfcon.resultSet.next()){
				b2=new Book();
				b2.setISBN(Bfcon.resultSet.getString("ISBN"));
				b2.setBookName(Bfcon.resultSet.getString("bookname"));
				b2.setPublisher(Bfcon.resultSet.getString("publisher"));
				b2.setAuthor(Bfcon.resultSet.getString("author"));
				b2.setSum(Bfcon.resultSet.getInt("stock"));
				b2.setBorrow(Bfcon.resultSet.getInt("borrow"));
				b2.setPrice(Bfcon.resultSet.getDouble("price"));
				aList.add(b2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Bfcon.close();
		}
		//创建一条通路		
		return aList;
	}

	public Rd createReaderID(Rd r) {
		System.out.println("selectReader...");
		try {
			Bfcon.connectDB();
//			conn=DBUtil.getConnection();
			String sql="INSERT into readers(readerName) values('"+r.getReaderName()+"')";

			Bfcon.statement=Bfcon.connection.prepareStatement(sql);
			Bfcon.statement.executeUpdate();

			String sql2 = "select * from readers where readerName='"+r.getReaderName()+"' order by readerID desc LIMIT 1";

			Bfcon.statement=Bfcon.connection.prepareStatement(sql2);
			Bfcon.resultSet=Bfcon.statement.executeQuery(sql2);

			if(Bfcon.resultSet.next()){
				r2=new Rd();
				r2.setReaderID(Bfcon.resultSet.getString("readerID"));
				r2.setReaderName(Bfcon.resultSet.getString("readerName"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Bfcon.close();
		}
		//创建一条通路
		return r2;
	}
}
