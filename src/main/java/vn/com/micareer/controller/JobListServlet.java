package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.micareer.dao.JobPostingDAO;

@WebServlet("/job-list")
public class JobListServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String location = request.getParameter("location");
        String workMode = request.getParameter("workMode");
        Long catId = parseLong(request.getParameter("catId"));
        Long levelId = parseLong(request.getParameter("levelId"));
        Long skillId = parseLong(request.getParameter("skillId"));

        request.setAttribute("keyword", keyword);
        request.setAttribute("location", location);
        request.setAttribute("workMode", workMode);
        request.setAttribute("catId", catId);
        request.setAttribute("levelId", levelId);
        request.setAttribute("skillId", skillId);

        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page").trim()) : 0;

        int pageSize = 9;

        int totalJobs;
        
        try {
            totalJobs = jobPostingDAO.countPublishedJobs(keyword, location, workMode, catId, levelId, skillId);
        } catch (Exception e) {
            e.printStackTrace();
            totalJobs = 0;
        }

        int totalPages = (int) Math.ceil((double) totalJobs / pageSize);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        try {
            request.setAttribute("jobs", jobPostingDAO.findPublishedJobs(keyword, location, workMode, catId, levelId, skillId, page, pageSize));
            request.setAttribute("categories", jobPostingDAO.findAllCategories());
            request.setAttribute("levels", jobPostingDAO.findAllLevels());
            request.setAttribute("skills", jobPostingDAO.findAllSkills());
            request.setAttribute("locations", jobPostingDAO.findAllWorkLocations());
        } catch (SQLException e) {
            request.setAttribute("jobs", Collections.emptyList());
            request.setAttribute("categories", Collections.emptyList());
            request.setAttribute("levels", Collections.emptyList());
            request.setAttribute("skills", Collections.emptyList());
            request.setAttribute("locations", Collections.emptyList());
            request.setAttribute("error", "Không thể tải danh sách việc làm. Vui lòng thử lại sau.");
        }

        request.getRequestDispatcher("/views/jobs/job-list.jsp").forward(request, response);
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