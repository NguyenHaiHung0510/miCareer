package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.com.micareer.dao.InterviewDAO;
import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.OfferDAO;
import vn.com.micareer.model.ApplicationDetailView;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.model.Interview;
import vn.com.micareer.model.Offer;

@WebServlet("/candidate/application-detail")
public class CandidateApplicationDetailServlet extends HttpServlet {

    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final OfferDAO offerDAO = new OfferDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Object user = session.getAttribute("user");
        if (!(user instanceof Candidate)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Candidate candidate = (Candidate) user;
        Integer candidateId = candidate.getCandidateId() != null
                ? candidate.getCandidateId()
                : candidate.getUserId();

        String jobAppIdStr = request.getParameter("jobAppId");
        if (jobAppIdStr == null || jobAppIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/candidate/my-applications");
            return;
        }

        try {
            long jobAppId = Long.parseLong(jobAppIdStr.trim());
            ApplicationDetailView detail = jobApplicationDAO.findDetailById(jobAppId);

            if (detail == null) {
                request.setAttribute("error", "Không tìm thấy đơn ứng tuyển.");
                request.getRequestDispatcher("/candidate/application-detail.jsp").forward(request, response);
                return;
            }

            // Kiểm tra đơn thuộc về candidate đang login
            if (candidateId == null || detail.getCandidateId() != candidateId) {
                response.sendRedirect(request.getContextPath() + "/candidate/my-applications");
                return;
            }

            List<Interview> interviews = interviewDAO.findByJobAppId(jobAppId);
            List<Offer> offers = offerDAO.findByJobAppId(jobAppId);
            request.setAttribute("appDetail", detail);
            request.setAttribute("interviews", interviews);
            request.setAttribute("offers", offers);
            request.setAttribute("jobAppId", jobAppId);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/candidate/my-applications");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải chi tiết đơn ứng tuyển.");
        }

        request.getRequestDispatcher("/candidate/application-detail.jsp").forward(request, response);
    }
}
