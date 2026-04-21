package vn.com.micareer.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Offer;

public class OfferDAO {

    /**
     * Tạo offer mới. Ver tự tăng dựa trên số offer hiện có của jobApp.
     * Trả về offerId được sinh ra.
     */
    public long create(Offer offer) throws SQLException {
        // Tính version tiếp theo
        int nextVer = getNextVersion(offer.getJobAppId());
        offer.setVer(nextVer);

        String sql = "INSERT INTO Offer (jobAppId, hrId, salary, `desc`, stat, ver) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, offer.getJobAppId());
            ps.setLong(2, offer.getHrId());
            if (offer.getSalary() != null) {
                ps.setBigDecimal(3, offer.getSalary());
            } else {
                ps.setNull(3, java.sql.Types.DECIMAL);
            }
            ps.setString(4, offer.getDesc());
            ps.setString(5, offer.getStat() != null ? offer.getStat() : "PENDING");
            ps.setInt(6, nextVer);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
            }
        }
        return -1;
    }

    /**
     * Lấy toàn bộ lịch sử offer (tất cả version) theo jobAppId.
     */
    public List<Offer> findByJobAppId(long jobAppId) throws SQLException {
        List<Offer> list = new ArrayList<>();
        String sql = "SELECT offerId, jobAppId, hrId, salary, `desc`, stat, ver "
                   + "FROM Offer WHERE jobAppId = ? ORDER BY ver DESC";
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
     * Lấy offer mới nhất (ver cao nhất) của một jobApp.
     */
    public Offer findLatestByJobAppId(long jobAppId) throws SQLException {
        String sql = "SELECT offerId, jobAppId, hrId, salary, `desc`, stat, ver "
                   + "FROM Offer WHERE jobAppId = ? ORDER BY ver DESC LIMIT 1";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobAppId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    private int getNextVersion(long jobAppId) throws SQLException {
        String sql = "SELECT COALESCE(MAX(ver), 0) + 1 AS nextVer FROM Offer WHERE jobAppId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, jobAppId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("nextVer");
            }
        }
        return 1;
    }

    private Offer mapRow(ResultSet rs) throws SQLException {
        Offer o = new Offer();
        o.setOfferId(rs.getLong("offerId"));
        o.setJobAppId(rs.getLong("jobAppId"));
        o.setHrId(rs.getLong("hrId"));
        BigDecimal salary = rs.getBigDecimal("salary");
        if (salary != null) o.setSalary(salary);
        o.setDesc(rs.getString("desc"));
        o.setStat(rs.getString("stat"));
        o.setVer(rs.getInt("ver"));
        return o;
    }

    /**
     * Cập nhật trạng thái offer (ACCEPTED / REJECTED).
     */
    public boolean updateStat(long offerId, String stat) throws SQLException {
        String sql = "UPDATE Offer SET stat = ? WHERE offerId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, stat);
            ps.setLong(2, offerId);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Lấy offer theo offerId (dùng để kiểm tra trạng thái trước khi cập nhật).
     */
    public Offer findById(long offerId) throws SQLException {
        String sql = "SELECT offerId, jobAppId, hrId, salary, `desc`, stat, ver "
                   + "FROM Offer WHERE offerId = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, offerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }
}
