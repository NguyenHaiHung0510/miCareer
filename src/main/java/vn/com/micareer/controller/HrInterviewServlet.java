package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.com.micareer.dao.InterviewDAO;
import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.model.ApplicationDetailView;
import vn.com.micareer.model.Interview;

@WebServlet("/hr/interview")
public class HrInterviewServlet extends HttpServlet {

    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jobAppIdStr = request.getParameter("jobAppId");
        if (jobAppIdStr == null || jobAppIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        try {
            long jobAppId = Long.parseLong(jobAppIdStr.trim());
            ApplicationDetailView appDetail = jobApplicationDAO.findDetailById(jobAppId);
            List<Interview> interviews = interviewDAO.findByJobAppId(jobAppId);

            request.setAttribute("appDetail", appDetail);
            request.setAttribute("interviews", interviews);
            request.setAttribute("jobAppId", jobAppId);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải dữ liệu phỏng vấn.");
        }

        request.getRequestDispatcher("/hr/interview.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        String jobAppIdStr = request.getParameter("jobAppId");

        if (jobAppIdStr == null) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        long jobAppId;
        try {
            jobAppId = Long.parseLong(jobAppIdStr.trim());
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        if ("create".equals(action)) {
            handleCreate(request, response, jobAppId, session);
        } else if ("updateStat".equals(action)) {
            handleUpdateStat(request, response, jobAppId);
        } else {
            response.sendRedirect(request.getContextPath() + "/hr/interview?jobAppId=" + jobAppId);
        }
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response,
                              long jobAppId, HttpSession session) throws IOException, ServletException {
        String title = request.getParameter("title");
        String startAtStr = request.getParameter("startAt");
        String endAtStr = request.getParameter("endAt");
        String mode = request.getParameter("mode");
        String linkMeet = request.getParameter("linkMeet");
        String loc = request.getParameter("loc");

        if (title == null || title.trim().isEmpty() || startAtStr == null || startAtStr.trim().isEmpty()) {
            request.setAttribute("error", "Tiêu đề và thời gian bắt đầu không được để trống.");
            doGet(request, response);
            return;
        }

        try {
            Interview interview = new Interview();
            interview.setJobAppId(jobAppId);
            interview.setTitle(title.trim());
            interview.setStartAt(LocalDateTime.parse(startAtStr));
            if (endAtStr != null && !endAtStr.trim().isEmpty()) {
                interview.setEndAt(LocalDateTime.parse(endAtStr));
            }
            interview.setMode(mode);
            interview.setLinkMeet(linkMeet);
            interview.setLoc(loc);
            interview.setStat("SCHEDULED");

            interviewDAO.create(interview);
            response.sendRedirect(request.getContextPath() + "/hr/interview?jobAppId=" + jobAppId + "&success=1");
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Định dạng thời gian không hợp lệ. Vui lòng nhập đúng (vd: 2026-04-20T09:00).");
            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tạo lịch phỏng vấn: " + e.getMessage());
            doGet(request, response);
        }
    }

    private void handleUpdateStat(HttpServletRequest request, HttpServletResponse response,
                                  long jobAppId) throws IOException, ServletException {
        String intervIdStr = request.getParameter("intervId");
        String stat = request.getParameter("stat");

        if (intervIdStr == null || stat == null) {
            response.sendRedirect(request.getContextPath() + "/hr/interview?jobAppId=" + jobAppId);
            return;
        }

        try {
            long intervId = Long.parseLong(intervIdStr.trim());
            interviewDAO.updateStat(intervId, stat);
            response.sendRedirect(request.getContextPath() + "/hr/interview?jobAppId=" + jobAppId + "&success=1");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi cập nhật trạng thái phỏng vấn.");
            doGet(request, response);
        }
    }
}
