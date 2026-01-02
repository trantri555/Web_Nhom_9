package controller;

import service.ProductService;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("query");
        ProductService service = new ProductService();
        List<Product> list = service.searchByName(keyword);

        request.setAttribute("productList", list);
        request.setAttribute("lastSearch", keyword); // Gửi lại từ khóa để hiển thị trên ô search

        // Dùng chung trang products.jsp để hiển thị kết quả
        request.getRequestDispatcher("/WEB-INF/user/products.jsp").forward(request, response);
    }
    @WebServlet(name = "FilterServlet", value = "/filter")
    public class FilterServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String supplier = request.getParameter("supplier");
            String priceStr = request.getParameter("maxPrice");
            Double maxPrice = (priceStr != null && !priceStr.isEmpty()) ? Double.parseDouble(priceStr) : null;

            ProductService service = new ProductService();
            List<Product> list = service.filterProducts(supplier, maxPrice);

            request.setAttribute("productList", list);
            request.getRequestDispatcher("/WEB-INF/user/products.jsp").forward(request, response);
        }
    }
}