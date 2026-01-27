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
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu form
        String name = request.getParameter("name");
        String supplier = request.getParameter("supplier_name");
        String description = request.getParameter("description");

        // Handle optional input gracefully
        double price = 0;
        int volume = 0;
        int quantity = 0;

        try {
            String priceStr = request.getParameter("price");
            if (priceStr != null && !priceStr.isEmpty())
                price = Double.parseDouble(priceStr);

            String volumeStr = request.getParameter("volume");
            if (volumeStr != null && !volumeStr.isEmpty())
                volume = Integer.parseInt(volumeStr);

            String quantityStr = request.getParameter("quantity");
            if (quantityStr != null && !quantityStr.isEmpty())
                quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("admin-product.jsp?error=invalid_number");
            return;
        }

        // 2. Prepare upload
        // Change path to images/product to match frontend
        String uploadPath = getServletContext().getRealPath("/images/product");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdirs();

        java.util.List<String> savedFileNames = new java.util.ArrayList<>();

        // Loop through all parts to find multiple "images"
        for (Part part : request.getParts()) {
            if ("images".equals(part.getName()) && part.getSize() > 0) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String savedFileName = System.currentTimeMillis() + "_" + fileName;
                part.write(uploadPath + File.separator + savedFileName);
                savedFileNames.add(savedFileName);
            }
        }

        if (savedFileNames.isEmpty()) {
            // Fallback for singular "image" if someone didn't update JSP
            Part part = request.getPart("image");
            if (part != null && part.getSize() > 0) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String savedFileName = System.currentTimeMillis() + "_" + fileName;
                part.write(uploadPath + File.separator + savedFileName);
                savedFileNames.add(savedFileName);
            }
        }

        if (savedFileNames.isEmpty()) {
            response.sendRedirect("admin-product.jsp?error=no_image");
            return;
        }

        // 3. Set Product
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        // Use the first image as the main thumbnail
        p.setImg(savedFileNames.get(0));
        p.setDescription(description);
        p.setSupplier_name(supplier);
        p.setVolume(volume);

        // 4. Insert DB
        ProductDAO dao = new ProductDAO();
        int newProductId = dao.insert(p);

        // 5. Insert extra images
        for (String img : savedFileNames) {
            dao.insertImage(newProductId, img);
        }

        response.sendRedirect(request.getContextPath() + "/admin/products?success=add");
    }
}
