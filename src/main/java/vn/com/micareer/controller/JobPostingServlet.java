package vn.com.micareer.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import vn.com.micareer.dao.*;
import vn.com.micareer.model.JobPosting;

@WebServlet("/hr/jobposting")
public class JobPostingServlet extends HttpServlet {

    private JobPostingDAO jobDAO = new JobPostingDAO();
    private JobRequirementDAO reqDAO = new JobRequirementDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private LevelDAO levelDAO = new LevelDAO();
    private HRDAO hrDAO = new HRDAO();

    // ================= GET =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Integer hrIdObj = (Integer) session.getAttribute("hrId");
        int hrId = (hrIdObj == null) ? 22 : hrIdObj;

        session.setAttribute("hrId", hrId);

        if (action == null) action = "list";

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
        int hrId = (hrIdObj == null) ? 22 : hrIdObj;

        session.setAttribute("hrId", hrId);

        switch (action) {
            case "create":
                createJob(request, response, hrId);
                break;
            case "update":
                updateJob(request, response, hrId);
                break;
            case "updateStatus":
                updateStatus(request, response, hrId);
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
            request.setAttribute("job", job);
            request.setAttribute("skills", reqDAO.getSkillsByJob(jobId));
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
            throws ServletException, IOException {

        Map<String, String> fieldErrors = new HashMap<>();

        try {
            JobPosting job = new JobPosting();

            Integer compId = hrDAO.getCompIdByHr(hrId);
            if (compId == null) {
                response.sendRedirect("jobposting?action=list");
                return;
            }

            String title = request.getParameter("title");
            String desc = request.getParameter("desc");
            String minSalaryRaw = request.getParameter("minSalary");
            String maxSalaryRaw = request.getParameter("maxSalary");
            String workLoc = request.getParameter("workLoc");
            // ===== VALIDATE =====
            if (title == null || title.trim().isEmpty()) {
                fieldErrors.put("title", "Không được để trống");
            }

            if (desc == null || desc.trim().isEmpty()) {
                fieldErrors.put("desc", "Không được để trống");
            }
            
            if (workLoc == null || workLoc.trim().isEmpty()) {
                fieldErrors.put("workLoc", "Không được để trống");
            }
            BigDecimal minSalary = null;
            BigDecimal maxSalary = null;

            try {
                minSalary = new BigDecimal(minSalaryRaw);
                maxSalary = new BigDecimal(maxSalaryRaw);

                if (minSalary.compareTo(BigDecimal.ZERO) < 0) {
                    fieldErrors.put("minSalary", "Mức lương phải >= 0");
                }

                if (maxSalary.compareTo(BigDecimal.ZERO) < 0) {
                    fieldErrors.put("maxSalary", "Mức lương phải >= 0");
                }

                if (minSalary.compareTo(maxSalary) > 0) {
                    fieldErrors.put("maxSalary", "Lương tối đa phải >= Lương tối thiểu");
                }

            } catch (Exception e) {
                fieldErrors.put("minSalary", "Không hợp lệ");
                fieldErrors.put("maxSalary", "Không hợp lệ");
            }

            // ===== CÓ LỖI -> RETURN =====
            if (!fieldErrors.isEmpty()) {
                request.setAttribute("fieldErrors", fieldErrors);
                forwardBackToForm(request, response, null);
                return;
            }

            // ===== SET DATA =====
            job.setCompId(compId);
            job.setHrId(hrId);
            job.setTitle(title.trim());
            job.setDesc(desc.trim());
            job.setMinSalary(minSalary);
            job.setMaxSalary(maxSalary);
            job.setWorkLoc(workLoc);
            job.setWorkMode(request.getParameter("workMode"));
            job.setStat("PUBLISHED");

            String expRaw = request.getParameter("expAt");
            if (expRaw != null && !expRaw.isEmpty()) {
                expRaw = expRaw.replace("T", " ") + ":00";
                job.setExpAt(Timestamp.valueOf(expRaw));
            }

            job.setCatId(Integer.parseInt(request.getParameter("catId")));
            job.setLevelId(Integer.parseInt(request.getParameter("levelId")));

            int jobId = jobDAO.insert(job);

            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(jobId,
                        Arrays.stream(skillIds).map(Integer::parseInt).toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống");
            forwardBackToForm(request, response, null);
            return;
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ================= UPDATE =================
    private void updateJob(HttpServletRequest request, HttpServletResponse response, int hrId)
            throws ServletException, IOException {

        int jobId = Integer.parseInt(request.getParameter("jobPostId"));
        Map<String, String> fieldErrors = new HashMap<>();

        try {
            JobPosting job = new JobPosting();

            Integer compId = hrDAO.getCompIdByHr(hrId);
            if (compId == null) {
                response.sendRedirect("jobposting?action=list");
                return;
            }

            String title = request.getParameter("title");
            String desc = request.getParameter("desc");
            String minSalaryRaw = request.getParameter("minSalary");
            String maxSalaryRaw = request.getParameter("maxSalary");
            String workLoc = request.getParameter("workLoc");
            
            if (title == null || title.trim().isEmpty()) {
                fieldErrors.put("title", "Không được để trống");
            }

            if (desc == null || desc.trim().isEmpty()) {
                fieldErrors.put("desc", "Không được để trống");
            }
            
            if (workLoc == null || workLoc.trim().isEmpty()) {
                fieldErrors.put("workLoc", "Không được để trống");
            }
            BigDecimal minSalary = null;
            BigDecimal maxSalary = null;

            try {
                minSalary = new BigDecimal(minSalaryRaw);
                maxSalary = new BigDecimal(maxSalaryRaw);
                
                if (minSalary.compareTo(BigDecimal.ZERO) < 0) {
                    fieldErrors.put("minSalary", "Mức lương phải >= 0");
                }

                if (maxSalary.compareTo(BigDecimal.ZERO) < 0) {
                    fieldErrors.put("maxSalary", "Mức lương phải >= 0");
                }
                
                if (minSalary.compareTo(maxSalary) > 0) {
                    fieldErrors.put("maxSalary", "Lương tối đa phải >= Lương tối thiểu");
                }

            } catch (Exception e) {
                fieldErrors.put("minSalary", "Không hợp lệ");
                fieldErrors.put("maxSalary", "Không hợp lệ");
            }

            if (!fieldErrors.isEmpty()) {
                request.setAttribute("fieldErrors", fieldErrors);
                forwardBackToForm(request, response, jobId);
                return;
            }

            // ===== SET =====
            job.setJobPostId(jobId);
            job.setHrId(hrId);
            job.setCompId(compId);
            job.setTitle(title.trim());
            job.setDesc(desc.trim());
            job.setMinSalary(minSalary);
            job.setMaxSalary(maxSalary);
            job.setWorkLoc(workLoc);
            job.setWorkMode(request.getParameter("workMode"));

            String expRaw = request.getParameter("expAt");
            if (expRaw != null && !expRaw.isEmpty()) {
                expRaw = expRaw.replace("T", " ") + ":00";
                job.setExpAt(Timestamp.valueOf(expRaw));
            }

            job.setCatId(Integer.parseInt(request.getParameter("catId")));
            job.setLevelId(Integer.parseInt(request.getParameter("levelId")));

            jobDAO.updateByHR(job, hrId);

            reqDAO.deleteByJob(jobId);

            String[] skillIds = request.getParameterValues("skills");
            if (skillIds != null) {
                reqDAO.insert(jobId,
                        Arrays.stream(skillIds).map(Integer::parseInt).toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống");
            forwardBackToForm(request, response, jobId);
            return;
        }

        response.sendRedirect("jobposting?action=list");
    }

    // ================= STATUS =================
    private void updateStatus(HttpServletRequest request, HttpServletResponse response, int hrId)
            throws IOException {

        int jobId = Integer.parseInt(request.getParameter("jobPostId"));
        String status = request.getParameter("stat");

        jobDAO.updateStatus(jobId, status, hrId);
        response.sendRedirect("jobposting?action=list");
    }

    // ================= FORWARD =================
    private void forwardBackToForm(HttpServletRequest request, HttpServletResponse response, Integer jobId)
            throws ServletException, IOException {

        if (jobId != null) {
            JobPosting job = jobDAO.getById(jobId);
            request.setAttribute("job", job);
            request.setAttribute("skills", reqDAO.getSkillsByJob(jobId));
        }

        request.setAttribute("categories", categoryDAO.getAll());
        request.setAttribute("levels", levelDAO.getAll());
        request.setAttribute("allSkills", reqDAO.getAllSkills());

        request.getRequestDispatcher("/hr/jobposting_edit.jsp").forward(request, response);
    }
}