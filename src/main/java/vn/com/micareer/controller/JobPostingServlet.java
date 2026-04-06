package vn.com.micareer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import vn.com.micareer.dao.CategoryDAO;

import vn.com.micareer.dao.JobPostingDAO;
import vn.com.micareer.dao.JobRequirementDAO;
import vn.com.micareer.dao.LevelDAO;
import vn.com.micareer.model.Category;
import vn.com.micareer.model.JobPosting;
import vn.com.micareer.model.Level;
import vn.com.micareer.model.Skill;

@WebServlet("/hr/jobposting")
public class JobPostingServlet extends HttpServlet {

    private JobPostingDAO jobDAO = new JobPostingDAO();
    private JobRequirementDAO reqDAO = new JobRequirementDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private LevelDAO levelDAO = new LevelDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // HR giả lập module 3
        String hrId = (String) session.getAttribute("hrId");
        if (hrId == null) {
            hrId = "868ef8c2-316e-11f1-a16f-d03957af07dc";
            session.setAttribute("hrId", hrId);
        }

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listJobs(request, response, hrId);
                break;
            case "view":
                viewJob(request, response);
                break;
            case "edit":
                editJobForm(request, response);
                break;
            default:
                listJobs(request, response, hrId);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // HR giả lập module 3
        String hrId = (String) session.getAttribute("hrId");
        if (hrId == null) {
            hrId = "868ef8c2-316e-11f1-a16f-d03957af07dc";
            session.setAttribute("hrId", hrId);
        }

        switch (action) {
            case "create":
                createJob(request, response, hrId);
                break;
            case "update":
                updateJob(request, response, hrId);
                break;
            case "updateStatus":
                updateStatus(request, response);
                break;
            default:
                response.sendRedirect("jobposting?action=list");
                break;
        }
    }

    // ========== LIST JOBS ==========
    private void listJobs(HttpServletRequest request, HttpServletResponse response, String hrId)
            throws ServletException, IOException {

        List<JobPosting> jobs = jobDAO.getByHr(hrId);
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/hr/jobposting_list.jsp").forward(request, response);
    }

    // ========== VIEW JOB ==========
    private void viewJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jobId = request.getParameter("id");
        JobPosting job = jobDAO.getById(jobId);

        if (job != null) {
            List<String> skills = reqDAO.getSkillsByJob(jobId);
            request.setAttribute("job", job);
            request.setAttribute("skills", skills);
            request.getRequestDispatcher("/hr/jobposting_view.jsp").forward(request, response);
        } else {
            response.sendRedirect("jobposting?action=list");
        }
    }

    // ========== EDIT / CREATE FORM ==========
    private void editJobForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jobId = request.getParameter("id");
        JobPosting job = null;
        List<String> skills = null;

        if (jobId != null) {
            job = jobDAO.getById(jobId);
            if (job != null) {
                skills = reqDAO.getSkillsByJob(jobId);
            }
        }

        List<Skill> allSkills = reqDAO.getAllSkills();
        List<Category> categories = categoryDAO.getAll();
        List<Level> levels = levelDAO.getAll();

        request.setAttribute("categories", categories);
        request.setAttribute("levels", levels);
        request.setAttribute("allSkills", allSkills);
        request.setAttribute("job", job);       // null → create
        request.setAttribute("skills", skills);

        request.getRequestDispatcher("/hr/jobposting_edit.jsp").forward(request, response);
    }

    // ========== CREATE JOB ==========
    private void createJob(HttpServletRequest request, HttpServletResponse response, String hrId)
            throws IOException {

        try {
            JobPosting job = new JobPosting();

            String jobId = java.util.UUID.randomUUID().toString();
            job.setJobPostId(jobId);
            job.setCompId(request.getParameter("compId"));
            job.setHrId(hrId);
            job.setTitle(request.getParameter("title"));
            job.setDesc(request.getParameter("desc"));
            job.setMinSalary(new BigDecimal(request.getParameter("minSalary")));
            job.setMaxSalary(new BigDecimal(request.getParameter("maxSalary")));
            job.setWorkLoc(request.getParameter("workLoc"));
            job.setWorkMode(request.getParameter("workMode"));
            job.setStat("OPEN");
            job.setExpAt(Timestamp.valueOf(request.getParameter("expAt")));
            job.setCatId(request.getParameter("catId"));
            job.setLevelId(request.getParameter("levelId"));

            jobDAO.insert(job);

            // Skill mapping
            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(job.getJobPostId(), Arrays.asList(skillIds));
            }

        } catch (Exception e) {
            System.out.println("createJob error: " + e.getMessage());
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ========== UPDATE JOB ==========
    private void updateJob(HttpServletRequest request, HttpServletResponse response, String hrId)
            throws IOException {

        try {
            JobPosting job = new JobPosting();

            job.setJobPostId(request.getParameter("jobPostId"));
            job.setHrId(hrId);
            job.setTitle(request.getParameter("title"));
            job.setDesc(request.getParameter("desc"));
            job.setMinSalary(new BigDecimal(request.getParameter("minSalary")));
            job.setMaxSalary(new BigDecimal(request.getParameter("maxSalary")));
            job.setWorkLoc(request.getParameter("workLoc"));
            job.setWorkMode(request.getParameter("workMode"));
            job.setExpAt(Timestamp.valueOf(request.getParameter("expAt")));
            job.setCatId(request.getParameter("catId"));
            job.setLevelId(request.getParameter("levelId"));

            jobDAO.update(job);

            // Update skills
            reqDAO.deleteByJob(job.getJobPostId());

            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(job.getJobPostId(), Arrays.asList(skillIds));
            }

        } catch (Exception e) {
            System.out.println("updateJob error: " + e.getMessage());
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ========== UPDATE STATUS ==========
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String jobId = request.getParameter("jobPostId");
        String status = request.getParameter("stat");

        jobDAO.updateStatus(jobId, status);

        response.sendRedirect("jobposting?action=list");
    }
}
