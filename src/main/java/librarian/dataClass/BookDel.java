package main.java.librarian.dataClass;

public class BookDel {
    private String isbn;
    private int rowIndex;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
    public  BookDel() {

    }
    public BookDel(String isbn, int rowIndex) {
        this.isbn = isbn;
        this.rowIndex = rowIndex;
    }
}
