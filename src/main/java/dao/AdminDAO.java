package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection conn;

    public AdminDAO(Connection conn) {
        this.conn = conn;
    }

    public int countOrdersToday() {
        String sql = "SELECT COUNT(*) FROM orders WHERE DATE(date) = CURDATE()";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countOrdersWeek() {
        String sql = "SELECT COUNT(*) FROM orders WHERE YEARWEEK(date, 1) = YEARWEEK(CURDATE(), 1)";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countOrdersMonth() {
        String sql = "SELECT COUNT(*) FROM orders WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE())";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public java.util.Map<java.sql.Date, Double> getRevenueLast7Days() {
        java.util.Map<java.sql.Date, Double> map = new java.util.LinkedHashMap<>();
        // Khởi tạo 7 ngày gần nhất với giá trị 0
        long millisPerDay = 24 * 60 * 60 * 1000L;
        long today = System.currentTimeMillis();
        for (int i = 6; i >= 0; i--) {
            map.put(new java.sql.Date(today - i * millisPerDay), 0.0);
        }

        String sql = "SELECT DATE(date) as cur_date, SUM(total) as revenue " +
                "FROM orders " +
                "WHERE date >= DATE(NOW()) - INTERVAL 6 DAY " +
                "GROUP BY DATE(date) " +
                "ORDER BY cur_date ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getDate("cur_date"), rs.getDouble("revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<model.TopProductDTO> getTopSellingProducts(int limit) {
        List<model.TopProductDTO> list = new ArrayList<>();
        // Table name appears to be 'ordenitems' based on user screenshot
        String sql = "SELECT p.product_name AS name, SUM(oi.quantity) as total_sold " +
                "FROM orderitems oi " +
                "JOIN products p ON oi.id_product = p.id " +
                "GROUP BY p.id, p.product_name " +
                "ORDER BY total_sold DESC " +
                "LIMIT ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new model.TopProductDTO(
                            rs.getString("name"),
                            rs.getInt("total_sold")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
