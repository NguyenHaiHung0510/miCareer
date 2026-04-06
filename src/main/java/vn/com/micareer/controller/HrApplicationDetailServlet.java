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

@WebServlet("/hr/application-detail")
public class HrApplicationDetailServlet extends HttpServlet {

    private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
    private final AppStatusHistoryDAO historyDAO = new AppStatusHistoryDAO();

    // Hiển thị chi tiết đơn ứng tuyển
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("jobAppId", request.getParameter("jobAppId"));
        request.getRequestDispatcher("/views/hr/application-detail.jsp").forward(request, response);
    }

    // Xử lý khi HR nhấn nút cập nhật trạng thái
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long hrId = (Long) session.getAttribute("loggedUserId");
        if (hrId == null) hrId = 1L; // Mock data

        String jobAppIdStr = request.getParameter("jobAppId");
        String newStatus = request.getParameter("newStatus");
        String oldStatus = request.getParameter("oldStatus"); // Trạng thái trước khi đổi

        if (jobAppIdStr != null && newStatus != null) {
            try {
                long jobAppId = Long.parseLong(jobAppIdStr);
                
                // 1. Cập nhật trạng thái mới vào bảng JobApplication
                boolean updated = jobApplicationDAO.updateStatus(jobAppId, newStatus);
                
                if (updated) {
                    // 2. Ghi log lịch sử thay đổi vào bảng AppStatusHistory
                    AppStatusHistory history = new AppStatusHistory();
                    history.setJobAppId(jobAppId);
                    history.setHrId(hrId);
                    history.setOldStat(oldStatus != null ? oldStatus : "PENDING");
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
        
        // Quay lại trang chi tiết bằng doGet
        doGet(request, response);
    }
}
