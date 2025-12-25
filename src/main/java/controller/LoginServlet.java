package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy dữ liệu từ JSP
        String user = request.getParameter("loginEmail");
        String pass = request.getParameter("password");



        // 2. Gọi lớp DAO để kiểm tra
        UserDAO dao = new UserDAO();
        User u = dao.login(user, pass);

        if (u != null) {
            // Đăng nhập thành công: Lưu vào Session và chuyển đến trang chủ
            HttpSession session = request.getSession();
            session.setAttribute("acc", u);
            response.sendRedirect("index.jsp");
        } else {
            // Đăng nhập thất bại: Báo lỗi và quay lại trang login
            request.setAttribute("mess", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}