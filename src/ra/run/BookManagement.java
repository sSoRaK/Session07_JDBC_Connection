package ra.run;

import ra.bussiness.BookBussiness;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("*************** MENU ***************");
            System.out.println("1. Hiển thị các sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm sách theo tên sách");
            System.out.println("6. Thống kê sách theo tên tác giả");
            System.out.println("7. Sắp xếp theo giá tăng dần (procedure)");
            System.out.println("8. Thoát");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayBookList();
                    break;
                case 2:
                    BookManagement.createBook(scanner);
                    break;
                case 3:
                    BookManagement.updateBook(scanner);
                    break;
                case 4:
                    BookManagement.deleteBook(scanner);
                    break;
                case 5:
                    BookManagement.searchBookByName(scanner);
                    break;
                case 6:
                    BookManagement.statisticalBook();
                    break;
                case 7:
                    BookManagement.sortPriceAsc();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập lựa chọn từ (1 - 8).");
                    break;

            }
        } while (true);
    }

    public static void displayBookList() {
        List<Book> bookList = BookBussiness.getAllBook();
        System.out.println("********** Thông Tin Danh Sách Book **********");
        bookList.stream().forEach(Book::displayData);
        System.out.println();
    }

    public static void createBook(Scanner scanner) {
        Book bookNew = new Book();
        bookNew.inputData(scanner);
        // Gọi createBook từ BookBussiness để thực hiện thêm dữ liệu vào DB
        boolean result = BookBussiness.createBook(bookNew);
        if (result) {
            System.out.println("Thêm mới thành công.");
        } else {
            System.err.println("Có lỗi xảy ra, vui lòng thực hiện lại!");
        }
    }

    public static void updateBook(Scanner scanner) {
        System.out.print("Nhập mã sách để cập nhật: ");
        String bookIdUpdate = scanner.nextLine();
        // Kiểm tra bookId tồn tại hay không
        Book bookUpdate = BookBussiness.getBookById(bookIdUpdate);
        if (bookIdUpdate != null) {
            // bookId có tồn tại trong CSDL
            System.out.print("Nhập vào tên sách để cập nhật: ");
            bookUpdate.setBookName(scanner.nextLine());
            System.out.print("Nhập vào giá sách để cập nhật: ");
            bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.print("Nhập vào tác giả để cập nhật: ");
            bookUpdate.setAuthor(scanner.nextLine());
            System.out.print("Nhập vào trạng thái để cập nhật: ");
            bookUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
            // Thực hiện cập nhật
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result) {
                System.out.println("Cập nhật thành công.");
            } else {
                System.err.println("Cập nhật thất bại, vui lòng thực hiện lại!");
            }
        } else {
            // bookId không tồn tại trong CSDL
            System.err.println("Mã sách không tồn tại.");
        }
    }

    public static void deleteBook(Scanner scanner) {
        System.out.print("Nhập vào mã sách cần xóa: ");
        String bookIdDelete = scanner.nextLine();
        // check bookId tồn tại trong DB
        Book bookDelete = BookBussiness.getBookById(bookIdDelete);
        if (bookDelete != null) {
            boolean result = BookBussiness.deleteBook(bookIdDelete);
            if (result) {
                System.out.println("Xóa thành công.");
            } else {
                System.err.println("Có lỗi xảy ra, vui lòng thực hiện lại!");
            }
        } else {
            System.err.println("Mã sách không tồn tại!");
        }
    }

    public static void searchBookByName(Scanner scanner) {
        System.out.print("Nhập vào tên sách cần tìm: ");
        String bookNameSearch = scanner.nextLine().toLowerCase().trim();
        // Kiểm tra bookName tồn tại trong CSDL
        List<Book> bookList = BookBussiness.getBookByName(bookNameSearch);
        if (!bookList.isEmpty()) {
            System.out.println("********** Thông Tin Danh Sách Book **********");
            bookList.stream().forEach(Book::displayData);
            System.out.println();
        } else {
            System.err.println("Không tìm thấy sách [ " + bookNameSearch + " ] trong danh sách!");
        }
    }

    public static void statisticalBook() {
        System.out.println("******* Số lượng sách thống kê theo tác giả *******");
        BookBussiness.statisticalBookByAuthor();
    }

    public static void sortPriceAsc() {
        List<Book> bookList = BookBussiness.sortPriceASC();
        System.out.println("********** Thông Tin Danh Sách Book(ASC) **********");
        bookList.stream().forEach(Book::displayData);
        System.out.println();
    }
}
