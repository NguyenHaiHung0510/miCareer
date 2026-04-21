package vn.com.micareer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.com.micareer.dao.CompanyDAO;
import vn.com.micareer.model.Company;

@WebServlet("/companies")
public class CompanyListServlet extends HttpServlet {

    private final CompanyDAO companyDAO = new CompanyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Company> companies = companyDAO.getAll();
            request.setAttribute("companies", companies);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải danh sách công ty.");
        }
        request.getRequestDispatcher("/views/companies/company-list.jsp").forward(request, response);
    }
}
