package main.java.reader.service;

import java.util.List;

import main.java.librarian.dataClass.Book;
import main.java.reader.bean.Rd;
import main.java.dbSQL.BFormdao;

public class BFormService {

	Book book=new Book();
     BFormdao dao=new BFormdao();
     
 	public List<Book> select_by_id(Book bf) {
		List<Book> aList=dao.select_by_id(bf);
		return aList;
	}

	public List<Book> select_by_isbn(Book bf) {
		List<Book> aList=dao.select_by_isbn(bf);
		return aList;
	}

    public Rd createReader(Rd r) {
		 Rd r2=dao.createReaderID(r);
		 return r2;
    }
}
