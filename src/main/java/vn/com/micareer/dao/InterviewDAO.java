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
import vn.com.micareer.model.Interview;

public class InterviewDAO {

    /**
     * Tạo lịch phỏng vấn mới. Trả về intervId được sinh ra.
     */
    public long create(Interview interview) throws SQLException {
        String sql = "INSERT INTO Interview (jobAppId, title, startAt, endAt, stat, mode, linkMeet, loc) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, interview.getJobAppId());
            ps.setString(2, interview.getTitle());
            ps.setTimestamp(3, interview.getStartAt() != null ? Timestamp.valueOf(interview.getStartAt()) : null);
            ps.setTimestamp(4, interview.getEndAt() != null ? Timestamp.valueOf(interview.getEndAt()) : null);
            ps.setString(5, interview.getStat() != null ? interview.getStat() : "SCHEDULED");
            ps.setString(6, interview.getMode());
            ps.setString(7, interview.getLinkMeet());
            ps.setString(8, interview.getLoc());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
            }
        }
        return -1;
    }

    /**
     * Lấy danh sách phỏng vấn theo jobAppId, sắp xếp theo thời gian bắt đầu.
     */
    public List<Interview> findByJobAppId(long jobAppId) throws SQLException {
        List<Interview> list = new ArrayList<>();
        String sql = "SELECT intervId, jobAppId, title, startAt, endAt, stat, mode, linkMeet, loc "
                   + "FROM Interview WHERE jobAppId = ? ORDER BY startAt DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobAppId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    /**
     * Cập nhật trạng thái phỏng vấn (COMPLETED / CANCELED).
     */
    public boolean updateStat(long intervId, String stat) throws SQLException {
        String sql = "UPDATE Interview SET stat = ? WHERE intervId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, stat);
            ps.setLong(2, intervId);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Lấy một phỏng vấn theo intervId.
     */
    public Interview findById(long intervId) throws SQLException {
        String sql = "SELECT intervId, jobAppId, title, startAt, endAt, stat, mode, linkMeet, loc "
                   + "FROM Interview WHERE intervId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, intervId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    private Interview mapRow(ResultSet rs) throws SQLException {
        Interview i = new Interview();
        i.setIntervId(rs.getLong("intervId"));
        i.setJobAppId(rs.getLong("jobAppId"));
        i.setTitle(rs.getString("title"));
        Timestamp startAt = rs.getTimestamp("startAt");
        if (startAt != null) i.setStartAt(startAt.toLocalDateTime());
        Timestamp endAt = rs.getTimestamp("endAt");
        if (endAt != null) i.setEndAt(endAt.toLocalDateTime());
        i.setStat(rs.getString("stat"));
        i.setMode(rs.getString("mode"));
        i.setLinkMeet(rs.getString("linkMeet"));
        i.setLoc(rs.getString("loc"));
        return i;
    }
}
