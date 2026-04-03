/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.AdminRole;

public class AdminRoleDAO implements CrudDAO<AdminRole, String> {

    @Override
    public String insert(AdminRole r) throws SQLException {
        String sql = "INSERT INTO AdminRole(roleId, roleName, desc) VALUES (?,?,?)";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRoleId());
            ps.setString(2, r.getRoleName());
            ps.setString(3, r.getDesc());

            return ps.executeUpdate() > 0 ? r.getRoleId() : null;
        }
    }

    @Override
    public boolean update(AdminRole r) throws SQLException {
        String sql = "UPDATE AdminRole SET roleName=?, desc=? WHERE roleId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRoleName());
            ps.setString(2, r.getDesc());
            ps.setString(3, r.getRoleId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM AdminRole WHERE roleId=?")) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<AdminRole> getAll() throws SQLException {
        List<AdminRole> list = new ArrayList<>();
        String sql = "SELECT * FROM AdminRole";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                AdminRole r = new AdminRole();
                r.setRoleId(rs.getString("roleId"));
                r.setRoleName(rs.getString("roleName"));
                r.setDesc(rs.getString("desc"));
                list.add(r);
            }
        }
        return list;
    }

    @Override
    public AdminRole getById(String id) throws SQLException {
        String sql = "SELECT * FROM AdminRole WHERE roleId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AdminRole r = new AdminRole();
                r.setRoleId(rs.getString("roleId"));
                r.setRoleName(rs.getString("roleName"));
                r.setDesc(rs.getString("desc"));
                return r;
            }
        }
        return null;
    }
}