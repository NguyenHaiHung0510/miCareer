/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Candidate;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO implements CrudDAO<Candidate, Long> {

    /* =========================
       MAP RESULT SET
       ========================= */
    private Candidate mapResultSetToCandidate(ResultSet rs) throws SQLException {

        Candidate c = new Candidate();

        c.setUserId(rs.getObject("user_id", Long.class));
        c.setBio(rs.getString("bio"));
        c.setCvUrl(rs.getString("cv_url"));

        Date dob = rs.getDate("dob");
        c.setDob(dob != null ? dob.toLocalDate() : null);

        c.setExpYears(rs.getObject("exp_years", Integer.class));

        return c;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    /* =========================
       INSERT
       ========================= */
    @Override
    public Long insert(Candidate c) throws SQLException {

        String sql = """
                INSERT INTO CANDIDATE
                (user_id, bio, cv_url, dob, exp_years)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, c.getUserId());
            ps.setString(2, c.getBio());
            ps.setString(3, c.getCvUrl());

            if (c.getDob() != null) {
                ps.setDate(4, Date.valueOf(c.getDob()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            if (c.getExpYears() != null) {
                ps.setInt(5, c.getExpYears());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? c.getUserId() : null;
        }
    }

    /* =========================
       GET BY ID (userId)
       ========================= */
    @Override
    public Candidate getById(Long userId) throws SQLException {

        String sql = "SELECT * FROM CANDIDATE WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCandidate(rs);
            }
        }

        return null;
    }

    /* =========================
       GET ALL
       ========================= */
    @Override
    public List<Candidate> getAll() throws SQLException {

        String sql = "SELECT * FROM CANDIDATE";
        List<Candidate> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToCandidate(rs));
            }
        }

        return list;
    }

    /* =========================
       UPDATE
       ========================= */
    @Override
    public boolean update(Candidate c) throws SQLException {

        String sql = """
                UPDATE CANDIDATE
                SET bio = ?,
                    cv_url = ?,
                    dob = ?,
                    exp_years = ?
                WHERE user_id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getBio());
            ps.setString(2, c.getCvUrl());

            if (c.getDob() != null) {
                ps.setDate(3, Date.valueOf(c.getDob()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            if (c.getExpYears() != null) {
                ps.setInt(4, c.getExpYears());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setLong(5, c.getUserId());

            return ps.executeUpdate() > 0;
        }
    }

    /* =========================
       DELETE
       ========================= */
    @Override
    public boolean delete(Long userId) throws SQLException {

        String sql = "DELETE FROM CANDIDATE WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            return ps.executeUpdate() > 0;
        }
    }
}