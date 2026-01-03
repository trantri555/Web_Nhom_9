import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/product-detail")
public class ProductDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy ID sản phẩm từ URL (ví dụ: product-detail?id=5)
        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            ProductDAO dao = new ProductDAO();

            // 2. Tìm sản phẩm theo ID
            Product p = dao.getProductById(id);

            if (p != null) {
                // 3. Đẩy đối tượng sản phẩm sang trang JSP
                request.setAttribute("product", p);
                request.getRequestDispatcher("product-detail.jsp").forward(request, response);
                return;
            }
        }
        // Nếu không thấy ID hoặc sản phẩm, về trang 404 hoặc danh sách
        response.sendRedirect("products");
    }
}

