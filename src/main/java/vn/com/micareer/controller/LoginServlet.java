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

            // check login
            User user = dao.login(userName, pwd);

            if (user == null) {
                request.setAttribute("error", "Sai username hoặc password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            System.out.println(user);

            // ?session
            HttpSession session = request.getSession();
            session.setAttribute("account", user); // lưu user chung

            Integer userId = user.getUserId(); // dùng Integer

            // phân quyền
            switch (user.getRole()) {

                case "CANDIDATE": {
                    CandidateDAO cdao = new CandidateDAO();
                    Candidate c = cdao.getById(userId); 

                    session.setAttribute("user", c);
                    response.sendRedirect("candidate/home.jsp");
                    break;
                }

                case "HR": {
                    // Truyền thêm biến loggedUserId kiểu Long cho Module 5 dùng
                    session.setAttribute("loggedUserId", Long.valueOf(user.getUserId()));
                    session.setAttribute("user", user);
                    
                    // Sửa hướng redirect sang Module 5
                    response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
                    break;
                }
                case "ADMIN": {
                    AdminDAO adao = new AdminDAO();
                    Admin a = adao.getById(userId);

                    session.setAttribute("user", a);
                    response.sendRedirect("admin/dashboard.jsp");
                    break;
                }

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