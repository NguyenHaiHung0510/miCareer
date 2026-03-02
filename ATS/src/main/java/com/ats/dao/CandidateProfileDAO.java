package com.ats.dao;

import com.ats.context.DBContext;
import com.ats.model.CandidateProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateProfileDAO implements CrudDAO<CandidateProfile, Long> {

    private CandidateProfile mapResultSetToCandidateProfile(ResultSet rs) throws SQLException {
        CandidateProfile cp = new CandidateProfile();

        cp.setId(rs.getObject("id", Long.class));
        cp.setUserId(rs.getObject("user_id", Long.class));

        cp.setFullName(rs.getString("full_name"));
        cp.setEmail(rs.getString("email"));
        cp.setPhone(rs.getString("phone"));
        cp.setSummary(rs.getString("summary"));

        cp.setTotalExperienceYears(rs.getObject("total_experience_years", Integer.class));
        cp.setCurrentPosition(rs.getString("current_position"));
        cp.setCurrentCompany(rs.getString("current_company"));
        cp.setLocation(rs.getString("location"));

        cp.setCvFileUrl(rs.getString("cv_file_url"));

        Timestamp createdTs = rs.getTimestamp("created_at");
        cp.setCreatedAt(createdTs != null ? createdTs.toLocalDateTime() : null);

        Timestamp updatedTs = rs.getTimestamp("updated_at");
        cp.setUpdatedAt(updatedTs != null ? updatedTs.toLocalDateTime() : null);

        cp.setActive(rs.getObject("active", Boolean.class));

        return cp;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    @Override
    public Long insert(CandidateProfile cp) throws SQLException {

        String sql = """
                INSERT INTO CANDIDATE_PROFILE
                (user_id, full_name, email, phone, summary,
                 total_experience_years, current_position,
                 current_company, location, cv_file_url,
                 created_at, updated_at, active)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, cp.getUserId());
            ps.setString(2, cp.getFullName());
            ps.setString(3, cp.getEmail());
            ps.setString(4, cp.getPhone());
            ps.setString(5, cp.getSummary());

            if (cp.getTotalExperienceYears() != null) {
                ps.setInt(6, cp.getTotalExperienceYears());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setString(7, cp.getCurrentPosition());
            ps.setString(8, cp.getCurrentCompany());
            ps.setString(9, cp.getLocation());
            ps.setString(10, cp.getCvFileUrl());

            if (cp.getCreatedAt() != null) {
                ps.setTimestamp(11, Timestamp.valueOf(cp.getCreatedAt()));
            } else {
                ps.setNull(11, Types.TIMESTAMP);
            }

            if (cp.getUpdatedAt() != null) {
                ps.setTimestamp(12, Timestamp.valueOf(cp.getUpdatedAt()));
            } else {
                ps.setNull(12, Types.TIMESTAMP);
            }

            ps.setObject(13, cp.getActive(), Types.BOOLEAN);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    cp.setId(id);
                    return id;
                }
            }
        }

        return null;
    }


    @Override
    public CandidateProfile getById(Long id) throws SQLException {

        String sql = "SELECT * FROM CANDIDATE_PROFILE WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCandidateProfile(rs);
            }
        }
        return null;
    }


    public CandidateProfile findByUserId(Long userId) throws SQLException {

        String sql = "SELECT * FROM CANDIDATE_PROFILE WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCandidateProfile(rs);
            }
        }
        return null;
    }

    @Override
    public List<CandidateProfile> getAll() throws SQLException {

        String sql = "SELECT * FROM CANDIDATE_PROFILE";
        List<CandidateProfile> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToCandidateProfile(rs));
            }
        }

        return list;
    }

    @Override
    public boolean update(CandidateProfile cp) throws SQLException {

        String sql = """
                UPDATE CANDIDATE_PROFILE
                SET full_name = ?,
                    email = ?,
                    phone = ?,
                    summary = ?,
                    total_experience_years = ?,
                    current_position = ?,
                    current_company = ?,
                    location = ?,
                    cv_file_url = ?,
                    updated_at = ?,
                    active = ?
                WHERE id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cp.getFullName());
            ps.setString(2, cp.getEmail());
            ps.setString(3, cp.getPhone());
            ps.setString(4, cp.getSummary());

            if (cp.getTotalExperienceYears() != null) {
                ps.setInt(5, cp.getTotalExperienceYears());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, cp.getCurrentPosition());
            ps.setString(7, cp.getCurrentCompany());
            ps.setString(8, cp.getLocation());
            ps.setString(9, cp.getCvFileUrl());

            if (cp.getUpdatedAt() != null) {
                ps.setTimestamp(10, Timestamp.valueOf(cp.getUpdatedAt()));
            } else {
                ps.setNull(10, Types.TIMESTAMP);
            }

            ps.setObject(11, cp.getActive(), Types.BOOLEAN);
            ps.setLong(12, cp.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException {

        String sql = "DELETE FROM CANDIDATE_PROFILE WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}