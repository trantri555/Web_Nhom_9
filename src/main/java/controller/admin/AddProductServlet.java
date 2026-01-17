package controller.admin;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/add-product")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //1. Lấy dữ liệu form
        String name = request.getParameter("name");
        String supplier = request.getParameter("supplier_name");
        String description = request.getParameter("description");

        double price;
        int volume, quantity;

        try {
            price = Double.parseDouble(request.getParameter("price"));
            volume = Integer.parseInt(request.getParameter("volume"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            response.sendRedirect("admin-product.jsp?error=invalid_number");
            return;
        }

        //2. Upload ảnh
        Part imagePart = request.getPart("image");
        if (imagePart == null || imagePart.getSize() == 0) {
            response.sendRedirect("admin-product.jsp?error=no_image");
            return;
        }

        String fileName = Paths.get(imagePart.getSubmittedFileName())
                .getFileName().toString();

        String uploadPath = getServletContext().getRealPath("/uploads/products");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String savedFileName = System.currentTimeMillis() + "_" + fileName;
        imagePart.write(uploadPath + File.separator + savedFileName);

        //3. Set Product
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        p.setImgage(savedFileName);          // ⚠️ xem phần sửa model
        p.setDescription(description);
        p.setSupplier_name(supplier);
        p.setVolume(volume);

        //4. Insert DB
        ProductDAO dao = new ProductDAO();
        dao.insert(p);

        response.sendRedirect("admin-product.jsp?success=add");
    }
}
