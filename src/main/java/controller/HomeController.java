package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> allProducts = dao.getListProduct();

        // Lấy 4 hoặc 8 sản phẩm đầu tiên làm "Sản phẩm nổi bật"
        List<Product> featuredProducts = allProducts.stream().limit(8).collect(Collectors.toList());

        request.setAttribute("featuredList", featuredProducts);
        // Chuyển hướng sang index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}