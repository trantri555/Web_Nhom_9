package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

public class ProductDAO extends BaseDao {
    // Giả sử bạn đã có lớp DBContext để kết nối SQL
//    public List<Product> getAllProducts() {
//        List<Product> list = new ArrayList<>();
//        String query = "SELECT * FROM Products";
//        try {
//            Connection conn = new DBContext().getConnection(); // Bạn thay bằng cách kết nối của mình
//            PreparedStatement ps = conn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    static Map<Integer, Product> data = new HashMap<~>();

    static {
        data.put(1, new Product(1, "Nước Ép Cam", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(2, new Product(2, "Nước ép Mía Dâu Tằm", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(3, new Product(3, "Nước ép Dứa - cà rốt - cam", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(4, new Product(4, "Nước Xoài", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(5, new Product(5, "Nước ổi", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(6, new Product(6, "Nước chanh dây", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(7, new Product(7, "Nước Dứa - Sơ ri - Chanh dây", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(8, new Product(8, "Nước dứa tươi", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(9, new Product(9, "Nước ép táo", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(10, new Product(10, "Nước ép Tropical", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(11, new Product(11, "Nước Mãng cầu xiêm Nectar", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(12, new Product(12, "Nước ép cà chua", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(13, new Product(13, "Nước Mãng cầu xiêm nectar - 1L", 79.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(14, new Product(14, "Nước cam tươi - 1L", 108.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(15, new Product(15, "Nước chanh dây - 1L", 73.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(16, new Product(16, "Nước cốt chanh tươi - 1L", 131.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(17, new Product(17, "Nước Dứa - Sơ ri - Chanh dây - 1L", 73.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(18, new Product(18, "Nước dứa tươi - 1L", 73.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(19, new Product(19, "Nước ép Dứa - cà rốt - cam - 1L", 74.000, 1, "Letuan fruit", 10, "linkanh", ""));
        data.put(20, new Product(20, "Nước Mía - Sơ ri - Tắc", 45.000, 230, "Letuan fruit", 10, "linkanh", ""));
        data.put(21, new Product(21, "Nước Mía Dâu Tằm - 130ml", 35.000, 130, "Letuan fruit", 10, "linkanh", ""));
        data.put(22, new Product(22, "Thùng nước ép Detox & Vitamin", 232.000, 0, "Letuan fruit", 10, "linkanh", ""));
        data.put(23, new Product(23, "LE FRUIT SPORT MÍA TẮC TÚI 130ML", 35.000, 130, "Letuan fruit", 10, "linkanh", ""));
        data.put(24, new Product(24, "Nước Mãng cầu xiêm nectar - 5L", 369.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(25, new Product(25, "Nước cam nectar - 5L", 487.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(26, new Product(26, "Nước chanh dây - 5L", 318.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(27, new Product(27, "Nước Dứa - Sơ ri - Chanh dây - 5L", 335.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(28, new Product(28, "Nước dứa nectar - 5L", 289.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(29, new Product(29, "Nước dứa tươi - 5L", 312.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(30, new Product(30, "Nước Dứa - cà rốt - cam - 5L", 335.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(31, new Product(31, "Nước ép Mía Dâu Tằm - 5L", 318.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(32, new Product(32, "Nước ép Tropical - 5L", 335.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(33, new Product(33, "Nước ổi - 5L", 289.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(34, new Product(34, "Nước ổi Le Fruit Kids - 130ml", 35.000, 130, "Letuan fruit", 10, "linkanh", ""));
        data.put(35, new Product(35, "Nước táo - 5L", 369.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(36, new Product(36, "Nước Mía - Sơ ri - Tắc - 5L", 340.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(37, new Product(37, "Nước xoài - 5L", 289.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(38, new Product(38, "Nước táo Le Fruit Kids - 130ml", 35.000, 130, "Letuan fruit", 10, "linkanh", ""));
        data.put(39, new Product(39, "Nước Củ dền - Ổi - Thanh long - 5L", 335.000, 5, "Letuan fruit", 10, "linkanh", ""));
        data.put(40, new Product(40, "Nước cam tươi - 5L", 489.000, 5, "Letuan fruit", 10, "linkanh", ""));

    }

    public List<Product> getListProduct() {
        return get().withHandle(h -> h.createQuery("select * from product").mapToBean(Product.class).list());
    }

    public Product getProductId(int id) {
        return get().withHandle(h -> {h.createQuery("select * from product where id = :id").bind("id",id).mapToBean(Product.class).first();
        });
    }

    public void insert(List<Product> list) {
        get().useHandle(h -> {
            PreparedBatch pb = h, preparedBatch
            ("insert into products values (:id,:name,:price,:volume,:supplier_name,:quantity,:img,:desciption");
            list.forEach(l -> {
                pb.bindBean(l).add();
            });
            pb.execute();
        });
    }
}

public static void main(String[] args) {
    ProductDao pd = new ProductDao();
    pd.insert(pd.getListProduct());
}
