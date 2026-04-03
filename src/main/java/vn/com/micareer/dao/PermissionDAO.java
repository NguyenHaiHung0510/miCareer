/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Permission;

public class PermissionDAO implements CrudDAO<Permission, String> {

    @Override
    public String insert(Permission p) throws SQLException {
        String sql = "INSERT INTO Permission(permId, permCode, desc) VALUES (?,?,?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getPermId());
            ps.setString(2, p.getPermCode());
            ps.setString(3, p.getDesc());

            return ps.executeUpdate() > 0 ? p.getPermId() : null;
        }
    }

    @Override
    public boolean update(Permission p) throws SQLException {
        String sql = "UPDATE Permission SET permCode=?, desc=? WHERE permId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getPermCode());
            ps.setString(2, p.getDesc());
            ps.setString(3, p.getPermId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Permission WHERE permId=?")) {

            ps.setString(1, id);
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
                p.setPermId(rs.getString("permId"));
                p.setPermCode(rs.getString("permCode"));
                p.setDesc(rs.getString("desc"));
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Permission getById(String id) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE permId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Permission p = new Permission();
                p.setPermId(rs.getString("permId"));
                p.setPermCode(rs.getString("permCode"));
                p.setDesc(rs.getString("desc"));
                return p;
            }
        }
        return null;
    }
}