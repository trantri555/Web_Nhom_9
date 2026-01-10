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
        System.out.println(">>> REGISTER SERVLET CALLED");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName= request.getParameter("fullName");
        // Kiểm tra form
        //  Kiểm tra email tồn tại
        if (userDAO.isUserEmailExists(email)) {
            request.setAttribute("error", "Tài khoản đã tồn tại!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        Map<String, String> errors = new HashMap<>(); //Lưu các lỗi
        //Kiểm tra mật khẩu
        if (password == null || password.isEmpty()) {
            errors.put("password", "Mật khẩu không được để trống");
        } else if (!isStrongPassword(password)) {
            errors.put("password",
                    "Mật khẩu ≥ 8 ký tự, có chữ hoa và ký tự đặc biệt");
        } else if (!password.equals(confirmPassword)) {
            errors.put("confirmPassword","Nhập lại mật khẩu chưa chính xác");
        }

        // Có lỗi → quay lại form
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("oldUsername", email);
            request.setAttribute("oldFullName", fullName);
            request.setAttribute("oldEmail", email);

            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
            return;
        }


        //  Kiểm tra độ mạnh mật khẩu
        if (!isStrongPassword(password)) {
            request.setAttribute("error",
                    "Mật khẩu phải ≥ 8 ký tự, có chữ hoa và ký tự đặc biệt!");
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
            return;
        }

        //  Lưu user
        User newUser = new User(0, email, password, "", email, 0);
        userDAO.register(newUser);

        // Thông báo Thành công va chuyen ve trang san pham
        request.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        request.getRequestDispatcher("/view/user/login.jsp")
                .forward(request, response);
    }

    // Hàm kiểm tra mật khẩu mạnh
    private boolean isStrongPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        return hasUpper && hasSpecial;
    }
    }
