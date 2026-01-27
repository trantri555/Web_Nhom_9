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

        String user = request.getParameter("email");
        String pass = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User u = dao.login(user, pass);

        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("acc", u);

            if (u.getRole().equals("admin")) {
                request.getRequestDispatcher("/view/admin/admin-dashboard.jsp")
                        .forward(request, response);
                return;
            } else {
                response.sendRedirect(request.getContextPath() + "/products");
                return;
            }
        }
        else {
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        }
    }
}