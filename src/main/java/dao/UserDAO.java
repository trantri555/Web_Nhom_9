package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import context.DBContext; // Giả sử bạn có lớp kết nối DB này

public class UserDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User login(String user, String pass) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            conn = new DBContext().getConnection(); // Mở kết nối
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}