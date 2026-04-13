package vn.com.micareer.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.micareer.dao.JobPostingDAO;

@WebServlet("/hr/job-detail")
public class HrJobDetailServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobIdParam = request.getParameter("jobPostId");
        
        if (jobIdParam == null || jobIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        try {
            long jobPostId = Long.parseLong(jobIdParam);
            // Kéo thông tin chi tiết từ Database
            request.setAttribute("jobDetail", jobPostingDAO.findJobDetailById(jobPostId));
            
        } catch (Exception e) {
            request.setAttribute("error", "Không thể tải chi tiết công việc.");
        }

        // Đẩy dữ liệu sang trang giao diện mới
        request.getRequestDispatcher("/hr/job-detail.jsp").forward(request, response);
    }
}