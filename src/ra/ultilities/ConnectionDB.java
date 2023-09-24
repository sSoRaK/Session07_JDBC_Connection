package ra.ultilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/book_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection openConnection() {
        // 1. Khai báo đối tượng Connection
        Connection conn = null;
        try {
            // 2. Set driver cho Driver Manager
            Class.forName(DRIVER);
            // 3. Khởi tạo đối tượng conn từ Driver Manager
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //        public static void main(String[] args) {
//        Connection conn = ConnectionDB.openConnection();
//        if (conn != null) {
//            System.out.println("Kết nối thành công.");
//        } else {
//            System.err.println("Kết nối thất bại!");
//        }
//    }
    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
