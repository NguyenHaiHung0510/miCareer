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
import vn.com.micareer.model.JobCategory;

import java.util.ArrayList;
import java.util.List;

public class JobCategoryDAO implements CrudDAO<JobCategory, Integer> {

    @Override
    public Integer insert(JobCategory cat) throws SQLException {
        String sql = "INSERT INTO JobCategory(catName, `desc`) VALUES (?,?)";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cat.getCatName());
            ps.setString(2, cat.getDesc());
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
    public boolean update(JobCategory cat) throws SQLException {
        String sql = "UPDATE JobCategory SET catName=?, `desc`=? WHERE catId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat.getCatName());
            ps.setString(2, cat.getDesc());
            ps.setInt(3, cat.getCatId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM JobCategory WHERE catId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public JobCategory getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM JobCategory WHERE catId=?";
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
    public List<JobCategory> getAll() throws SQLException {
        List<JobCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM JobCategory";
        try (Connection con = DBContext.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private JobCategory map(ResultSet rs) throws SQLException {
        return new JobCategory(
                rs.getInt("catId"),
                rs.getString("catName"),
                rs.getString("desc")
        );
    }
}
