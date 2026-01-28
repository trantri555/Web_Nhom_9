package dao;

import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection conn;
    public OrderDAO() {
        try {
            this.conn = new util.DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Constructor nhận Connection để dùng Transaction trong Servlet nếu cần
    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Lấy danh sách đơn hàng (Đã bổ sung đầy đủ thuộc tính)
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setId_user(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                o.setStatus(rs.getString("paymentStatus"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm đơn hàng (Hàm cơ bản)
    public void insertOrder(Order order) {
        String sql = "INSERT INTO orders(id_user, total, status_order , status_payment, date) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getId_user());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getPaymentStatus());
            ps.setTimestamp(5, new java.sql.Timestamp(order.getOrderDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Quan trọng nhất: Insert và trả về ID để làm OrderItem
    public int insertAndReturnId(Order order) throws SQLException {
        String sql = "INSERT INTO orders(id_user, total, status_order , status_payment, date) VALUES(?,?,?,?,?)";
        // Sử dụng RETURN_GENERATED_KEYS để lấy ID tự động tăng từ Database
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getId_user());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getPaymentStatus());
            ps.setTimestamp(5, new java.sql.Timestamp(order.getOrderDate().getTime()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    // 4. Cập nhật trạng thái đơn hàng (Duyệt đơn, Hủy đơn...)
    public void updateStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status_order=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Xóa 1 đơn hàng
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6. Xóa sạch đơn hàng (Thận trọng khi dùng!)
    public void deleteAllOrders() {
        String sql = "DELETE FROM orders";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}