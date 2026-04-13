package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobApplication;

public class JobApplicationDAO {

    public int countByJobPostId(long jobPostId) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM JobApplication WHERE jobPostId = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobPostId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

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
}
