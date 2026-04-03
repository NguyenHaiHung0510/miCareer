package vn.com.micareer.controller;

import vn.com.micareer.dao.UserDAO;
import vn.com.micareer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import vn.com.micareer.dao.AdminDAO;
import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.model.Admin;
import vn.com.micareer.model.Candidate;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");

        try {
            UserDAO dao = new UserDAO();

            // 🔹 check login
            User user = dao.login(userName, pwd);
            
            if (user == null) {
                request.setAttribute("error", "Sai username hoặc password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            System.out.println(user);
            // 🔹 lưu session
            HttpSession session = request.getSession();

            // 🔥 phân quyền
            switch (user.getRole()) {
                case "candidate":
                    CandidateDAO Cdao = new CandidateDAO();
                    Candidate c = Cdao.getById(user.getUserId());
                    session.setAttribute("user", c);
                    response.sendRedirect("candidate/home.jsp");
                    break;
                case "hr":
                    response.sendRedirect("hr/dashboard.jsp");
                    break;
                case "ADMIN":
                    AdminDAO Adao = new AdminDAO();
                    Admin a = Adao.getById(user.getUserId());
                    session.setAttribute("user", a);
                    response.sendRedirect("admin/dashboard.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}