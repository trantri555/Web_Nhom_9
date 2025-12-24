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
        data.put(1, new Product(1, "Nước Ép Cam", 45.000, 230, "Letuan fruit", 3, "linkanh", ""));
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