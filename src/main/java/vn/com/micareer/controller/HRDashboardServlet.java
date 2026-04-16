package vn.com.micareer.controller;

import vn.com.micareer.dao.HRDashboardDAO;
import vn.com.micareer.dao.HRDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import vn.com.micareer.model.User;

@WebServlet("/hr/dashboard")
public class HRDashboardServlet extends HttpServlet {

    private HRDashboardDAO dashboardDAO = new HRDashboardDAO();
    private HRDAO hrDAO = new HRDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ===== 1. Lấy HR từ session =====
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");
            int hrId = user.getUserId();

            // ===== 2. Lấy compId =====
            Integer compId = hrDAO.getCompIdByHr(hrId);
            if (compId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // ===== 3. Lấy filter từ request =====
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String status = request.getParameter("status");

            // ===== 4. Gọi DAO =====
            int totalJobs = dashboardDAO.countJobs(compId, status);
            int totalApplications = dashboardDAO.countApplications(compId, fromDate, toDate);
            int applicationsToday = dashboardDAO.countApplicationsToday(compId);

            List<Object[]> topJobs = dashboardDAO.getTopJobs(compId, 5, fromDate, toDate);
            List<Object[]> stats = dashboardDAO.getApplicationStats(compId, fromDate, toDate);

            // ===== 5. Set attribute =====
            request.setAttribute("totalJobs", totalJobs);
            request.setAttribute("totalApplications", totalApplications);
            request.setAttribute("applicationsToday", applicationsToday);
            request.setAttribute("topJobs", topJobs);
            request.setAttribute("stats", stats);

            // giữ lại filter để render lại form
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("toDate", toDate);
            request.setAttribute("status", status);

            // ===== 6. Forward =====
            request.getRequestDispatcher("/hr/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
