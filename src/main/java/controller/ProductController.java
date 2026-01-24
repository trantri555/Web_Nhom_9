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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getListProduct();

        // Đẩy danh sách sản phẩm sang trang JSP
        request.setAttribute("productList", list);

        // Chuyển hướng (Đảm bảo đường dẫn này đúng với vị trí file jsp của bạn)
        request.getRequestDispatcher("/view/user/products.jsp").forward(request, response);
    }
}
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Khởi tạo DAO
//        ProductDAO dao = new ProductDAO();
//
//        // Gọi đúng tên hàm trong DAO
//        List<Product> list = dao.getListProduct();
//
//        // Đẩy dữ liệu sang JSP
//        request.setAttribute("productList", list);
//
//        // Chuyển hướng sang file JSP (đảm bảo file này nằm trong thư mục webapp)
//        request.getRequestDispatcher("product-list.jsp").forward(request, response);
//    }
//}