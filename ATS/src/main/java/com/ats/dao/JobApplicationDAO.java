package com.ats.dao;

import com.ats.context.DBContext;
import com.ats.model.JobApplication;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO implements CrudDAO<JobApplication, Long> {

    private JobApplication mapResultSetToJobApplication(ResultSet rs) throws SQLException {
        JobApplication app = new JobApplication();

        app.setId(rs.getObject("id", Long.class));
        app.setCandidateProfileId(rs.getObject("candidate_profile_id", Long.class));
        app.setJobPostingId(rs.getObject("job_posting_id", Long.class));

        app.setStatus(rs.getString("status"));
        app.setCvSnapshotJson(rs.getString("cv_snapshot_json"));

        app.setExpectedSalary(rs.getBigDecimal("expected_salary"));
        app.setSource(rs.getString("source"));

        Timestamp appliedTs = rs.getTimestamp("applied_at");
        app.setAppliedAt(appliedTs != null ? appliedTs.toLocalDateTime() : null);

        Timestamp updatedTs = rs.getTimestamp("updated_at");
        app.setUpdatedAt(updatedTs != null ? updatedTs.toLocalDateTime() : null);

        app.setActive(rs.getObject("active", Boolean.class));

        return app;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    @Override
    public Long insert(JobApplication app) throws SQLException {
        String sql = """
                INSERT INTO JOB_APPLICATION
                (candidate_profile_id, job_posting_id, status, cv_snapshot_json,
                 expected_salary, source, applied_at, updated_at, active)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, app.getCandidateProfileId());
            ps.setLong(2, app.getJobPostingId());
            ps.setString(3, app.getStatus());
            ps.setString(4, app.getCvSnapshotJson());

            if (app.getExpectedSalary() != null) {
                ps.setBigDecimal(5, app.getExpectedSalary());
            } else {
                ps.setNull(5, Types.DECIMAL);
            }

            ps.setString(6, app.getSource());

            if (app.getAppliedAt() != null) {
                ps.setTimestamp(7, Timestamp.valueOf(app.getAppliedAt()));
            } else {
                ps.setNull(7, Types.TIMESTAMP);
            }

            if (app.getUpdatedAt() != null) {
                ps.setTimestamp(8, Timestamp.valueOf(app.getUpdatedAt()));
            } else {
                ps.setNull(8, Types.TIMESTAMP);
            }

            ps.setObject(9, app.getActive(), Types.BOOLEAN);

            Integer affectedRows = ps.executeUpdate();
            
            if (affectedRows == 0) {
                return null;
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }
        return null;
    }

    @Override
    public JobApplication getById(Long id) throws SQLException {
        String sql = "SELECT * FROM JOB_APPLICATION WHERE id = ?";

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

    @Override
    public boolean update(JobApplication app) throws SQLException {
        String sql = """
                UPDATE JOB_APPLICATION
                SET status = ?,
                    expected_salary = ?,
                    source = ?,
                    updated_at = ?,
                    active = ?
                WHERE id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, app.getStatus());

            if (app.getExpectedSalary() != null) {
                ps.setBigDecimal(2, app.getExpectedSalary());
            } else {
                ps.setNull(2, Types.DECIMAL);
            }

            ps.setString(3, app.getSource());

            if (app.getUpdatedAt() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(app.getUpdatedAt()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            ps.setObject(5, app.getActive(), Types.BOOLEAN);

            ps.setLong(6, app.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM JOB_APPLICATION WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}