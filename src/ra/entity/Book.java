package ra.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float price;
    private String author;
    private boolean status;

    public Book() {
    }

    public Book(String bookId, String bookName, float price, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập vào mã sách: ");
        this.bookId = scanner.nextLine();
        System.out.print("Nhập vào tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập vào giá sách: ");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập vào tác giả: ");
        this.author = scanner.nextLine();
        System.out.print("Nhập trạng thái sách: ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData() {
        String displayStatus = this.status ? "Hoạt động" : "Không hoạt động";
        System.out.printf("%-15s%-50s%-15.2f%-30s%-20s\n", bookId, bookName, price, author, displayStatus);
    }
}
