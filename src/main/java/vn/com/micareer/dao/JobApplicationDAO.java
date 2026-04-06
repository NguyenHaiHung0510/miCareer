package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobApplication;

public class JobApplicationDAO {

    public boolean existsByJobAndCandidate(long jobPostId, long candidateId) throws SQLException {
        String sql = "SELECT 1 FROM JobApplication WHERE jobPostId = ? AND candidateId = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobPostId);
            statement.setLong(2, candidateId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        }
    }

    public long create(JobApplication application) throws SQLException {
        String sql = "INSERT INTO JobApplication (jobPostId, candidateId, stat, cvSnapUrl, coverLetter) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, application.getJobPostId());
            statement.setLong(2, application.getCandidateId());
            statement.setString(3, application.getStat());
            statement.setString(4, application.getCvSnapUrl());
            statement.setString(5, application.getCoverLetter());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
        }
        return -1;
    }
    //Module5 Lấy danh sách hồ sơ ứng tuyển theo mã bài đăng tuyển dụng
    public java.util.List<JobApplication> findByJobPostId(long jobPostId) throws SQLException {
        java.util.List<JobApplication> list = new java.util.ArrayList<>();
        String sql = "SELECT jobAppId, jobPostId, candidateId, appliedAt, stat, cvSnapUrl, coverLetter "
                   + "FROM JobApplication WHERE jobPostId = ? ORDER BY appliedAt DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobPostId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    JobApplication app = new JobApplication();
                    app.setJobAppId(rs.getLong("jobAppId"));
                    app.setJobPostId(rs.getLong("jobPostId"));
                    app.setCandidateId(rs.getLong("candidateId"));
                    java.sql.Timestamp appliedAt = rs.getTimestamp("appliedAt");
                    if (appliedAt != null) app.setAppliedAt(appliedAt.toLocalDateTime());
                    app.setStat(rs.getString("stat"));
                    app.setCvSnapUrl(rs.getString("cvSnapUrl"));
                    app.setCoverLetter(rs.getString("coverLetter"));
                    list.add(app);
                }
            }
        }
        return list;
    }

    //Module5 Cập nhật trạng thái của đơn ứng tuyển
    public boolean updateStatus(long jobAppId, String newStatus) throws SQLException {
        String sql = "UPDATE JobApplication SET stat = ? WHERE jobAppId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setLong(2, jobAppId);
            return ps.executeUpdate() > 0;
        }
    }
}
