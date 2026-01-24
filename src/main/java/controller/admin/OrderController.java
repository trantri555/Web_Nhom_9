package controller.admin;

import dao.OrderDAO;
import model.Order;
import model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/orders")
public class OrderController extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");
        orderDAO = new OrderDAO(conn);
    }

    // ================== HIỂN THỊ DANH SÁCH ĐƠN ==================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("../login");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (!"ADMIN".equals(user.getRole())) {
            response.sendError(403);
            return;
        }

        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/admin-orders.jsp")
                .forward(request, response);
    }

    // ================== XỬ LÝ HÀNH ĐỘNG ==================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("../login");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (!"ADMIN".equals(user.getRole())) {
            response.sendError(403);
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("orders");
            return;
        }

        switch (action) {

            // ➕ THÊM ĐƠN (ít dùng – chủ yếu test)
            case "add":
                String customerName = request.getParameter("customerName");
                double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

                Order order = new Order();
                order.setCustomerName(customerName);
                order.setTotalPrice(totalPrice);
                order.setStatus("Chờ xác nhận");
                order.setOrderDate(new Date());

                orderDAO.addOrder(order);
                break;

            // 🔄 CẬP NHẬT TRẠNG THÁI
            case "updateStatus":
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String status = request.getParameter("status");
                orderDAO.updateStatus(orderId, status);
                break;

            // ❌ XÓA 1 ĐƠN
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("orderId"));
                orderDAO.deleteOrder(deleteId);
                break;

            // 🔥 XÓA TOÀN BỘ ĐƠN
            case "deleteAll":
                orderDAO.deleteAllOrders();
                break;
        }

        response.sendRedirect("orders");
    }
}
