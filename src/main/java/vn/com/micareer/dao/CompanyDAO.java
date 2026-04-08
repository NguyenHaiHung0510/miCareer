package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements CrudDAO<Company, Integer> {

    @Override
    public Integer insert(Company c) throws SQLException {
        String sql = "INSERT INTO Company(compName, taxCode, webUrl, logoUrl, contactEmail, provId, ward, street) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getCompName());
            ps.setString(2, c.getTaxCode());
            ps.setString(3, c.getWebUrl());
            ps.setString(4, c.getLogoUrl());
            ps.setString(5, c.getContactEmail());
            ps.setString(6, c.getProvId());
            ps.setString(7, c.getWard());
            ps.setString(8, c.getStreet());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return null;
        }
    }

    @Override
    public boolean update(Company c) throws SQLException {
        String sql = "UPDATE Company SET compName=?, taxCode=?, webUrl=?, logoUrl=?, contactEmail=?, provId=?, ward=?, street=? WHERE compId=?";

        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCompName());
            ps.setString(2, c.getTaxCode());
            ps.setString(3, c.getWebUrl());
            ps.setString(4, c.getLogoUrl());
            ps.setString(5, c.getContactEmail());
            ps.setString(6, c.getProvId());
            ps.setString(7, c.getWard());
            ps.setString(8, c.getStreet());
            ps.setInt(9, c.getCompId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Company WHERE compId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Company getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Company WHERE compId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Company> getAll() throws SQLException {
        List<Company> list = new ArrayList<>();
        String sql = "SELECT * FROM Company";
        try (Connection con = DBContext.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private Company map(ResultSet rs) throws SQLException {
        return new Company(
                rs.getInt("compId"),
                rs.getString("compName"),
                rs.getString("taxCode"),
                rs.getString("webUrl"),
                rs.getString("logoUrl"),
                rs.getString("contactEmail"),
                rs.getString("provId"),
                rs.getString("ward"),
                rs.getString("street")
        );
    }
}
