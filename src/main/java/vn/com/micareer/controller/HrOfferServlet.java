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

import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.OfferDAO;
import vn.com.micareer.model.ApplicationDetailView;
import vn.com.micareer.model.Offer;

@WebServlet("/hr/offer")
public class HrOfferServlet extends HttpServlet {

    private final OfferDAO offerDAO = new OfferDAO();
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
            List<Offer> offers = offerDAO.findByJobAppId(jobAppId);

            request.setAttribute("appDetail", appDetail);
            request.setAttribute("offers", offers);
            request.setAttribute("jobAppId", jobAppId);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải dữ liệu offer.");
        }

        request.getRequestDispatcher("/hr/offer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        Long hrId = session != null ? (Long) session.getAttribute("loggedUserId") : null;

        String jobAppIdStr = request.getParameter("jobAppId");
        String salaryStr = request.getParameter("salary");
        String desc = request.getParameter("desc");

        if (jobAppIdStr == null || hrId == null) {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        try {
            long jobAppId = Long.parseLong(jobAppIdStr.trim());

            Offer offer = new Offer();
            offer.setJobAppId(jobAppId);
            offer.setHrId(hrId);
            offer.setDesc(desc);
            offer.setStat("PENDING");

            if (salaryStr != null && !salaryStr.trim().isEmpty()) {
                try {
                    offer.setSalary(new BigDecimal(salaryStr.trim().replace(",", "")));
                } catch (NumberFormatException ignored) {
                    // Bỏ qua nếu nhập sai định dạng lương
                }
            }

            offerDAO.create(offer);
            response.sendRedirect(request.getContextPath() + "/hr/offer?jobAppId=" + jobAppId + "&success=1");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tạo offer: " + e.getMessage());
            doGet(request, response);
        }
    }
}
