package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi DAO để lấy dữ liệu
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAllProducts();

        // Đẩy dữ liệu lên request để JSP hứng
        request.setAttribute("productList", list);

        // Chuyển hướng sang trang hiển thị (JSP)
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }
}