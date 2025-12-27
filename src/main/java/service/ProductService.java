package service;

import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO pdao = new ProductDAO();

    public List<Product> getListProduct() {
        // 1. Lấy danh sách từ DAO (dữ liệu ảo)
        List<Product> list = pdao.getListProduct();

        // 2. Duyệt qua danh sách để xử lý link ảnh nếu cần
        for (Product p : list) {
            // Nếu biến img trong DAO đang là "linkanh", bạn có thể gán lại
            // thành đường dẫn thực tế trong thư mục web của bạn
            if (p.getImg() == null || p.getImg().equals("linkanh")) {
                p.setImg("images/product-default.jpg"); // Gán ảnh mặc định nếu chưa có
            }
        }
        return list;
    }
    public Product getProductById(int id) {
        return pdao.findById(id);
    }
    public Product getProductDetail(int id) {
        // 1. Lấy sản phẩm trực tiếp từ ProductDAO thông qua ID
        // Lưu ý: Đảm bảo trong ProductDAO bạn đã đổi tên hàm thành findById hoặc getProductId cho khớp
        Product p = pdao.findById(id);

        // 2. Vì bạn chỉ có 1 hình ảnh đã nằm sẵn trong Model Product, 
        // không cần gọi pidao.getProductImages(id) nữa.
        if (p != null) {
            // Nếu bạn muốn xử lý logic đường dẫn ảnh (ví dụ thêm thư mục gốc)
            // p.setImg("assets/images/" + p.getImg()); 

            // Hoặc đơn giản là kiểm tra nếu ảnh bị trống thì gán ảnh mặc định
            if (p.getImg() == null || p.getImg().isEmpty() || p.getImg().equals("linkanh")) {
                p.setImg("images/default-product.png");
            }
        }

        return p;
    }
    //chức năng tìm & lọc sản phẩm
    public List<Product> searchByName(String keyword) {
        List<Product> all = getListProduct(); // Lấy list đã qua xử lý ảnh
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
    // 3. Logic Lọc sản phẩm (BỎ VÀO ĐÂY)
    public List<Product> filterProducts(String supplier, Double maxPrice) {
        List<Product> all = getListProduct();
        List<Product> result = new ArrayList<>();

        for (Product p : all) {
            // Kiểm tra nhà cung cấp (nếu supplier null thì mặc định khớp)
            boolean matchSupplier = (supplier == null || supplier.isEmpty() || p.getSupplier_name().equals(supplier));
            // Kiểm tra giá tiền
            boolean matchPrice = (maxPrice == null || p.getPrice() <= maxPrice);

            if (matchSupplier && matchPrice) {
                result.add(p);
            }
        }
        return result;
    }

    // Các hàm khác như getProductDetail... giữ nguyên
    private void handleImage(Product p) {
        if (p.getImg() == null || p.getImg().equals("linkanh")) {
            p.setImg("images/product-default.jpg");
        }
    }

}
