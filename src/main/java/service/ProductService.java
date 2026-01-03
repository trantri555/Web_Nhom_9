package service;

import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductDAO pdao = new ProductDAO();
    private final ProductDAO productDAO = new ProductDAO();
    //  Lấy danh sách sản phẩm
    public List<Product> getListProduct() {
        List<Product> list = pdao.getAll();

        for (Product p : list) {
            handleImage(p);
        }
        return list;
    }

    //  Lấy sản phẩm theo ID (dùng chung)
    public Product getProductById(int id) {
        Product p = pdao.findById(id);
        if (p != null) {
            handleImage(p);
        }
        return p;
    }

    //  Giữ nguyên logic chi tiết sản phẩm
    public Product getProductDetail(int id) {
        Product p = pdao.findById(id);

        if (p != null) {
            handleImage(p);
        }
        return p;
    }

    // ⃣Tìm kiếm theo tên
    public List<Product> searchByName(String keyword) {
        List<Product> all = getListProduct();
        List<Product> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return all;
        }

        String searchKey = keyword.toLowerCase().trim();
        for (Product p : all) {
            if (p.getName().toLowerCase().contains(searchKey)) {
                result.add(p);
            }
        }
        return result;
    }

    // Lọc sản phẩm theo NCC + giá
    public List<Product> filterProducts(String supplier, Double maxPrice) {
        List<Product> all = getListProduct();
        List<Product> result = new ArrayList<>();

        for (Product p : all) {
            boolean matchSupplier =
                    (supplier == null || supplier.isEmpty()
                            || p.getSupplier_name().equalsIgnoreCase(supplier));

            boolean matchPrice =
                    (maxPrice == null || p.getPrice() <= maxPrice);

            if (matchSupplier && matchPrice) {
                result.add(p);
            }
        }
        return result;
    }

    // Thêm sản phẩm (CHUYỂN QUA DAO)
    public void add(Product p) {
        pdao.insert(p);
    }

    //  Xóa sản phẩm (CHUYỂN QUA DAO)
    public void delete(int id) {
        pdao.delete(id);
    }
    private void handleImage(Product p) {
        if (p.getImg() == null || p.getImg().isEmpty() || p.getImg().equals("linkanh")) {
            p.setImgage("images/default-product.png");
        }
    }
    public List<Product> getTop3BestSeller() {
        return productDAO.getTop3BestSeller();
    }
}
