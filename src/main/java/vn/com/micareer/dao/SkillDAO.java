/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO implements CrudDAO<Skill, Integer> {

    @Override
    public Integer insert(Skill s) throws SQLException {
        String sql = "INSERT INTO Skill(skillName, `desc`) VALUES (?,?)";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getSkillName());
            ps.setString(2, s.getDesc());
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
    public boolean update(Skill s) throws SQLException {
        String sql = "UPDATE Skill SET skillName=?, `desc`=? WHERE skillId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getSkillName());
            ps.setString(2, s.getDesc());
            ps.setInt(3, s.getSkillId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Skill WHERE skillId=?";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Skill getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Skill WHERE skillId=?";
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
    public List<Skill> getAll() throws SQLException {
        List<Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill";
        try (Connection con = DBContext.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private Skill map(ResultSet rs) throws SQLException {
        return new Skill(
                rs.getInt("skillId"),
                rs.getString("skillName"),
                rs.getString("desc")
        );
    }
}
