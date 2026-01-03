package dao;

import model.Product;
import java.util.List;

public class ProductDAO extends BaseDao {

    //  Lấy toàn bộ sản phẩm
    public List<Product> getAll() {
        String sql = """
            SELECT 
                id_product   AS id,
                product_name AS name,
                price,
                volume,
                supplier_name,
                quantity,
                image         AS img,
                description
            FROM Product
        """;

        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }
    // Lấy danh sách sản phẩm (alias cho getAll)
    public List<Product> getListProduct() {
        return getAll();
    }


    //  Lấy sản phẩm theo ID
    public Product findById(int id) {
        String sql = """
            SELECT 
                id_product   AS id,
                product_name AS name,
                price,
                volume,
                supplier_name,
                quantity,
                image         AS img,
                description
            FROM Product
            WHERE id_product = :id
        """;

        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null)
        );
    }

    //  Thêm sản phẩm
    public void insert(Product p) {
        String sql = """
            INSERT INTO Product
            (product_name, price, volume, supplier_name, quantity, image, description)
            VALUES (:name, :price, :volume, :supplier, :quantity, :img, :description)
        """;

        get().useHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(p)
                        .execute()
        );
    }

    // Xóa sản phẩm
    public void delete(int id) {
        get().useHandle(handle ->
                handle.createUpdate("DELETE FROM Product WHERE id_product = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    // Cập nhật sản phẩm
    public void update(Product p) {
        String sql = """
            UPDATE Product SET
                product_name = :name,
                price        = :price,
                volume       = :volume,
                supplier_name= :supplier,
                quantity     = :quantity,
                image        = :img,
                description  = :description
            WHERE id_product = :id
        """;

        get().useHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(p)
                        .execute()
        );
    }
    public List<Product> getTop3BestSeller() {

        String sql = """
            SELECT 
                p.id_product   AS id,
                p.product_name AS name,
                SUM(od.quantity) AS sold
            FROM Order_Detail od
            JOIN Product p ON od.id_product = p.id_product
            JOIN Orders o ON od.id_order = o.id_order
            WHERE o.status = 'COMPLETED'
            GROUP BY p.id_product, p.product_name
            ORDER BY sold DESC
            LIMIT 3
        """;

        return get().withHandle(h ->
                h.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }
}
