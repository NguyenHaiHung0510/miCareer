package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.com.micareer.dao.CompanyDAO;
import vn.com.micareer.dao.ProvinceDAO;
import vn.com.micareer.model.Company;
import vn.com.micareer.model.Province;

@WebServlet("/company-detail")
public class CompanyDetailServlet extends HttpServlet {

    private final CompanyDAO companyDAO = new CompanyDAO();
    private final ProvinceDAO provinceDAO = new ProvinceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/companies");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            Company company = companyDAO.getById(id);

            if (company == null) {
                response.sendRedirect(request.getContextPath() + "/companies");
                return;
            }

            // Join Province để lấy tên tỉnh
            if (company.getProvId() != null && !company.getProvId().isEmpty()) {
                Province province = provinceDAO.getById(company.getProvId());
                request.setAttribute("province", province);
            }

            request.setAttribute("company", company);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/companies");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải thông tin công ty.");
        }

        request.getRequestDispatcher("/views/companies/company-detail.jsp").forward(request, response);
    }
}
