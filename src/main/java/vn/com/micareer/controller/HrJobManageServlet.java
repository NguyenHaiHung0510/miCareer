package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.com.micareer.dao.JobPostingDAO;

@WebServlet("/hr/my-jobs")
public class HrJobManageServlet extends HttpServlet {
    private final JobPostingDAO jobPostingDAO = new JobPostingDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long hrId = (Long) session.getAttribute("loggedUserId");
        if (hrId == null) {
            hrId = 1L; 
        }

        try {
            request.setAttribute("jobs", jobPostingDAO.findPublishedJobs(null, 50)); 
        } catch (SQLException e) {
            request.setAttribute("jobs", Collections.emptyList());
            request.setAttribute("error", "Lỗi tải danh sách công việc: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/hr/my-jobs.jsp").forward(request, response);
    }
}
