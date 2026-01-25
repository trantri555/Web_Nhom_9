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
                                                     pi.image_URL AS img, -- Lấy link ảnh từ bảng product_images
                                                     p.description
                                                 FROM products p
                                                 LEFT JOIN product_images pi ON p.image = pi.id
                                                 GROUP BY p.id
                """;

        return jdbi.withHandle(handle -> handle.createQuery(sql)
                .mapToBean(Product.class)
                .list());
    }

    public List<Product> getListProduct4() {
        return getTopFeatured(4);
    }

    public List<Product> getListProduct() {
        return getAll();
    }

    // Lấy sản phẩm theo ID
    public Product findById(int id) {
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
                    WHERE p.id = :id
                """;

        return get().withHandle(handle -> handle.createQuery(sql)
                .bind("id", id)
                .mapToBean(Product.class)
                .findOne()
                .orElse(null));
    }

    // Thêm sản phẩm
    public void insert(Product p) {
        String sql = """
                    INSERT INTO products
                    (product_name, price, volume, supplier_name, quantity, image, description)
                    VALUES (:name, :price, :volume, :supplier, :quantity, :img, :description)
                """;

        get().useHandle(handle -> handle.createUpdate(sql)
                .bindBean(p)
                .execute());
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
        return jdbi.withHandle(handle -> handle.createQuery(finalSql)
                .mapToBean(Product.class)
                .list());
    }

    // // Xóa sản phẩm
    // public void delete(int id) {
    // get().useHandle(handle ->
    // handle.createUpdate("DELETE FROM Product WHERE id_product = :id")
    // .bind("id", id)
    // .execute()
    // );
    // }
    //
    // // Cập nhật sản phẩm
    // public void update(Product p) {
    // String sql = """
    // UPDATE Product SET
    // product_name = :name,
    // price = :price,
    // volume = :volume,
    // supplier_name= :supplier,
    // quantity = :quantity,
    // image = :img,
    // description = :description
    // WHERE id_product = :id
    // """;
    //
    // get().useHandle(handle ->
    // handle.createUpdate(sql)
    // .bindBean(p)
    // .execute()
    // );
    // }
    public List<Product> getTop3BestSeller() {

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
                ORDER BY RAND()
                LIMIT 8
                """;

        return get().withHandle(h -> h.createQuery(sql)
                .mapToBean(Product.class)
                .list());
    }

    // // Sản phẩm liên quan (Random 8 sản phẩm khác)
    public List<Product> getRelatedProducts(int currentId) {
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
                    WHERE p.id != :id
                    ORDER BY RAND()
                    LIMIT 8
                """;

        return get().withHandle(handle -> handle.createQuery(sql)
                .bind("id", currentId)
                .mapToBean(Product.class)
                .list());
    }

    // Lấy danh sách thể tích duy nhất để hiển thị bộ lọc
    public List<Integer> getAllVolumes() {
        String sql = "SELECT DISTINCT volume FROM products ORDER BY volume ASC";
        return get().withHandle(handle -> handle.createQuery(sql)
                .mapTo(Integer.class)
                .list());
    }

    // Lấy danh sách nhà cung cấp duy nhất
    public List<String> getAllSuppliers() {
        String sql = "SELECT DISTINCT supplier_name FROM products WHERE supplier_name IS NOT NULL ORDER BY supplier_name ASC";
        return get().withHandle(handle -> handle.createQuery(sql)
                .mapTo(String.class)
                .list());
    }

    // Lọc sản phẩm
    public List<Product> getFilteredProducts(Double minPrice, Double maxPrice, String volumeStr, String supplier,
                                             String sortBy) {
        StringBuilder sql = new StringBuilder("""
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
                    WHERE 1=1
                """);

        if (minPrice != null) {
            sql.append(" AND p.price >= :minPrice");
        }
        if (maxPrice != null) {
            sql.append(" AND p.price <= :maxPrice");
        }
        Integer volume = null;
        if (volumeStr != null && !volumeStr.isEmpty()) {
            try {
                volume = Integer.parseInt(volumeStr);
                sql.append(" AND p.volume = :volume");
            } catch (NumberFormatException e) {
                // Ignore invalid volume
            }
        }

        if (supplier != null && !supplier.isEmpty()) {
            sql.append(" AND p.supplier_name = :supplier");
        }

        // Sorting (Logic cũ)
        if ("priceAsc".equals(sortBy)) {
            sql.append(" ORDER BY p.price ASC");
        } else if ("priceDesc".equals(sortBy)) {
            sql.append(" ORDER BY p.price DESC");
        } else if ("nameAsc".equals(sortBy)) {
            sql.append(" ORDER BY p.product_name ASC");
        }

        Integer finalVolume = volume;
        return get().withHandle(handle -> {
            var query = handle.createQuery(sql.toString());
            if (minPrice != null)
                query.bind("minPrice", minPrice);
            if (maxPrice != null)
                query.bind("maxPrice", maxPrice);
            if (finalVolume != null)
                query.bind("volume", finalVolume);
            if (supplier != null && !supplier.isEmpty())
                query.bind("supplier", supplier);
            return query.mapToBean(Product.class).list();
        });
    }

    // Tìm kiếm theo tên
    public List<Product> searchByName(String keyword) {
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
                    WHERE p.product_name LIKE :keyword
                """;
        return get().withHandle(handle -> handle.createQuery(sql)
                .bind("keyword", "%" + keyword + "%")
                .mapToBean(Product.class)
                .list());
    }

    // Lọc theo NCC và Giá tối đa
    public List<Product> filterProducts(String supplier, Double maxPrice) {
        StringBuilder sql = new StringBuilder("""
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
                    WHERE 1=1
                """);

        if (supplier != null && !supplier.trim().isEmpty()) {
            sql.append(" AND p.supplier_name = :supplier");
        }
        if (maxPrice != null) {
            sql.append(" AND p.price <= :maxPrice");
        }

        return get().withHandle(handle -> {
            var query = handle.createQuery(sql.toString());
            if (supplier != null && !supplier.trim().isEmpty()) {
                query.bind("supplier", supplier);
            }
            if (maxPrice != null) {
                query.bind("maxPrice", maxPrice);
            }
            return query.mapToBean(Product.class).list();
        });
    }

}
