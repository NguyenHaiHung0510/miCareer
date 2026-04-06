package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.JobPostingDAO;

@WebServlet("/hr/applications")
public class HrApplicationListServlet extends HttpServlet {

    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
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
            
            // Lấy thông tin công việc để hiển thị tiêu đề
            request.setAttribute("jobDetail", jobPostingDAO.findJobDetailById(jobPostId));
            // Lấy danh sách hồ sơ nộp vào công việc này
            request.setAttribute("applications", jobApplicationDAO.findByJobPostId(jobPostId));
            
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("applications", Collections.emptyList());
            request.setAttribute("error", "Không thể tải danh sách ứng viên.");
        }

        request.getRequestDispatcher("/views/hr/application-list.jsp").forward(request, response);
    }
}
