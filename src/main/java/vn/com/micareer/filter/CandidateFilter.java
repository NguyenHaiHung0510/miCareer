package vn.com.micareer.filter;

import vn.com.micareer.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/candidate/*")
public class CandidateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }
        System.out.println(user);
        System.out.println(user.getRole());
        // ❌ chưa login
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // ❌ không phải candidate
        if (!"candidate".equalsIgnoreCase(user.getRole())) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // ✅ hợp lệ → cho vào
        chain.doFilter(request, response);
    }
}