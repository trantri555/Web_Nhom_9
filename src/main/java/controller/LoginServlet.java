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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang login khi người dùng truy cập /login
        request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy dữ liệu từ form JSP
        // Chú ý: "loginEmail" và "password" phải trùng với name="..." trong <input> của JSP
        String user = request.getParameter("loginEmail");
        String pass = request.getParameter("password");

        // 2. Gọi lớp DAO (lúc này đang dùng dữ liệu ảo)
        UserDAO dao = new UserDAO();
        User u = dao.login(user, pass);

        if (u != null) {
            // Đăng nhập thành công: Lưu vào Session
            HttpSession session = request.getSession();
            session.setAttribute("acc", u);

            // Chuyển hướng về trang chủ
            response.sendRedirect("products"); // Hoặc "index.jsp" tùy bạn
        } else {
            // Đăng nhập thất bại: Gửi thông báo lỗi
            request.setAttribute("mess", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        }
    }
}