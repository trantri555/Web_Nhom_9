package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Product;
import service.ProductService;

import java.io.IOException;

@WebServlet("/admin/products")
public class ProductServlet extends HttpServlet {

    private final ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("products", service.getListProduct());
        req.getRequestDispatcher("/admin-products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            Product p = new Product();
            p.setName(req.getParameter("name"));
            p.setPrice(Double.parseDouble(req.getParameter("price")));
            p.setCategory(req.getParameter("category"));
            p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            p.setImgage("placeholder.png");

            service.add(p);
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            service.delete(id);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
