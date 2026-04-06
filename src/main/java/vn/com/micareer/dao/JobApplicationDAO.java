package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobApplication;
import vn.com.micareer.model.ApplicationDetailView;

public class JobApplicationDAO {

    // 1. Kiểm tra xem ứng viên đã nộp đơn vào công việc này chưa (module trước)
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

    // 2. Tạo đơn ứng tuyển mới (module trước)
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

    // 3. Lấy danh sách hồ sơ ứng tuyển theo mã bài đăng tuyển dụng (module5)
    public List<JobApplication> findByJobPostId(long jobPostId) throws SQLException {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT jobAppId, jobPostId, candidateId, appliedAt, stat, cvSnapUrl, coverLetter "
                   + "FROM JobApplication WHERE jobPostId = ? ORDER BY appliedAt DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobPostId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    JobApplication app = new JobApplication();
                    app.setJobAppId(rs.getLong("jobAppId"));
                    app.setJobPostId(rs.getLong("jobPostId"));
                    app.setCandidateId(rs.getLong("candidateId"));
                    Timestamp appliedAt = rs.getTimestamp("appliedAt");
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

    // 4. Cập nhật trạng thái của đơn ứng tuyển (module5)
    public boolean updateStatus(long jobAppId, String newStatus) throws SQLException {
        String sql = "UPDATE JobApplication SET stat = ? WHERE jobAppId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setLong(2, jobAppId);
            return ps.executeUpdate() > 0;
        }
    }

    // 5. Lấy chi tiết đơn ứng tuyển bao gồm thông tin ứng viên (module5)
    public ApplicationDetailView findDetailById(long jobAppId) throws SQLException {
        String sql = "SELECT ja.jobAppId, ja.candidateId, u.fName, u.lName, u.email, u.phone, "
                   + "ja.cvSnapUrl, ja.coverLetter, ja.stat, ja.appliedAt, jp.title "
                   + "FROM JobApplication ja "
                   + "INNER JOIN `User` u ON ja.candidateId = u.userId "
                   + "INNER JOIN JobPosting jp ON ja.jobPostId = jp.jobPostId "
                   + "WHERE ja.jobAppId = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobAppId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ApplicationDetailView detail = new ApplicationDetailView();
                    detail.setJobAppId(rs.getLong("jobAppId"));
                    detail.setCandidateId(rs.getLong("candidateId"));
                    
                    String fName = rs.getString("fName") != null ? rs.getString("fName") : "";
                    String lName = rs.getString("lName") != null ? rs.getString("lName") : "";
                    detail.setCandidateName((fName + " " + lName).trim());
                    
                    detail.setEmail(rs.getString("email"));
                    detail.setPhone(rs.getString("phone"));
                    detail.setCvSnapUrl(rs.getString("cvSnapUrl"));
                    detail.setCoverLetter(rs.getString("coverLetter"));
                    detail.setStat(rs.getString("stat"));
                    detail.setJobTitle(rs.getString("title"));
                    
                    Timestamp appliedAt = rs.getTimestamp("appliedAt");
                    if (appliedAt != null) detail.setAppliedAt(appliedAt.toLocalDateTime());
                    
                    return detail;
                }
            }
        }
        return null;
    }
}
