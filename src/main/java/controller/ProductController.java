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
            request.setAttribute("productList", dao.getListProduct());

            request.getRequestDispatcher("/WEB-INF/view/user/products.jsp")
                    .forward(request, response);
        }
    }



