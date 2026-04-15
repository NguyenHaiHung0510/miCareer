package vn.com.micareer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import vn.com.micareer.dao.*;
import vn.com.micareer.model.*;
import vn.com.micareer.util.PasswordUtil;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    // ================== LOAD PROVINCE ==================
    private void loadProvince(HttpServletRequest request) throws Exception {
        ProvinceDAO dao = new ProvinceDAO();

        List<Province> all = dao.getAll();

        Map<String, List<Province>> grouped =
                all.stream().collect(Collectors.groupingBy(Province::getRegId));

        request.setAttribute("north", grouped.get("NORTH"));
        request.setAttribute("central", grouped.get("CENTRAL"));
        request.setAttribute("south", grouped.get("SOUTH"));
    }

    // helper tránh try-catch lặp
    private void loadProvinceSafe(HttpServletRequest request) {
        try {
            loadProvince(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // forward gọn lại
    private void forwardRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        loadProvinceSafe(request);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // ================== GET ==================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        forwardRegister(request, response);
    }

    // ================== POST ==================
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
            forwardRegister(request, response);
            return;
        }

        // ❗ check password
        if (!pwd.equals(confirmPwd)) {
            request.setAttribute("error", "Mật khẩu không khớp!");
            forwardRegister(request, response);
            return;
        }
        try {
            UserDAO dao = new UserDAO();
            CandidateDAO cdao = new CandidateDAO();
            
            // ❗ check username
            if (dao.isUsernameExist(userName)) {
                request.setAttribute("error", "Username đã tồn tại!");
                forwardRegister(request, response);
                return;
            }
            // check mail
            if (dao.isEmailExist(email)){
                request.setAttribute("error", "Email đã tồn tại!");
                forwardRegister(request, response);
                return;
            }
            // ===== Candidate fields =====
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
            String hashedPwd = PasswordUtil.hashPassword(pwd);
            
            c.setUserName(userName);
            c.setPwd(hashedPwd);
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

            c.setBio(bio);
            c.setCvUrl(cvUrl);
            c.setDob(dob);
            c.setExpYears(expYears);

            // ===== Insert =====

            cdao.insert(c);

            // 👉 redirect sang login (PRG)
            response.sendRedirect(request.getContextPath() + "/login?success=1");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống!");
            forwardRegister(request, response);
        }
    }
}