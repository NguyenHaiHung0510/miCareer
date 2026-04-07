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
import vn.com.micareer.model.JobSearchCriteria;

@WebServlet("/job-list")
public class JobListServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JobSearchCriteria criteria = buildCriteria(request);
        request.setAttribute("criteria", criteria);
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page").trim()) : 0;

        int pageSize = 2;

        int totalJobs;
        
        try {
            totalJobs = jobPostingDAO.countPublishedJobs(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            totalJobs = 0;
        }

        int totalPages = (int) Math.ceil((double) totalJobs / pageSize);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        try {
            request.setAttribute("jobs", jobPostingDAO.findPublishedJobs(criteria, page, pageSize));
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

    private JobSearchCriteria buildCriteria(HttpServletRequest request) {
        JobSearchCriteria criteria = new JobSearchCriteria();
        criteria.setKeyword(request.getParameter("keyword"));
        criteria.setLocation(request.getParameter("location"));
        criteria.setWorkMode(request.getParameter("workMode"));
        criteria.setCatId(parseLong(request.getParameter("catId")));
        criteria.setLevelId(parseLong(request.getParameter("levelId")));
        criteria.setSkillId(parseLong(request.getParameter("skillId")));
        return criteria;
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