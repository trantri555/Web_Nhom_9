package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home", ""})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> featured = dao.getTopFeatured(4);

        // KIỂM TRA DEBUG: In ra console để xem có dữ liệu không
        System.out.println("DEBUG: So luong SP noi bat: " + (featured != null ? featured.size() : "NULL"));

        request.setAttribute("featuredList", featured);

        // NẾU index.jsp nằm ở webapp/ thì dùng "/index.jsp"
        // NẾU index.jsp nằm ở webapp/view/user/ thì phải dùng "/view/user/index.jsp"
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}