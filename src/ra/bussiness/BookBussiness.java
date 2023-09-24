package ra.bussiness;

import ra.entity.Book;
import ra.ultilities.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookBussiness {
    public static List<Book> getAllBook() {
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_book()}");
            // Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            // Duyệt các bản ghi trong rs và đẩy ra bookList
            bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookList;
    }

    public static boolean createBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_book(?,?,?,?,?)}");
            // set dữ liệu cho các input variable
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setFloat(3, book.getPrice());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.isStatus());
            // Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_book(?,?,?,?,?)}");
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setFloat(3, book.getPrice());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.isStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Book getBookById(String bookId) {
        Book book = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_book_by_id(?)}");
            // set giá trị tham số vào
            callSt.setString(1, bookId);
            // Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            // Lấy dữ liệu 'rs' đẩy vào đối tượng 'book' trả về
            while (rs.next()) {
                book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return book;
    }

    public static boolean deleteBook(String bookId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_book(?)}");
            callSt.setString(1, bookId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Book> getBookByName(String bookName) {
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_book_by_name(?)}");
            callSt.setString(1, bookName);
            ResultSet rs = callSt.executeQuery();
            // Lấy dữ liệu 'rs' đẩy vào đối tượng 'book' trả về
            bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookList;
    }

    public static void statisticalBookByAuthor() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_cntBook_by_author()}");
            // Thực hiện procedure
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                String author = rs.getString("author");
                int bookCnt = rs.getInt("bookCnt");
                System.out.println("Tác giả: " + author + " - Số lượng: " + bookCnt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    public static List<Book> sortPriceASC() {
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_price_asc()}");
            ResultSet rs = callSt.executeQuery();
            bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookList;
    }
}
