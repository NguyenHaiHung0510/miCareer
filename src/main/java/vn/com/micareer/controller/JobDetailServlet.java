package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.JobPostingDAO;
import vn.com.micareer.model.JobDetailView;

@WebServlet("/job-detail")
public class JobDetailServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();
    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long jobPostId = parseLong(request.getParameter("id"));
        if (jobPostId == null || jobPostId <= 0) {
            response.sendRedirect(request.getContextPath() + "/job-list");
            return;
        }

        try {
            JobDetailView detail = jobPostingDAO.findJobDetailById(jobPostId);
            if (detail == null) {
                response.sendRedirect(request.getContextPath() + "/job-list");
                return;
            }
            request.setAttribute("job", detail);
            request.setAttribute("applicationCount", jobApplicationDAO.countByJobPostId(jobPostId));
        } catch (SQLException e) {
            request.setAttribute("error", "Không thể tải chi tiết việc làm. Vui lòng thử lại sau.");
        }

        request.getRequestDispatcher("/views/jobs/job-detail.jsp").forward(request, response);
    }

    private Long parseLong(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}