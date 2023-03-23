package main.java.dbSQL;

import main.java.librarian.dataClass.Borrow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowSQL {
    ConnectionSQL borCon = new ConnectionSQL();

    //判断是否超出借阅数量限制
    public int check(Borrow r) {
        int borrowing = -1;
        try{
            borCon.connectDB();
            String sql = "select COUNT(readerID) from borrow where readerID=" + r.getID();
            borCon.statement = borCon.connection.prepareStatement(sql);
            borCon.resultSet = borCon.statement.executeQuery(sql);
            if(borCon.resultSet .next()){
                borrowing = borCon.resultSet .getInt("COUNT(readerID)");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            borCon.close();
        }
        return borrowing;
    }
    //新增借阅
    public int BorrowBooks(Borrow r) {
        int ret = 0;
        try{
            borCon.connectDB();
            String sqlborr = "insert into borrow(ISBN, readerID, borrowDate, staffID) " +
                    "values('" + r.getIsbn() +"'," + r.getID() + ",'" + r.getBorDate() +"','" + r.getStaffID() +"');";
            borCon.statement = borCon.connection.prepareStatement(sqlborr);
            System.out.println(sqlborr);
            ret = borCon.statement.executeUpdate(sqlborr);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            borCon.close();
        }
        return ret;
    }
    //图书借出+1
    public int BorrowAdd(Borrow r) {
        int ret = 0;
        try{
            borCon.connectDB();
            String sql1 = "select borrow from books where ISBN='" + r.getIsbn() + "'";
            borCon.statement = borCon.connection.prepareStatement(sql1);
            borCon.resultSet = borCon.statement.executeQuery(sql1);
            int borrow = -1;
            if(borCon.resultSet .next()){
                borrow = borCon.resultSet .getInt("borrow") + 1;
            }
            String sql2 = "update books set borrow='" + borrow + "' where ISBN='" + r.getIsbn() + "';";
            borCon.statement = borCon.connection.prepareStatement(sql2);
            ret = borCon.statement.executeUpdate(sql2);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            borCon.close();
        }
        return ret;
    }
    //图书借出-1
    public void BorrowMinus(Borrow r) {
        try{
            borCon.connectDB();
            String sql1 = "select borrow from books where ISBN='" + r.getIsbn() + "'";
            borCon.statement = borCon.connection.prepareStatement(sql1);
            borCon.resultSet = borCon.statement.executeQuery(sql1);
            int borrow = -1;
            if(borCon.resultSet .next()){
                borrow = borCon.resultSet .getInt("borrow") - 1;
            }

            String sql2 = "update books set borrow='" + borrow + "' where ISBN='" + r.getIsbn() + "'";
            borCon.statement = borCon.connection.prepareStatement(sql2);
            borCon.statement.executeUpdate(sql2);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            borCon.close();
        }
    }
    //归还
    public int RetBooks(Borrow r) {
        int ret = 0;
        try{
            borCon.connectDB();
            String sqlret = "delete from borrow where ISBN='" + r.getIsbn() + "' and readerID=" + r.getID();
            borCon.statement = borCon.connection.prepareStatement(sqlret);
            ret = borCon.statement.executeUpdate(sqlret);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            borCon.close();
        }
        return ret;
    }
    //借阅查询
    public List<Borrow> readerQuery(String s1, String s2) {
        List<Borrow> listReader =new ArrayList<>();
        String sql1;
        try{
            borCon.connectDB();
            if (s1.equals("借阅者ID")) {
                sql1 = "select * from borrow where readerID =" + Integer.parseInt(s2.trim());
            } else {
                sql1="select * from borrow where readerID in (select readerID from readers where readerName like '%" + s2 + "%')" ;
            }
            System.out.println(sql1);
            borCon.statement = borCon.connection.prepareStatement(sql1);
            borCon.resultSet = borCon.statement.executeQuery();

            while(borCon.resultSet.next()) {
                Borrow r = new Borrow();
                r.setID(borCon.resultSet.getInt("readerID"));
                r.setIsbn(borCon.resultSet.getString("ISBN"));
                r.setBorDate(borCon.resultSet.getString("borrowDate"));
                listReader.add(r);
            }
            // get bookname  readername staffname
            for (int i = 0; i < listReader.size(); i++) {
                String isbn = listReader.get(i).getIsbn();
                int id = listReader.get(i).getID();
                String sql2 = "select bookname, readerName,name from books, readers,borrow,user where books.ISBN = borrow.ISBN and readers.readerID = borrow.readerID and user.id = borrow.staffID and borrow.ISBN = '" + isbn + "' and borrow.readerID =" + id;
                borCon.statement = borCon.connection.prepareStatement(sql2);
                borCon.resultSet = borCon.statement.executeQuery(sql2);
                borCon.resultSet.next();
                listReader.get(i).setBookname(borCon.resultSet.getString("bookname"));
                listReader.get(i).setReadername(borCon.resultSet.getString("readerName"));
                listReader.get(i).setStaffName(borCon.resultSet.getString("name"));
            }
            return listReader;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            borCon.close();
        }
        return null;
    }
}
