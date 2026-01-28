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

    // Tổng doanh thu toàn bộ thời gian
    public double getTotalRevenue() {
        String sql = "SELECT SUM(total) FROM orders";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tổng số đơn hàng toàn bộ thời gian
    public int getTotalOrders() {
        String sql = "SELECT COUNT(*) FROM orders";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
        // Prepare date formatter and calendar
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // Calculate cleanup cutoff (6 days ago at 00:00:00)
        // to pass to SQL, ensuring timezone consistency
        long todayMillis = System.currentTimeMillis();
        cal.setTimeInMillis(todayMillis);
        cal.add(java.util.Calendar.DAY_OF_YEAR, -6);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        java.sql.Timestamp sixDaysAgo = new java.sql.Timestamp(cal.getTimeInMillis());

        // 1. Fetch DB data
        java.util.Map<String, Double> dbMap = new java.util.HashMap<>();
        String sql = "SELECT DATE(date) as cur_date, SUM(total) as revenue " +
                "FROM orders " +
                "WHERE date >= ? " +
                "GROUP BY DATE(date) ";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, sixDaysAgo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Get Date object then format it, ensuring match with loop key
                    java.sql.Date dbDate = rs.getDate("cur_date");
                    if (dbDate != null) {
                        String dateStr = sdf.format(dbDate);
                        double revenue = rs.getDouble("revenue");
                        dbMap.put(dateStr, revenue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Build final 7-day map in order
        java.util.Map<java.sql.Date, Double> finalMap = new java.util.LinkedHashMap<>();

        for (int i = 6; i >= 0; i--) {
            cal.setTimeInMillis(todayMillis);
            cal.add(java.util.Calendar.DAY_OF_YEAR, -i);

            // Same formatter used here
            String keyDate = sdf.format(cal.getTime());

            Double val = dbMap.getOrDefault(keyDate, 0.0);

            // Normalize key for Map
            cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
            cal.set(java.util.Calendar.MINUTE, 0);
            cal.set(java.util.Calendar.SECOND, 0);
            cal.set(java.util.Calendar.MILLISECOND, 0);

            finalMap.put(new java.sql.Date(cal.getTimeInMillis()), val);
        }

        return finalMap;
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
