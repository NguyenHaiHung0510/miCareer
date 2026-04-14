package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.micareer.dao.JobPostingDAO;
import vn.com.micareer.model.JobCardView;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<JobCardView> jobs = jobPostingDAO.findPublishedJobs(null, null, null, null, null, null, 0, 6);
            request.setAttribute("jobs", jobs);
        } catch (SQLException e) {
            request.setAttribute("jobs", Collections.emptyList());
            request.setAttribute("error", "Không thể tải danh sách việc làm. Vui lòng thử lại sau.");
        }
        request.getRequestDispatcher("/views/jobs/home.jsp").forward(request, response);
    }
}