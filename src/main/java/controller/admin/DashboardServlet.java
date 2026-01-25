package controller.admin;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
    // private final ProductService productService = new ProductService();
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    // throws ServletException, IOException {
    //
    // req.setAttribute("todayOrders", 32);
    // req.setAttribute("weekOrders", 180);
    // req.setAttribute("monthOrders", 980);
    //
    // // Top 3 bán chạy
    // req.setAttribute("topProducts",
    // productService.getTop3BestSeller());
    //
    // req.getRequestDispatcher("/views/admin-dashboard.jsp")
    // .forward(req, resp);
    //
    // req.getRequestDispatcher("/views/admin-dashboard.jsp")
    // .forward(req, resp);
    // }
}
