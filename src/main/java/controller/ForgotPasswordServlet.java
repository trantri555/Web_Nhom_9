package controller;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ForgotPasswordServlet", value = "/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login"); // Redirect if access via GET
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDAO dao = new UserDAO();

        if (dao.isUserEmailExists(email)) {
            // 1. Generate new password (simple random string)
            String newPassword = UUID.randomUUID().toString().substring(0, 8);

            // 2. Update DB
            boolean isUpdated = dao.updatePassword(email, newPassword);

            if (isUpdated) {
                // 3. Mock send email
                System.out.println(">>> [MOCK EMAIL] Reset Password for " + email + ": " + newPassword);
                request.setAttribute("success", "Mật khẩu mới đã được gửi vào email (Check Console Log)!");
            } else {
                request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau!");
            }
        } else {
            request.setAttribute("error", "Email không tồn tại trong hệ thống!");
        }

        request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
    }
}
