package dao;

import model.Product;
import java.util.List;

public class ProductDAO extends BaseDao {

    // Lấy tất cả sản phẩm (cho trang Products)
    public List<Product> getAll() {
        String sql = """
            SELECT 
                p.id AS id, 
                p.product_name AS name, 
                p.price, 
                p.volume, 
                p.supplier_name, 
                p.quantity, 
                pi.image_URL AS img, 
                p.description 
            FROM products p
            LEFT JOIN product_images pi ON p.image = pi.id
            WHERE p.status = 'active'
            GROUP BY p.id, pi.image_URL
        """;
        return get().withHandle(handle ->
                handle.createQuery(sql).mapToBean(Product.class).list()
        );
    }

    // Lấy TOP sản phẩm nổi bật dựa trên số lượng bán (cho trang Home)
    public List<Product> getTopFeatured(int limit) {
        // JOIN thêm bảng product_images để lấy pi.image_URL gán vào img
        String sql = """
            SELECT 
                p.id AS id,
                p.product_name AS name, 
                p.price, 
                pi.image_URL AS img, 
                SUM(oi.quantity) AS total_sold
            FROM products p
            LEFT JOIN product_images pi ON p.image = pi.id
            LEFT JOIN orderitems oi ON p.id = oi.id_product
            WHERE p.status = 'active'
            GROUP BY p.id, p.product_name, p.price, pi.image_URL
            ORDER BY total_sold DESC
            LIMIT :limit
        """;
        return get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("limit", limit)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getListProduct4() {
        return getTopFeatured(4);
    }

    public List<Product> getListProduct() {
        return getAll();
    }
    //  Lấy sản phẩm theo ID
    public Product findById(int id) {
        String sql = """
            SELECT
                id   AS id,
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

    // fill sản phẩm lọc ra sản phẩm
    public List<Product> getProducts(String sortBy) {
        // Câu query cơ bản
        String sql = """
        SELECT 
            p.id AS id, 
            p.product_name AS name, 
            p.price, 
            p.volume, 
            p.supplier_name, 
            p.quantity, 
            pi.image_URL AS img, 
            p.description 
        FROM products p
        LEFT JOIN product_images pi ON p.id = pi.id_product
        GROUP BY p.id
    """;

        // Thêm logic sắp xếp dựa trên tham số truyền vào
        if ("priceAsc".equals(sortBy)) {
            sql += " ORDER BY p.price ASC";
        } else if ("priceDesc".equals(sortBy)) {
            sql += " ORDER BY p.price DESC";
        } else if ("nameAsc".equals(sortBy)) {
            sql += " ORDER BY p.product_name ASC";
        }

        String finalSql = sql;
        return jdbi.withHandle(handle ->
                handle.createQuery(finalSql)
                        .mapToBean(Product.class)
                        .list()
        );
    }
}
