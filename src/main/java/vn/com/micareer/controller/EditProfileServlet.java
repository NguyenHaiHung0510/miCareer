package vn.com.micareer.controller;

import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.util.UploadCVUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EditProfileServlet", urlPatterns = {"/candidate/editProfile"})
@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024,
        maxRequestSize = 3 * 1024 * 1024
)
public class EditProfileServlet extends HttpServlet {
    
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }
    
    
    public static String extractPublicId(String url) {
        if (url == null || url.isEmpty()) return null;

        int uploadIndex = url.indexOf("/upload/");
        if (uploadIndex == -1) return null;

        String path = url.substring(uploadIndex + 8);

        if (path.startsWith("v")) {
            int slashIndex = path.indexOf("/");
            path = path.substring(slashIndex + 1);
        }

        return path; // giữ .pdf
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Candidate user = (Candidate) session.getAttribute("user");

        try {
            request.setCharacterEncoding("UTF-8");

            // 🔹 form data
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String bio = request.getParameter("bio");
            String dobStr = request.getParameter("dob");
            String expStr = request.getParameter("expYears");

            Part filePart = request.getPart("file");

            // 🔥 CV handling
            String newCvUrl = user.getCvUrl();


            if (filePart != null && filePart.getSize() > 0) {
                
                String oldPublicId = extractPublicId(newCvUrl);
                // 🗑️ xoá CV cũ
                if (oldPublicId != null) {
                    UploadCVUtil.deleteCV(oldPublicId);
                }

                // ⬆️ upload mới
                UploadCVUtil.UploadResult result =
                        UploadCVUtil.uploadCV(filePart, String.valueOf(user.getUserId()));

                newCvUrl = result.getUrl();
            }

            // 🔹 convert
            LocalDate dob = null;
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = LocalDate.parse(dobStr);
            }

            Double expYears = null;
            if (expStr != null && !expStr.isEmpty()) {
                expYears = Double.valueOf(expStr);
            }

            // 🔹 set lại user
            user.setfName(fName);
            user.setlName(lName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBio(bio);
            user.setDob(dob);
            user.setExpYears(expYears);

            // 🔥 QUAN TRỌNG
            user.setCvUrl(newCvUrl);
//            System.out.println(user);
            // 🔹 update DB
            CandidateDAO dao = new CandidateDAO();
            boolean r = dao.update(user);

            if (r) {
                session.setAttribute("message", "Cập nhật thành công!");
            } else {
                session.setAttribute("error", "Cập nhật thất bại!");
            }

            response.sendRedirect("./profile");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Cập nhật thất bại!");
            request.getRequestDispatcher("candidate/edit.jsp").forward(request, response);
        }
    }
}