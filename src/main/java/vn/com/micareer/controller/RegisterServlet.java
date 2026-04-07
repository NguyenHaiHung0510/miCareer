/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package vn.com.micareer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.dao.ProvinceDAO;
import vn.com.micareer.dao.UserDAO;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.model.Province;
import vn.com.micareer.model.User;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProvinceDAO dao = new ProvinceDAO();

            List<Province> all = dao.getAll();

            // 🔥 group theo region
            Map<String, List<Province>> grouped =
                    all.stream().collect(Collectors.groupingBy(Province::getRegId));

            request.setAttribute("north", grouped.get("NORTH"));
            request.setAttribute("central", grouped.get("CENTRAL"));
            request.setAttribute("south", grouped.get("SOUTH"));
            
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading provinces");
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String confirmPwd = request.getParameter("confirmPwd");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String provId = request.getParameter("provId");
        String ward = request.getParameter("ward");
        String street = request.getParameter("street");
        String role = request.getParameter("role");

        // ❗ chỉ cho candidate
        if (!"candidate".equals(role)) {
            request.setAttribute("error", "Chỉ hỗ trợ đăng ký Candidate!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // ❗ check password
        if (!pwd.equals(confirmPwd)) {
            request.setAttribute("error", "Mật khẩu không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            UserDAO dao = new UserDAO();

            // ❗ check username
            if (dao.isUsernameExist(userName)) {
                request.setAttribute("error", "Username đã tồn tại!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // 🔹 Candidate fields
            String bio = request.getParameter("bio");
            String cvUrl = request.getParameter("cvUrl");
            String dobStr = request.getParameter("dob");
            String expStr = request.getParameter("expYears");

            LocalDate dob = null;
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = LocalDate.parse(dobStr);
            }

            Double expYears = null;
            if (expStr != null && !expStr.isEmpty()) {
                expYears = Double.valueOf(expStr);
            }

            Candidate c = new Candidate();

            c.setUserName(userName);
            c.setPwd(pwd);
            c.setfName(fName);
            c.setlName(lName);
            c.setEmail(email);
            c.setPhone(phone);
            c.setProvId(provId);
            c.setWard(ward);
            c.setStreet(street);

            c.setRole("CANDIDATE");
            c.setStat("ACTIVE");
            c.setCreatedAt(LocalDateTime.now());

            // Candidate fields
            c.setBio(bio);
            c.setCvUrl(cvUrl);
            c.setDob(dob);
            c.setExpYears(expYears);

            // 🔥 Insert
            CandidateDAO Cdao = new CandidateDAO();
            Cdao.insert(c);

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}