package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO implements CrudDAO<Region, String> {

    @Override
    public String insert(Region r) throws SQLException {
        String sql = "INSERT INTO Region(regId, regName) VALUES (?,?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRegId());
            ps.setString(2, r.getRegName());

            return ps.executeUpdate() > 0 ? r.getRegId() : null;
        }
    }

    @Override
    public boolean update(Region r) throws SQLException {
        String sql = "UPDATE Region SET regName=? WHERE regId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRegName());
            ps.setString(2, r.getRegId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Region WHERE regId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Region getById(String id) throws SQLException {
        String sql = "SELECT * FROM Region WHERE regId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }
        return null;
    }

    @Override
    public List<Region> getAll() throws SQLException {
        List<Region> list = new ArrayList<>();
        String sql = "SELECT * FROM Region";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private Region map(ResultSet rs) throws SQLException {
        return new Region(
                rs.getString("regId"),
                rs.getString("regName")
        );
    }
}