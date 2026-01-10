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
        List<Product> list = new ArrayList<>(); // Tạo danh sách rỗng mặc định
        try {
            ProductDAO dao = new ProductDAO();
            list = dao.getListProduct(); // Lấy dữ liệu
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra log để bạn sửa, nhưng web không chết
            System.out.println("Lỗi kết nối DB, đang hiển thị trang trống dữ liệu.");
        }

        request.setAttribute("productList", list);
        // Luôn luôn chạy dòng này để lên giao diện
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}