package main.java.dbSQL;

import main.java.librarian.dataClass.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksSQL {
    ConnectionSQL bookCon = new ConnectionSQL();
    public int insertBook(Book b) {
        int ret = 0;
        try{
            bookCon.connectDB();
            String sql="insert into books(ISBN, bookname, author, typeID, publisher, stock, price) " +
                    "values('" + b.getISBN() +"','" + b.getBookName() + "','" + b.getAuthor() + "','" + b.getTypeId() +"','" + b.getPublisher() + "'," + b.getSum() + "," + b.getPrice() + ")";
            System.out.println(sql);
            bookCon.statement = bookCon.connection.prepareStatement(sql);
            ret = bookCon.statement.executeUpdate(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            bookCon.close();
        }
        return ret;
    }

    public List<Book> selectBook(String s1, String s2) {
        List<Book> listBook =new ArrayList<>();
        String sql;
        try{
            bookCon.connectDB();
            if (s1.equals("ISBN号")) {
                sql = "select * from books where ISBN = '" + s2 + "'";
            } else {
                sql="select * from books where bookname like '%" + s2 + "%'";
            }
            System.out.println(sql);
            bookCon.statement = bookCon.connection.prepareStatement(sql);
            bookCon.resultSet = bookCon.statement.executeQuery();

            while(bookCon.resultSet.next()) {
                Book b = new Book();
                b.setISBN(bookCon.resultSet.getString("ISBN"));
                b.setBookName(bookCon.resultSet.getString("bookname"));
                b.setAuthor(bookCon.resultSet.getString("author"));
                b.setTypeId(bookCon.resultSet.getInt("typeID"));
                b.setPublisher(bookCon.resultSet.getString("publisher"));
                b.setSum(bookCon.resultSet.getInt("stock"));
                b.setBorrow(bookCon.resultSet.getInt("borrow"));
                b.setPrice(bookCon.resultSet.getDouble("price"));
                listBook.add(b);
            }
            for (int i = 0; i < listBook.size(); i++) {
                int id = listBook.get(i).getTypeId();
                String sql2 = "select type from types where typeID=" + id;
                bookCon.statement = bookCon.connection.prepareStatement(sql2);
                bookCon.resultSet = bookCon.statement.executeQuery(sql2);
                bookCon.resultSet.next();
                listBook.get(i).setType(bookCon.resultSet.getString("type"));
            }
            return listBook;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            bookCon.close();
        }
        return null;
    }

    public int delBook(String isbn) {
        int re = 0;
        try{
            bookCon.connectDB();
            String sql = "delete from books where ISBN='" + isbn + "'"; //borrow表跟删
            bookCon.statement = bookCon.connection.prepareStatement(sql);
            re = bookCon.statement.executeUpdate();
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            bookCon.close();
        }
        return re;
    }

    public int modBook(String ISBN, Book b) {
        int ret = 0;
        try{
            bookCon.connectDB();
            String sql = "update books set ISBN='" + b.getISBN() + "', bookname='" + b.getBookName() + "', author='" + b.getAuthor() + "', typeID='" + b.getTypeId() + "', publisher='" + b.getPublisher() + "', stock=" + b.getSum() + ", price=" + b.getPrice() + " where ISBN='" + ISBN + "'";
            System.out.println(sql);
            bookCon.statement = bookCon.connection.prepareStatement(sql);
            ret = bookCon.statement.executeUpdate();
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            bookCon.close();
        }
        return ret;
    }

    public void getTypeId(Book b) {
        try{
            bookCon.connectDB();
            String sql = "select typeID from types where type = '" + b.getType() + "'";
            System.out.println(sql);
            bookCon.statement = bookCon.connection.prepareStatement(sql);
            bookCon.resultSet = bookCon.statement.executeQuery();
            int id = -1;
            if(bookCon.resultSet .next()){
                id = bookCon.resultSet .getInt("typeID");
            }
            b.setTypeId(id);
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            bookCon.close();
        }
    }
}
