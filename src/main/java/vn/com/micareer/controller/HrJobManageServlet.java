package vn.com.micareer.controller;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.com.micareer.dao.JobPostingDAO;

@WebServlet("/hr/my-jobs")
public class HrJobManageServlet extends HttpServlet {
    private final JobPostingDAO jobPostingDAO = new JobPostingDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // 1. Lấy ID trực tiếp từ Session do LoginServlet truyền sang
        // (HRFilter đã chặn người lạ, nên hrId ở đây chắc chắn có giá trị thật)
        Long hrId = (Long) session.getAttribute("loggedUserId");

        try {
            // 2. Dùng ID của HR đang đăng nhập để query đúng các công việc của họ
            // Lưu ý: Đảm bảo trong JobPostingDAO của bạn có hàm findByHrId (hoặc đổi tên cho khớp với hàm bạn có)
            request.setAttribute("jobs", jobPostingDAO.findByHrId(hrId)); 
            
        } catch (Exception e) {
            request.setAttribute("jobs", Collections.emptyList());
            request.setAttribute("error", "Lỗi tải danh sách công việc: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/hr/my-jobs.jsp").forward(request, response);
    }
}