/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO implements CrudDAO<JobApplication, Long> {

    /* =========================
       MAP RESULT SET
       ========================= */
    private JobApplication mapResultSetToJobApplication(ResultSet rs) throws SQLException {

        JobApplication app = new JobApplication();

        app.setJobAppId(rs.getObject("job_app_id", Long.class));
        app.setJobPostId(rs.getObject("job_post_id", Long.class));
        app.setCandidateId(rs.getObject("candidate_id", Long.class));

        app.setStat(rs.getString("stat"));
        app.setCvSnapUrl(rs.getString("cv_snap_url"));
        app.setCoverLetter(rs.getString("cover_letter"));

        Timestamp appliedTs = rs.getTimestamp("applied_at");
        app.setAppliedAt(appliedTs != null ? appliedTs.toLocalDateTime() : null);

        return app;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    /* =========================
       INSERT
       ========================= */
    @Override
    public Long insert(JobApplication app) throws SQLException {

        String sql = """
                INSERT INTO JOB_APPLICATION
                (job_post_id, candidate_id, applied_at, stat, cv_snap_url, cover_letter)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, app.getJobPostId());
            ps.setLong(2, app.getCandidateId());

            if (app.getAppliedAt() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(app.getAppliedAt()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }

            ps.setString(4, app.getStat());
            ps.setString(5, app.getCvSnapUrl());
            ps.setString(6, app.getCoverLetter());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    app.setJobAppId(id);
                    return id;
                }
            }
        }

        return null;
    }

    /* =========================
       GET BY ID
       ========================= */
    @Override
    public JobApplication getById(Long id) throws SQLException {

        String sql = "SELECT * FROM JOB_APPLICATION WHERE job_app_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToJobApplication(rs);
            }
        }

        return null;
    }

    /* =========================
       GET ALL
       ========================= */
    @Override
    public List<JobApplication> getAll() throws SQLException {

        String sql = "SELECT * FROM JOB_APPLICATION";
        List<JobApplication> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToJobApplication(rs));
            }
        }

        return list;
    }

    /* =========================
       UPDATE
       ========================= */
    @Override
    public boolean update(JobApplication app) throws SQLException {

        String sql = """
                UPDATE JOB_APPLICATION
                SET stat = ?,
                    cv_snap_url = ?,
                    cover_letter = ?
                WHERE job_app_id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, app.getStat());
            ps.setString(2, app.getCvSnapUrl());
            ps.setString(3, app.getCoverLetter());
            ps.setLong(4, app.getJobAppId());

            return ps.executeUpdate() > 0;
        }
    }

    /* =========================
       DELETE
       ========================= */
    @Override
    public boolean delete(Long id) throws SQLException {

        String sql = "DELETE FROM JOB_APPLICATION WHERE job_app_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}