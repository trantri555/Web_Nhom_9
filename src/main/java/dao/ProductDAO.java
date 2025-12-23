package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {
    // Giả sử bạn đã có lớp DBContext để kết nối SQL
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try {
            Connection conn = new DBContext().getConnection(); // Bạn thay bằng cách kết nối của mình
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}