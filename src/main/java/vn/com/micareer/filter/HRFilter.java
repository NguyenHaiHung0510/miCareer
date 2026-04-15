package vn.com.micareer.filter;

import vn.com.micareer.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/hr/*") // Chặn tất cả các đường dẫn bắt đầu bằng /hr/
public class HRFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // ❌ Chưa login hoặc session hết hạn
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // ❌ Có login nhưng KHÔNG PHẢI là HR (cố tình gõ link)
        if (!"HR".equalsIgnoreCase(user.getRole())) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // ✅ Hợp lệ → Cho đi tiếp vào các Servlet của Module 5
        chain.doFilter(request, response);
    }
}