package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.AdminRole;

public class AdminRoleDAO implements CrudDAO<AdminRole, Integer> { // Đổi Generic sang Integer

    @Override
    public Integer insert(AdminRole r) throws SQLException {
        String sql = "INSERT INTO AdminRole(roleName, `desc`) VALUES (?,?)";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, r.getRoleName());
            ps.setString(2, r.getDesc());

            int affected = ps.executeUpdate();
            if(affected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(AdminRole r) throws SQLException {
        String sql = "UPDATE AdminRole SET roleName=?, `desc`=? WHERE roleId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRoleName());
            ps.setString(2, r.getDesc());
            ps.setInt(3, r.getRoleId()); // setInt

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException { // Tham số là Integer
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM AdminRole WHERE roleId=?")) {

            ps.setInt(1, id); // setInt
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
                r.setRoleId(rs.getInt("roleId")); // getInt
                r.setRoleName(rs.getString("roleName"));
                r.setDesc(rs.getString("desc"));
                list.add(r);
            }
        }
        return list;
    }

    @Override
    public AdminRole getById(Integer id) throws SQLException { // Tham số là Integer
        String sql = "SELECT * FROM AdminRole WHERE roleId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // setInt
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdminRole r = new AdminRole();
                    r.setRoleId(rs.getInt("roleId")); // getInt
                    r.setRoleName(rs.getString("roleName"));
                    r.setDesc(rs.getString("desc"));
                    return r;
                }
            }
        }
        return null;
    }
}