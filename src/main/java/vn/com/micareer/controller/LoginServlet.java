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
import vn.com.micareer.util.PasswordUtil;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");
        String redirect = request.getParameter("redirect");

        try {
            UserDAO dao = new UserDAO();

            // 🔥 LẤY USER THEO USERNAME (KHÔNG CHECK PASSWORD TRONG SQL)
            User user = dao.getByUsername(userName);
            System.out.println(user);
            // 🔥 VERIFY PASSWORD BẰNG BCRYPT
            if (user == null || !PasswordUtil.verifyPassword(pwd, user.getPwd())) {
                request.setAttribute("error", "Sai username hoặc password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            System.out.println(user);

            // ===== LOGIN SUCCESS =====
            HttpSession session = request.getSession();
            session.setAttribute("account", user);

            Integer userId = user.getUserId();

            switch (user.getRole()) {

                case "CANDIDATE": {
                    CandidateDAO cdao = new CandidateDAO();
                    Candidate c = cdao.getById(userId);

                    session.setAttribute("user", c);

                    if (redirect != null && !redirect.isBlank()) {
                        response.sendRedirect(redirect);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                    break;
                }

                case "HR": {
                    session.setAttribute("loggedUserId", Long.valueOf(user.getUserId()));
                    session.setAttribute("user", user);

                    if (redirect != null && !redirect.isBlank()) {
                        response.sendRedirect(redirect);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/hr/dashboard");
                    }
                    break;
                }

                case "ADMIN": {
                    AdminDAO adao = new AdminDAO();
                    Admin a = adao.getById(userId);

                    session.setAttribute("user", a);
                    response.sendRedirect("admin/dashboard");
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