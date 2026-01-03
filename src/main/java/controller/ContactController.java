package controller;

import dao.ContactDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/contact")
public class ContactController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            new ContactDAO().insert(
                    req.getParameter("full_name"),
                    req.getParameter("email"),
                    req.getParameter("phone"),
                    req.getParameter("subject"),
                    req.getParameter("message"),
                    null
            );

            resp.sendRedirect("contact-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/view/user/contact.jsp")
                .forward(req, resp);
    }
}
