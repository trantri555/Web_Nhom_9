package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();

        // 1. Lấy tham số lọc từ URL
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        String volumeStr = request.getParameter("volume");
        String supplier = request.getParameter("supplier");
        String sortBy = request.getParameter("sort");

        // --- PHÂN TRANG ---
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        int pageSize = 12;
        int offset = (page - 1) * pageSize;

        Double minPrice = null;
        Double maxPrice = null;

        try {
            if (minPriceStr != null && !minPriceStr.isEmpty())
                minPrice = Double.parseDouble(minPriceStr);
            if (maxPriceStr != null && !maxPriceStr.isEmpty())
                maxPrice = Double.parseDouble(maxPriceStr);
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }

        // 2. Gọi DAO để lấy danh sách đã lọc (có phân trang)
        List<Product> list = dao.getFilteredProducts(minPrice, maxPrice, volumeStr, supplier, sortBy, offset, pageSize);
        int totalProducts = dao.getTotalFilteredProducts(minPrice, maxPrice, volumeStr, supplier);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // 3. Lấy danh sách volume và supplier để hiển thị checkbox/select
        List<Integer> volumeList = dao.getAllVolumes();
        List<String> supplierList = dao.getAllSuppliers();

        // 4. Đẩy dữ liệu sang JSP
        request.setAttribute("productList", list);
        request.setAttribute("volumeList", volumeList);
        request.setAttribute("supplierList", supplierList);

        // Phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Giữ lại giá trị filter để điền lại vào form
        request.setAttribute("currentMinPrice", minPriceStr);
        request.setAttribute("currentMaxPrice", maxPriceStr);
        request.setAttribute("currentVolume", volumeStr);
        request.setAttribute("currentSupplier", supplier);
        request.setAttribute("currentSort", sortBy);

        // Chuyển hướng
        request.getRequestDispatcher("/view/user/products.jsp").forward(request, response);
    }
}
// @Override
// protected void doGet(HttpServletRequest request, HttpServletResponse
// response) throws ServletException, IOException {
// // Khởi tạo DAO
// ProductDAO dao = new ProductDAO();
//
// // Gọi đúng tên hàm trong DAO
// List<Product> list = dao.getListProduct();
//
// // Đẩy dữ liệu sang JSP
// request.setAttribute("productList", list);
//
// // Chuyển hướng sang file JSP (đảm bảo file này nằm trong thư mục webapp)
// request.getRequestDispatcher("product-list.jsp").forward(request, response);
// }
// }