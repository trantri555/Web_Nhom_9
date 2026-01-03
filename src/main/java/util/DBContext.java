package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private static final String URL =
            "jdbc:mysql://localhost:3306/juicy_db"
                    + "?useSSL=false&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=Asia/Ho_Chi_Minh";

    private static final String USER = "root";
    private static final String PASSWORD = ""; // WAMP mặc định

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
