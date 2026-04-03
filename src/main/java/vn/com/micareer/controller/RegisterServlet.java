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
import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.dao.UserDAO;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.model.User;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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

        if (!"candidate".equals(role)) {
            request.setAttribute("error", "Chỉ hỗ trợ đăng ký Candidate!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!pwd.equals(confirmPwd)) {
            request.setAttribute("error", "Mật khẩu không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            CandidateDAO dao = new CandidateDAO();

            if (dao.isUsernameExist(userName)) {
                request.setAttribute("error", "Username đã tồn tại!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // 🔹 Lấy thêm field candidate
            String bio = request.getParameter("bio");
            String cvUrl = request.getParameter("cvUrl");
            String dobStr = request.getParameter("dob");
            String expStr = request.getParameter("expYears");

            // 🔹 Convert dữ liệu
            LocalDate dob = null;
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = LocalDate.parse(dobStr);
            }

            Double expYears = null;
            if (expStr != null && !expStr.isEmpty()) {
                expYears = Double.valueOf(expStr);
            }

            Candidate c = new Candidate();

            String id = java.util.UUID.randomUUID().toString();

            c.setUserId(id);
            c.setCandidateId(id); // rất quan trọng

            c.setUserName(userName);
            c.setPwd(pwd);
            c.setfName(fName);
            c.setlName(lName);
            c.setEmail(email);
            c.setPhone(phone);
            c.setProvId(provId);
            c.setWard(ward);
            c.setStreet(street);

            c.setRole("candidate");
            c.setStat("active");
            c.setCreatedAt(LocalDateTime.now());

            // 🔹 Candidate fields
            c.setBio(bio);
            c.setCvUrl(cvUrl);
            c.setDob(dob);
            c.setExpYears(expYears);

            // 🔹 Insert DB
            dao.insert(c);

            // 🔥 Redirect sang login
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}