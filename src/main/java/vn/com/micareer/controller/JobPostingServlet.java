package vn.com.micareer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import vn.com.micareer.dao.*;
import vn.com.micareer.model.JobPosting;

@WebServlet("/hr/jobposting")
public class JobPostingServlet extends HttpServlet {

    private JobPostingDAO jobDAO = new JobPostingDAO();
    private JobRequirementDAO reqDAO = new JobRequirementDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private LevelDAO levelDAO = new LevelDAO();

    // ================= GET =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Integer hrIdObj = (Integer) session.getAttribute("hrId");
        int hrId;

        if (hrIdObj == null) {
            hrId = 22;
            session.setAttribute("hrId", hrId);
        } else {
            hrId = hrIdObj;
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
        }
    }

    // ================= POST =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Integer hrIdObj = (Integer) session.getAttribute("hrId");
        int hrId;

        if (hrIdObj == null) {
            hrId = 22;
            session.setAttribute("hrId", hrId);
        } else {
            hrId = hrIdObj;
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
        }
    }

    // ================= LIST =================
    private void listJobs(HttpServletRequest request, HttpServletResponse response, int hrId)
            throws ServletException, IOException {

        List<JobPosting> jobs = jobDAO.getByHr(hrId);
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/hr/jobposting_list.jsp").forward(request, response);
    }

    // ================= VIEW =================
    private void viewJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int jobId = Integer.parseInt(request.getParameter("id"));
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

    // ================= EDIT =================
    private void editJobForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idRaw = request.getParameter("id");
        JobPosting job = null;
        List<String> skills = null;

        if (idRaw != null) {
            int jobId = Integer.parseInt(idRaw);
            job = jobDAO.getById(jobId);

            if (job != null) {
                skills = reqDAO.getSkillsByJob(jobId);
            }
        }

        request.setAttribute("categories", categoryDAO.getAll());
        request.setAttribute("levels", levelDAO.getAll());
        request.setAttribute("allSkills", reqDAO.getAllSkills());
        request.setAttribute("job", job);
        request.setAttribute("skills", skills);

        request.getRequestDispatcher("/hr/jobposting_edit.jsp").forward(request, response);
    }

    // ================= CREATE =================
    private void createJob(HttpServletRequest request, HttpServletResponse response, int hrId)
            throws IOException {

        try {
            JobPosting job = new JobPosting();

            // ❗ jobId auto tăng → KHÔNG cần set nếu DB identity
            job.setCompId(Integer.parseInt(request.getParameter("compId")));
            job.setHrId(hrId);
            job.setTitle(request.getParameter("title"));
            job.setDesc(request.getParameter("desc"));

            job.setMinSalary(new BigDecimal(request.getParameter("minSalary")));
            job.setMaxSalary(new BigDecimal(request.getParameter("maxSalary")));

            job.setWorkLoc(request.getParameter("workLoc"));
            job.setWorkMode(request.getParameter("workMode"));
            job.setStat("PUBLISHED");

            // DATE
            String expRaw = request.getParameter("expAt");
            if (expRaw != null && !expRaw.isEmpty()) {
                expRaw = expRaw.replace("T", " ") + ":00";
                job.setExpAt(Timestamp.valueOf(expRaw));
            }

            job.setCatId(Integer.parseInt(request.getParameter("catId")));
            job.setLevelId(Integer.parseInt(request.getParameter("levelId")));

            int jobId = jobDAO.insert(job); // ⚠️ DAO nên return id

            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(jobId, Arrays.stream(skillIds)
                        .map(Integer::parseInt)
                        .toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ================= UPDATE =================
    private void updateJob(HttpServletRequest request, HttpServletResponse response, int hrId)
            throws IOException {

        try {
            JobPosting job = new JobPosting();

            int jobId = Integer.parseInt(request.getParameter("jobPostId"));

            job.setJobPostId(jobId);
            job.setHrId(hrId);
            job.setCompId(Integer.parseInt(request.getParameter("compId")));

            job.setTitle(request.getParameter("title"));
            job.setDesc(request.getParameter("desc"));

            job.setMinSalary(new BigDecimal(request.getParameter("minSalary")));
            job.setMaxSalary(new BigDecimal(request.getParameter("maxSalary")));

            job.setWorkLoc(request.getParameter("workLoc"));
            job.setWorkMode(request.getParameter("workMode"));

            String expRaw = request.getParameter("expAt");
            if (expRaw != null && !expRaw.isEmpty()) {
                expRaw = expRaw.replace("T", " ") + ":00";
                job.setExpAt(Timestamp.valueOf(expRaw));
            }

            job.setCatId(Integer.parseInt(request.getParameter("catId")));
            job.setLevelId(Integer.parseInt(request.getParameter("levelId")));

            jobDAO.update(job);

            reqDAO.deleteByJob(jobId);

            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(jobId, Arrays.stream(skillIds)
                        .map(Integer::parseInt)
                        .toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ================= STATUS =================
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int jobId = Integer.parseInt(request.getParameter("jobPostId"));
        String status = request.getParameter("stat");

        jobDAO.updateStatus(jobId, status);

        response.sendRedirect("jobposting?action=list");
    }
}
