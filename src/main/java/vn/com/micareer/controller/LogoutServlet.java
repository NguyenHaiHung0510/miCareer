package vn.com.micareer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
    
import java.io.IOException; 

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔹 nếu có session thì hủy
        if (session != null) {
            session.invalidate();
        }

        // 🔥 quay về login
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}