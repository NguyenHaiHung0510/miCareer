/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Admin;

public class AdminDAO implements CrudDAO<Admin, String> {
    @Override
    public String insert(Admin a) throws SQLException {
        String sqlUser = "INSERT INTO User(userId, userName, pwd, fName, lName, email, phone, stat, role, provId, ward, street) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String sqlAdmin = "INSERT INTO Admin(adminId, roleId) VALUES (?,?)";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false); // bắt đầu transaction

            // 1. Insert User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setString(1, a.getUserId());
                psUser.setString(2, a.getUserName());
                psUser.setString(3, a.getPwd());
                psUser.setString(4, a.getfName());
                psUser.setString(5, a.getlName());
                psUser.setString(6, a.getEmail());
                psUser.setString(7, a.getPhone());
                psUser.setString(8, a.getStat());
                psUser.setString(9, a.getRole());
                psUser.setString(10, a.getProvId());
                psUser.setString(11, a.getWard());
                psUser.setString(12, a.getStreet());
                psUser.executeUpdate();
            }

            // 2. Insert Admin
            try (PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
                psAdmin.setString(1, a.getAdminId()); // = userId
                psAdmin.setString(2, a.getRoleId());

                psAdmin.executeUpdate();
            }

            con.commit(); // OK hết thì commit
            return a.getAdminId();

        } catch (SQLException e) {
            if (con != null) con.rollback(); // lỗi → rollback
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(Admin a) throws SQLException {
        String sqlUser = "UPDATE User SET userName=?, pwd=?, fName=?, lName=?, email=?, phone=?, stat=?, role=?, provId=?, ward=?, street=? WHERE userId=?";
        String sqlAdmin = "UPDATE Admin SET roleId=? WHERE adminId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false); // transaction

            // 1. Update User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setString(1, a.getUserName());
                psUser.setString(2, a.getPwd());
                psUser.setString(3, a.getfName());
                psUser.setString(4, a.getlName());
                psUser.setString(5, a.getEmail());
                psUser.setString(6, a.getPhone( ));
                psUser.setString(7, a.getStat());
                psUser.setString(8, a.getRole());
                psUser.setString(9, a.getProvId());
                psUser.setString(10, a.getWard());
                psUser.setString(11, a.getStreet());
                psUser.setString(12, a.getUserId());
                psUser.executeUpdate();
            }

            // 2. Update Admin
            try (PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
                psAdmin.setString(1, a.getRoleId());
                psAdmin.setString(2, a.getAdminId());

                psAdmin.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback(); //lỗi rollback
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sqlAdmin = "DELETE FROM Admin WHERE adminId=?";
        String sqlUser = "DELETE FROM User WHERE userId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false); // transaction

            // 1. Xóa Admin trước
            try (PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
                psAdmin.setString(1, id);
                psAdmin.executeUpdate();
            }

            // 2. Xóa User sau
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setString(1, id);
                psUser.executeUpdate();
            }

            con.commit(); // thành công
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback(); //lỗi → rollback
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }
    
    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin> list = new ArrayList<>();

        String sql = "SELECT * FROM Admin a JOIN User u ON a.adminId = u.userId";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs)); // dùng lại
            }
        }
        return list;
    }

    @Override
    public Admin getById(String id) throws SQLException {

        String sql = "SELECT * FROM Admin a JOIN User u ON a.adminId = u.userId WHERE a.adminId = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs); //dùng lại
            }
        }
        return null;
    }

    private Admin map(ResultSet rs) throws SQLException {
        Admin a = new Admin();

        // Admin fields
        a.setAdminId(rs.getString("adminId"));
        a.setRoleId(rs.getString("roleId"));

        // User fields
        a.setUserId(rs.getString("userId"));
        a.setUserName(rs.getString("userName"));
        a.setPwd(rs.getString("pwd"));
        a.setfName(rs.getString("fName"));
        a.setlName(rs.getString("lName"));
        a.setEmail(rs.getString("email"));
        a.setPhone(rs.getString("phone"));
        a.setStat(rs.getString("stat"));
        a.setRole(rs.getString("role"));
        a.setProvId(rs.getString("provId"));
        a.setWard(rs.getString("ward"));
        a.setStreet(rs.getString("street"));

        return a;
    }
}