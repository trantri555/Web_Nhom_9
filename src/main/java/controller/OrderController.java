//package controller;
//
//import dao.ProductDAO;
//import model.Product;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//import java.io.IOException;
//import java.util.List;
//@WebServlet("/order")
//public class OrderController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login");
//            return;
//        }
//
//        request.getRequestDispatcher("/view/user/order.jsp")
//                .forward(request, response);
//    }
//}
package controller;

import dao.OrderDAO;
import model.Order;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);

        RequestDispatcher rd = request.getRequestDispatcher("/admin-orders.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // 🔹 THÊM ĐƠN HÀNG
        if ("add".equals(action)) {
            String customerName = request.getParameter("customerName");
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

            Order order = new Order();
            order.setCustomerName(customerName);
            order.setTotalPrice(totalPrice);
            order.setStatus("Chờ xác nhận");
            order.setOrderDate(new Date());

            orderDAO.addOrder(order);
        }

        // 🔹 CẬP NHẬT TRẠNG THÁI
        if ("updateStatus".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String status = request.getParameter("status");
            orderDAO.updateStatus(orderId, status);
        }
        //xóa đơn hàng
        if ("delete".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            orderDAO.deleteOrder(orderId);
        }

        response.sendRedirect("orders");
    }


}