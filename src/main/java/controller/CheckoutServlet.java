package controller;

import dao.OrderDAO;
import dao.OrderItemDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import util.DBContext;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getAllItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String customerName = request.getParameter("customerName");

        Connection conn = null;

        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false); //  TRANSACTION

            // 1. Tạo order
            Order order = new Order();
            order.setCustomerName(customerName);
            order.setTotalPrice(cart.getTotalPrice());
            order.setStatus("PAID");
            order.setOrderDate(new Date());

            OrderDAO orderDAO = new OrderDAO(conn);
            int orderId = orderDAO.insertOrder(order); /////Lỗi////////

            // 2. Tạo order_items
            OrderItemDAO itemDAO = new OrderItemDAO();

            for (CartItem ci : cart.getAllItems()) {
                OrderItem oi = new OrderItem();
                oi.setOrderId(orderId);
                oi.setProductId(ci.getProduct().getId());
                oi.setQuantity(ci.getQuantity());
                oi.setPriceAtTime(ci.getPrice());

                itemDAO.insert(oi);
            }

            conn.commit(); //  OK

            session.removeAttribute("cart"); // clear cart
            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); //  lỗi rollback
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new ServletException(e);

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}
