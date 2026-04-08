/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.HRPosition;

import java.util.ArrayList;
import java.util.List;

public class HRPositionDAO implements CrudDAO<HRPosition, Integer> {

    @Override
    public Integer insert(HRPosition pos) throws SQLException {
        String sql = "INSERT INTO HRPosition(posName, `desc`) VALUES (?,?)";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pos.getPosName());
            ps.setString(2, pos.getDesc());
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
    public boolean update(HRPosition pos) throws SQLException {
        String sql = "UPDATE HRPosition SET posName=?, `desc`=? WHERE posId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pos.getPosName());
            ps.setString(2, pos.getDescription());
            ps.setInt(3, pos.getPosId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM HRPosition WHERE posId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public HRPosition getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM HRPosition WHERE posId=?";
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
    public List<HRPosition> getAll() throws SQLException {
        List<HRPosition> list = new ArrayList<>();
        String sql = "SELECT * FROM HRPosition";
        try (Connection con = DBContext.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private HRPosition map(ResultSet rs) throws SQLException {
        return new HRPosition(
                rs.getInt("posId"),
                rs.getString("posName"),
                rs.getString("desc")
        );
    }
}
