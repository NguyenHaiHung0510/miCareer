package vn.com.micareer.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpSession;
import vn.com.micareer.dao.CandidateDAO;
import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.JobPostingDAO;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.model.JobApplication;
import vn.com.micareer.model.JobDetailView;
import vn.com.micareer.util.FileUploadUtil;

@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet("/apply-job")
public class ApplyJobServlet extends HttpServlet {

    private final JobPostingDAO jobPostingDAO = new JobPostingDAO();
    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
    private final CandidateDAO candidateDAO = new CandidateDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long jobPostId = parseLong(request.getParameter("jobPostId"));
        if (jobPostId == null || jobPostId <= 0) {
            response.sendRedirect(request.getContextPath() + "/job-list");
            return;
        }

        Candidate candidate = getLoggedInCandidate(request);
        if (candidate == null) {
            redirectToLogin(request, response, jobPostId);
            return;
        }
        request.setAttribute("candidateId", resolveCandidateId(candidate));

        try {
            JobDetailView detail = jobPostingDAO.findJobDetailById(jobPostId);
            if (detail == null) {
                response.sendRedirect(request.getContextPath() + "/job-list");
                return;
            }
            request.setAttribute("job", detail);
        } catch (SQLException e) {
            request.setAttribute("error", "Không thể tải thông tin việc làm.");
        }

        request.getRequestDispatcher("/views/jobs/apply-job.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long jobPostId = parseLong(request.getParameter("jobPostId"));
        Candidate candidate = getLoggedInCandidate(request);
        if (candidate == null) {
            redirectToLogin(request, response, jobPostId);
            return;
        }

        Long candidateId = resolveCandidateId(candidate);
        String coverLetter = request.getParameter("coverLetter");
        Part cvFile = request.getPart("cvFile");

        if (jobPostId == null || candidateId == null) {
            request.setAttribute("error", "Không thể xác định tài khoản candidate để ứng tuyển.");
            forwardWithJob(request, response, jobPostId);
            return;
        }

        try {
            if (!candidateDAO.existsById(candidateId)) {
                request.setAttribute("error", "Candidate không tồn tại. Vui lòng kiểm tra candidateId.");
                forwardWithJob(request, response, jobPostId);
                return;
            }

            if (jobApplicationDAO.existsByJobAndCandidate(jobPostId, candidateId)) {
                request.setAttribute("error", "Bạn đã ứng tuyển công việc này trước đó.");
                forwardWithJob(request, response, jobPostId);
                return;
            }

            String cvPath;
            if (cvFile != null && cvFile.getSize() > 0) {
                if (!FileUploadUtil.isValidCvFile(cvFile)) {
                    request.setAttribute("error", "Định dạng CV không hợp lệ. Chỉ chấp nhận PDF/DOC/DOCX.");
                    forwardWithJob(request, response, jobPostId);
                    return;
                }
                String uploadPath = getServletContext().getRealPath("");
                cvPath = FileUploadUtil.saveFile(cvFile, uploadPath);
            } else {
                request.setAttribute("error", "Vui lòng upload CV trước khi ứng tuyển.");
                forwardWithJob(request, response, jobPostId);
                return;
            }

            JobApplication application = new JobApplication();
            application.setJobPostId(jobPostId);
            application.setCandidateId(candidateId);
            application.setStat("PENDING");
            application.setCvSnapUrl(cvPath);
            application.setCoverLetter(coverLetter);

            long jobAppId = jobApplicationDAO.create(application);
            if (jobAppId > 0) {
                request.setAttribute("success", "Ứng tuyển thành công. Mã hồ sơ: " + jobAppId);
            } else {
                request.setAttribute("error", "Ứng tuyển thất bại. Vui lòng thử lại.");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi hệ thống khi tạo đơn ứng tuyển: " + e.getMessage());
        }

        forwardWithJob(request, response, jobPostId);
    }

    private void forwardWithJob(HttpServletRequest request, HttpServletResponse response, Long jobPostId)
            throws ServletException, IOException {
        if (jobPostId != null && jobPostId > 0) {
            try {
                request.setAttribute("job", jobPostingDAO.findJobDetailById(jobPostId));
            } catch (SQLException ignored) {
                request.setAttribute("job", null);
            }
        }
        request.setAttribute("candidateId", resolveCandidateId(getLoggedInCandidate(request)));
        request.getRequestDispatcher("/views/jobs/apply-job.jsp").forward(request, response);
    }

    private Candidate getLoggedInCandidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Object user = session.getAttribute("user");
        if (user instanceof Candidate) {
            return (Candidate) user;
        }
        return null;
    }

    private Long resolveCandidateId(Candidate candidate) {
        if (candidate == null) {
            return null;
        }
        if (candidate.getCandidateId() != null) {
            return candidate.getCandidateId().longValue();
        }
        if (candidate.getUserId() != null) {
            return candidate.getUserId().longValue();
        }
        return null;
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response, Long jobPostId) throws IOException {
        String target = request.getContextPath() + "/apply-job";
        if (jobPostId != null && jobPostId > 0) {
            target += "?jobPostId=" + jobPostId;
        }
        String loginUrl = request.getContextPath() + "/login.jsp?redirect="
                + URLEncoder.encode(target, StandardCharsets.UTF_8);
        response.sendRedirect(loginUrl);
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