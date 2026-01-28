package controller;

import dao.BillDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.PaymentDAO;
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
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String action = request.getParameter("action"); // Tham số để phân biệt hành động

        // Kiểm tra giỏ hàng
        Cart cart = (Cart) (session != null ? session.getAttribute("cart") : null);
        if (cart == null || cart.getAllItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        // Phân luồng xử lý bằng action
        if ("prepare".equals(action)) {
            session.setAttribute("orderFlag", "show");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        if ("confirm".equals(action)) {
            Connection conn = null;
            try {
                // Mở kết nối và tắt AutoCommit để làm Transaction
                conn = DBContext.getConnection();
                conn.setAutoCommit(false);

                // Khởi tạo các DAO với chung 1 kết nối
                OrderDAO orderDAO = new OrderDAO(conn);
                OrderItemDAO oiDAO = new OrderItemDAO();
                BillDAO billDAO = new BillDAO(conn);
                PaymentDAO paymentDAO = new PaymentDAO(conn);
                // Lấy thông tin từ Modal
                String fullName = request.getParameter("receiverName");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String paymentMethod = request.getParameter("paymentMethod");

                // Lấy User và Voucher (nếu có)
                User user = (User) session.getAttribute("auth");
                Voucher voucher = (Voucher) session.getAttribute("voucher");

                double shippingFee = 15000;
                double discount = (voucher != null) ? voucher.getDiscountValue() : 0;
                double finalTotal = cart.getTotalPrice() + shippingFee - discount;
                if (finalTotal < 0) finalTotal = 0;
                int methodId = "COD".equals(paymentMethod) ? 1 : 2;

                //  Tạo đối tượng Order
                Order order = new Order();
                order.setId_user(user != null ? user.getId() : 0);
                order.setTotalPrice(finalTotal);
                order.setStatus("Chờ xác nhận");
                order.setPaymentStatus((methodId == 1 ? "unpaid" : "paid"));
                order.setOrderDate(new Date());

                int orderId = orderDAO.insertAndReturnId(order);

                if (orderId > 0) {
                    // Insert Order Items
                    for (CartItem item : cart.getAllItems()) {
                        OrderItem oi = new OrderItem(orderId, item.getProduct().getId(), item.getQuantity(), item.getProduct().getPrice());
                        oiDAO.insertOrderItem(oi);
                    }

                    // Insert Bill
                    Bill newBill = new Bill(0, orderId, new Date(), discount, cart.getTotalPrice() + 15000, finalTotal);
                    billDAO.insertBill(newBill);

                    // Insert Payment

                    Payment payment = new Payment(0, orderId, methodId, finalTotal, new Date(), (methodId == 1 ? "paid" : "unpaid"));
                    paymentDAO.insertPayment(payment);

                    // 4. CHỐT HẠ: Nếu mọi thứ OK thì mới lưu thật sự vào DB
                    conn.commit();
                    session.setAttribute("orderSuccess", "Đặt hàng thành công! Cảm ơn bạn đã ủng hộ.");
                    session.removeAttribute("cart");
                    session.removeAttribute("voucher");
                    session.removeAttribute("orderFlag");
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    conn.rollback(); // Lỗi thì trả lại trạng thái cũ
                    response.sendRedirect(request.getContextPath() + "/cart?error=save_failed");
                }

            } catch (Exception e) {
                try { if (conn != null) conn.rollback(); } catch (Exception ex) {} // Rollback khi có lỗi
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/cart?error=exception");
            } finally {
                try { if (conn != null) conn.close(); } catch (Exception ex) {} // Luôn đóng kết nối
            }
        }
    }
}