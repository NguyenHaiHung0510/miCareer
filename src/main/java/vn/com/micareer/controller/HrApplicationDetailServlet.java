package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.com.micareer.dao.JobApplicationDAO;
import vn.com.micareer.dao.AppStatusHistoryDAO;
import vn.com.micareer.model.AppStatusHistory;
import vn.com.micareer.model.ApplicationDetailView;

@WebServlet("/hr/application-detail")
public class HrApplicationDetailServlet extends HttpServlet {

    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
    private final AppStatusHistoryDAO historyDAO = new AppStatusHistoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobAppIdStr = request.getParameter("jobAppId");
        
        if (jobAppIdStr != null && !jobAppIdStr.isEmpty()) {
            try {
                long jobAppId = Long.parseLong(jobAppIdStr);
                
                // Sử dụng hàm findDetailById đã được ghép hoàn chỉnh từ DAO
                ApplicationDetailView detail = jobApplicationDAO.findDetailById(jobAppId);
                
                request.setAttribute("appDetail", detail);
                request.setAttribute("jobAppId", jobAppId);
            } catch (SQLException | NumberFormatException e) {
                request.setAttribute("error", "Lỗi tải dữ liệu chi tiết: " + e.getMessage());
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/hr/my-jobs");
            return;
        }

        request.getRequestDispatcher("/views/hr/application-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long hrId = (Long) session.getAttribute("loggedUserId");
        
        String jobAppIdStr = request.getParameter("jobAppId");
        String newStatus = request.getParameter("newStatus");
        String oldStatus = request.getParameter("oldStatus"); 

        if (jobAppIdStr != null && newStatus != null) {
            try {
                long jobAppId = Long.parseLong(jobAppIdStr);
                
                // 1. Cập nhật trạng thái
                boolean updated = jobApplicationDAO.updateStatus(jobAppId, newStatus);
                
                if (updated) {
                    // 2. Ghi log thay đổi
                    AppStatusHistory history = new AppStatusHistory();
                    history.setJobAppId(jobAppId);
                    history.setHrId(hrId);
                    history.setOldStat(oldStatus != null && !oldStatus.isEmpty() ? oldStatus : "PENDING");
                    history.setNewStat(newStatus);
                    
                    historyDAO.insertHistory(history);
                    request.setAttribute("success", "Cập nhật trạng thái thành công!");
                } else {
                    request.setAttribute("error", "Cập nhật thất bại, vui lòng thử lại.");
                }
            } catch (SQLException | NumberFormatException e) {
                request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            }
        }
        
        // Load lại dữ liệu mới nhất để hiển thị
        doGet(request, response);
    }
}
