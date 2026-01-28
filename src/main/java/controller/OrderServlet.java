package controller;

import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.VoucherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import util.DBContext;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 1. Lấy cart từ session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getAllItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }
        session.setAttribute("orderFlag", "show");
        session.removeAttribute("voucher");
        response.sendRedirect(request.getContextPath() + "/cart");
//
//        // 2. Lấy dữ liệu từ form checkout.jsp
//        String fullName = request.getParameter("fullName");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//        String order_status="";
//        String order_payment="";
//        String paymentMethod = request.getParameter("paymentMethod");
//        String code =request.getParameter("voucher");
//        //Lấy dữ liệu từ session
//        User user = (User) session.getAttribute("auth");
//        if (user != null) {
//            int id_user = user.getId();
//        }
//        response.sendRedirect(request.getContextPath() + "/cart");

//
//        // 3. Tạo Order
//        Order order = new Order();
//        order.setTotalPrice(cart.getTotalPrice());
//        order.setStatus("PENDING"); // hoặc PAID nếu thanh toán online
//        order.setOrderDate(new Date());
//
//        Connection conn = null;
//
//        try {
//            // 4. Lấy connection
//            conn = DBContext.getConnection();
//            conn.setAutoCommit(false); // TRANSACTION
//
//            OrderDAO orderDAO = new OrderDAO(conn);
//            OrderItemDAO orderItemDAO = new OrderItemDAO(conn);
//
//            // 5. Insert order → lấy orderId
//            int orderId = orderDAO.insertAndReturnId(order);
//
//            // 6. Insert order_items từ cart
//            for (CartItem item : cart.getAllItems()) {
////                orderItemDAO.insert(order);
//            }
//
//            // 7. Commit transaction
//            conn.commit();
//
//            // 8. Xóa cart
//            session.removeAttribute("cart");
//
//            // 9. Redirect sang trang thành công
//            response.sendRedirect("order-success.jsp");
//
//        } catch (Exception e) {
//            try {
//                if (conn != null) conn.rollback();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            throw new ServletException(e);
//        }
    }

}