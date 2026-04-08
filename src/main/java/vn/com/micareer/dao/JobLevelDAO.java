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
import vn.com.micareer.model.JobLevel;

import java.util.ArrayList;
import java.util.List;

public class JobLevelDAO implements CrudDAO<JobLevel, Integer> {

    @Override
    public Integer insert(JobLevel level) throws SQLException {
        String sql = "INSERT INTO JobLevel(levelName, `desc`) VALUES (?,?)";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, level.getLevelName());
            ps.setString(2, level.getDesc());
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
    public boolean update(JobLevel level) throws SQLException {
        String sql = "UPDATE JobLevel SET levelName=?, `desc`=? WHERE levelId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, level.getLevelName());
            ps.setString(2, level.getDesc());
            ps.setInt(3, level.getLevelId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM JobLevel WHERE levelId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public JobLevel getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM JobLevel WHERE levelId=?";
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
    public List<JobLevel> getAll() throws SQLException {
        List<JobLevel> list = new ArrayList<>();
        String sql = "SELECT * FROM JobLevel";
        try (Connection con = DBContext.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private JobLevel map(ResultSet rs) throws SQLException {
        return new JobLevel(
                rs.getInt("levelId"),
                rs.getString("levelName"),
                rs.getString("desc")
        );
    }
}
