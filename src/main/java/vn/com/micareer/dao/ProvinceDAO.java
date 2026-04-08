package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO implements CrudDAO<Province, String> {

    @Override
    public String insert(Province p) throws SQLException {
        String sql = "INSERT INTO Province(provId, provName, regId) VALUES (?,?,?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getProvId());
            ps.setString(2, p.getProvName());
            ps.setString(3, p.getRegId());

            return ps.executeUpdate() > 0 ? p.getProvId() : null;
        }
    }

    @Override
    public boolean update(Province p) throws SQLException {
        String sql = "UPDATE Province SET provName=?, regId=? WHERE provId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getProvName());
            ps.setString(2, p.getRegId());
            ps.setString(3, p.getProvId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Province WHERE provId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Province getById(String id) throws SQLException {
        String sql = "SELECT * FROM Province WHERE provId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }
        return null;
    }

    @Override
    public List<Province> getAll() throws SQLException {
        List<Province> list = new ArrayList<>();
        String sql = "SELECT * FROM Province";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    // 🔥 thêm useful method
    public List<Province> getByRegion(String regId) throws SQLException {
        List<Province> list = new ArrayList<>();
        String sql = "SELECT * FROM Province WHERE regId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private Province map(ResultSet rs) throws SQLException {
        return new Province(
                rs.getString("provId"),
                rs.getString("provName"),
                rs.getString("regId")
        );
    }
}