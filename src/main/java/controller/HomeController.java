package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();

        // 1. Lấy dữ liệu sản phẩm nổi bật
        List<Product> featured = dao.getListProduct4();

        // 2. Gán vào request với tên "featuredList" khớp với index.jsp
        request.setAttribute("featuredList", featured);

        // 3. Forward sang trang index.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}