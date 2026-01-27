package controller;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");

        Map<String, String> errors = new HashMap<>(); // Lưu các lỗi

        // 1. Check existing user/email
        if (userDAO.isUserEmailExists(email)) {
            errors.put("email", "Email đã tồn tại!");
        }
        if (userDAO.isUserNameExists(username)) {
            errors.put("username", "Tên tài khoản đã tồn tại!");
        }

        // 2. Validate password
        if (password == null || password.isEmpty()) {
            errors.put("password", "Mật khẩu không được để trống");
        } else if (!isStrongPassword(password)) {
            errors.put("password", "Mật khẩu ≥ 8 ký tự, có chữ hoa và ký tự đặc biệt");

        } else if (!password.equals(confirmPassword)) {
            errors.put("confirmPassword", "Nhập lại mật khẩu chưa chính xác");
        }

        // 3. Return if errors
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("oldUsername", username);
            request.setAttribute("oldFullName", fullName);
            request.setAttribute("oldEmail", email);

            // Switch to register tab automatically using JS or just show errors
            // Switch to register tab automatically
            request.setAttribute("activeTab", "register");
            request.setAttribute("error", "Vui lòng kiểm tra lại thông tin!"); // General error
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
            return;
        }


// 4. Register
        try {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setFullName(fullName);
            newUser.setEmail(email);
            newUser.setRole(0); // Default user role

            userDAO.register(newUser);

            // 5. Success
            request.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log err to server console
            request.setAttribute("errors", errors); // Keep existing errors map if any (though likely empty here)
            request.setAttribute("oldUsername", username);
            request.setAttribute("oldFullName", fullName);
            request.setAttribute("oldEmail", email);
            request.setAttribute("activeTab", "register");
            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        }
    }

    // Hàm kiểm tra mật khẩu mạnh
    private boolean isStrongPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        return hasUpper && hasSpecial;
    }
}
