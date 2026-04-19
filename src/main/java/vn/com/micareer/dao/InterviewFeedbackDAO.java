package vn.com.micareer.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.InterviewFeedback;

public class InterviewFeedbackDAO {

    /**
     * Tạo feedback phỏng vấn. Trả về feedbackId được sinh ra.
     */
    public long create(InterviewFeedback fb) throws SQLException {
        String sql = "INSERT INTO InterviewFeedback (intervId, hrId, score, cmt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, fb.getIntervId());
            ps.setLong(2, fb.getHrId());
            if (fb.getScore() != null) {
                ps.setBigDecimal(3, fb.getScore());
            } else {
                ps.setNull(3, java.sql.Types.DECIMAL);
            }
            ps.setString(4, fb.getCmt());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
            }
        }
        return -1;
    }

    /**
     * Lấy tất cả feedback của một vòng phỏng vấn.
     */
    public List<InterviewFeedback> findByIntervId(long intervId) throws SQLException {
        List<InterviewFeedback> list = new ArrayList<>();
        String sql = "SELECT feedbackId, intervId, hrId, score, cmt, subAt "
                   + "FROM InterviewFeedback WHERE intervId = ? ORDER BY subAt DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, intervId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InterviewFeedback fb = new InterviewFeedback();
                    fb.setFeedbackId(rs.getLong("feedbackId"));
                    fb.setIntervId(rs.getLong("intervId"));
                    fb.setHrId(rs.getLong("hrId"));
                    BigDecimal score = rs.getBigDecimal("score");
                    if (score != null) fb.setScore(score);
                    fb.setCmt(rs.getString("cmt"));
                    Timestamp subAt = rs.getTimestamp("subAt");
                    if (subAt != null) fb.setSubAt(subAt.toLocalDateTime());
                    list.add(fb);
                }
            }
        }
        return list;
    }
}
