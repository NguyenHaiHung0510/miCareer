package vn.com.micareer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.com.micareer.dao.InterviewDAO;
import vn.com.micareer.dao.InterviewFeedbackDAO;
import vn.com.micareer.model.Interview;
import vn.com.micareer.model.InterviewFeedback;

@WebServlet("/hr/interview-feedback")
public class HrInterviewFeedbackServlet extends HttpServlet {

    private final InterviewFeedbackDAO feedbackDAO = new InterviewFeedbackDAO();
    private final InterviewDAO interviewDAO = new InterviewDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String intervIdStr = request.getParameter("intervId");
        if (intervIdStr == null || intervIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        try {
            long intervId = Long.parseLong(intervIdStr.trim());
            Interview interview = interviewDAO.findById(intervId);
            List<InterviewFeedback> feedbacks = feedbackDAO.findByIntervId(intervId);

            request.setAttribute("interview", interview);
            request.setAttribute("feedbacks", feedbacks);
            request.setAttribute("intervId", intervId);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải dữ liệu feedback.");
        }

        request.getRequestDispatcher("/hr/interview-feedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        Long hrId = session != null ? (Long) session.getAttribute("loggedUserId") : null;

        String intervIdStr = request.getParameter("intervId");
        String scoreStr = request.getParameter("score");
        String cmt = request.getParameter("cmt");

        if (intervIdStr == null || hrId == null) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        try {
            long intervId = Long.parseLong(intervIdStr.trim());

            InterviewFeedback fb = new InterviewFeedback();
            fb.setIntervId(intervId);
            fb.setHrId(hrId);
            fb.setCmt(cmt);

            if (scoreStr != null && !scoreStr.trim().isEmpty()) {
                try {
                    fb.setScore(new BigDecimal(scoreStr.trim()));
                } catch (NumberFormatException ignored) {
                    // Bỏ qua nếu nhập sai số
                }
            }

            feedbackDAO.create(fb);
            response.sendRedirect(request.getContextPath() + "/hr/interview-feedback?intervId=" + intervId + "&success=1");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi lưu feedback: " + e.getMessage());
            doGet(request, response);
        }
    }
}
