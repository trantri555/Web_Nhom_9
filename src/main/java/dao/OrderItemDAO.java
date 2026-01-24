package dao;

import model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {

    private Connection conn;

    public OrderItemDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(OrderItem item) {
        String sql = """
            INSERT INTO order_items(order_id, product_id, quantity, price_at_time)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPriceAtTime());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
