package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ListProductController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService ps = new ProductService();
        List<Product> list = ps.getListProduct();
        request.setAttribute("list", list);
        request.getRequestDispatcher("D:\\Web_Nhom_9\\src\\main\\webapp\\WEB-INF\\user\\products.jsp").forward(request,response);


    }

    public void destroy() {
    }

}
