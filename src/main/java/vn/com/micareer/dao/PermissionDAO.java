package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Permission;

public class PermissionDAO implements CrudDAO<Permission, Integer> { // Đổi sang Integer

    @Override
    public Integer insert(Permission p) throws SQLException {

        String sql = "INSERT INTO Permission(permCode, `desc`) VALUES (?,?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getPermCode());
            ps.setString(2, p.getDesc());

            int affected = ps.executeUpdate();

            if (affected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); //  ID DB sinh
                }
            }
        }

        return null;
    }
    
    @Override
    public boolean update(Permission p) throws SQLException {
        String sql = "UPDATE Permission SET permCode=?, `desc`=? WHERE permId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getPermCode());
            ps.setString(2, p.getDesc());
            ps.setInt(3, p.getPermId()); // setInt

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException { // Tham số Integer
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Permission WHERE permId=?")) {

            ps.setInt(1, id); // setInt
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Permission> getAll() throws SQLException {
        List<Permission> list = new ArrayList<>();
        String sql = "SELECT * FROM Permission";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Permission p = new Permission();
                p.setPermId(rs.getInt("permId")); // getInt
                p.setPermCode(rs.getString("permCode"));
                p.setDesc(rs.getString("desc"));
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Permission getById(Integer id) throws SQLException { // Tham số Integer
        String sql = "SELECT * FROM Permission WHERE permId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // setInt
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Permission p = new Permission();
                    p.setPermId(rs.getInt("permId")); // getInt
                    p.setPermCode(rs.getString("permCode"));
                    p.setDesc(rs.getString("desc"));
                    return p;
                }
            }
        }
        return null;
    }
}