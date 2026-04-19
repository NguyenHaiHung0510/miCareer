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

import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.model.Candidate;
import vn.com.micareer.model.CandidateApplicationView;

@WebServlet("/candidate/my-applications")
public class CandidateMyApplicationsServlet extends HttpServlet {

    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

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

        if (candidateId == null) {
            request.setAttribute("error", "Không xác định được ứng viên.");
            request.getRequestDispatcher("/candidate/my-applications.jsp").forward(request, response);
            return;
        }

        try {
            List<CandidateApplicationView> apps = jobApplicationDAO.findByCandidateId(candidateId);
            request.setAttribute("applications", apps);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải danh sách đơn ứng tuyển.");
        }

        request.getRequestDispatcher("/candidate/my-applications.jsp").forward(request, response);
    }
}
