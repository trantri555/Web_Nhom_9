package dao;

import model.Product;

import java.util.List;

public class ProductDAO extends BaseDao {

    // 1. Lấy sản phẩm nổi bật (Sửa: OrderItem -> OrderItems)
    public List<Product> getTopFeatured(int limit) {
        String sql = """
                    SELECT 
                        p.id_product   AS id, 
                        p.product_name AS name, 
                        p.price, 
                        p.image        AS img,
                        SUM(oi.quantity) AS total_sold
                    FROM Product p
                    LEFT JOIN OrderItems oi ON p.id_product = oi.id_product
                    GROUP BY p.id_product
                    ORDER BY total_sold DESC
                    LIMIT ?
                """;
        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, limit)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // 2. Lấy danh sách sản phẩm (Dùng cho trang products.jsp)
    public List<Product> getListProduct() {
        String sql = """
                    SELECT 
                        id_product   AS id,
                        product_name AS name,
                        price,
                        volume,
                        supplier_name AS supplier,
                        quantity,
                        image         AS img,
                        description
                    FROM Product
                    WHERE status = 'active'
                """;
        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getAll() {
        String sql = """
                SELECT
                id_product AS id,
                product_name AS name,
                price,
                volume,
                supplier_name,
                quantity,
                image AS img,
                description
                FROM Product
                """;
        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // 3. Lấy Top 3 bán chạy (Sửa: Order_Detail -> OrderItems, Orders -> Order)
    public List<Product> getTop3BestSeller() {
        String sql = """
                    SELECT 
                        p.id_product   AS id,
                        p.product_name AS name,
                        p.image        AS img,
                        SUM(oi.quantity) AS sold
                    FROM OrderItems oi
                    JOIN Product p ON oi.id_product = p.id_product
                    JOIN `Order` o ON oi.id_order = o.id_order
                    WHERE o.status_order = 'completed'
                    GROUP BY p.id_product, p.product_name, p.image
                    ORDER BY sold DESC
                    LIMIT 3
                """;
        return get().withHandle(h ->
                h.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // Các hàm Insert/Update/Delete giữ nguyên vì chúng chỉ gọi tới bảng Product (không đổi tên)
    public void insert(Product p) {
        String sql = """
                    INSERT INTO Product
                    (product_name, price, volume, supplier_name, quantity, image, description)
                    VALUES (:name, :price, :volume, :supplier, :quantity, :img, :description)
                """;
        get().useHandle(handle ->
                handle.createUpdate(sql).bindBean(p).execute()
        );
    }

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
                handle.createUpdate(sql).bindBean(p).execute()
        );
    }

    public void delete(int id) {
        get().useHandle(handle ->
                handle.createUpdate("DELETE FROM Product WHERE id_product = :id")
                        .bind("id", id).execute()
        );
    }

    public Product findById(int id) {
        String sql = """
                    SELECT 
                        id_product   AS id,
                        product_name AS name,
                        price,
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
}