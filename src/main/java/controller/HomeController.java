package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();

//        // Lấy 4 sản phẩm nổi bật
//        List<Product> featured = dao.getTopFeatured(4);
//        request.setAttribute("featuredList", featured);
//
//        // Chuyển hướng tới index.jsp
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}