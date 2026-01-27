package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Giả lập dữ liệu hoặc gọi Service ở đây
        req.setAttribute("todayOrders", 32);

        // Đảm bảo đường dẫn này trỏ đúng đến file .jsp admin của bạn
        req.getRequestDispatcher("/view/admin/admin-dashboard.jsp").forward(req, resp);
    }
}