package main.java.librarian.dataClass;

public class Book {
    private String ISBN;
    private String bookName;
    private String author;
    private String type;
    private int typeId = 9;
    private String publisher;
    private int sum;
    private int borrow;
    private double price;

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return ISBN;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookName() {
        return bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public int getTypeId(){
        return typeId;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public int getSum() {
        return sum;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }
    public int getBorrow() {
        return borrow;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", typeId=" + typeId +
                ", publisher='" + publisher + '\'' +
                ", sum=" + sum +
                ", borrow=" + borrow +
                ", price=" + price +
                '}';
    }
}
