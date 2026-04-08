package vn.com.micareer.controller;

import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.model.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EditProfileServlet", urlPatterns = {"/editProfile"})
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ❌ chưa login
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Candidate user = (Candidate) session.getAttribute("user");

        try {
            // 🔹 Lấy dữ liệu từ form
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String bio = request.getParameter("bio");
            String cvUrl = request.getParameter("cvUrl");
            String dobStr = request.getParameter("dob");
            String expStr = request.getParameter("expYears");

            // 🔹 Convert
            LocalDate dob = null;
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = LocalDate.parse(dobStr);
            }

            Double expYears = null;
            if (expStr != null && !expStr.isEmpty()) {
                expYears = Double.valueOf(expStr);
            }

            // 🔹 Set lại object
            user.setfName(fName);
            user.setlName(lName);
            user.setEmail(email);
            user.setPhone(phone);

            user.setBio(bio);
            user.setCvUrl(cvUrl);
            user.setDob(dob);
            user.setExpYears(expYears);

            // 🔹 Update DB
            CandidateDAO dao = new CandidateDAO();
            boolean r = dao.update(user);
            
            if(r){
                System.out.println("Update thành công");
            }
            else{
                System.out.println("Update thâts bại");
            }
            // 🔹 Update lại session
            session.setAttribute("user", user);

            // 🔥 Redirect về profile
            response.sendRedirect("candidate/profile.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Cập nhật thất bại!");
            request.getRequestDispatcher("candidate/edit.jsp").forward(request, response);
        }
    }
}