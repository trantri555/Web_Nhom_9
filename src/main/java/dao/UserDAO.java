package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {
    // Tạo danh sách User ảo thay vì kết nối Database
    private static List<User> listUsers = new ArrayList<>();

    static {
        // Khởi tạo một số tài khoản mẫu
        // Cấu trúc: id, username, password, fullName, email, role
        listUsers.add(new User(1, "admin", "123", "Quản Trị Viên", "admin@gmail.com", 1));
        listUsers.add(new User(2, "user", "123", "Khách Hàng A", "user@gmail.com", 0));
        listUsers.add(new User(3, "test@gmail.com", "123", "Người dùng Test", "test@gmail.com", 0));
    }

    public User login(String user, String pass) {
        // Duyệt qua danh sách ảo để kiểm tra tài khoản
        for (User u : listUsers) {
            if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                return u; // Tìm thấy user khớp tài khoản và mật khẩu
            }
        }
        return null; // Không tìm thấy
    }
}