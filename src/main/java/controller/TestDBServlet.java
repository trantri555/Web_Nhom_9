package controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBContext;

import java.io.IOException;
import java.sql.Connection;


@WebServlet("/test-db")
    public class TestDBServlet extends HttpServlet {

        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {

            try {
                Connection conn = DBContext.getConnection();
                resp.getWriter().println("✅ CONNECT DB SUCCESS");
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(resp.getWriter());
            }
        }
    }


