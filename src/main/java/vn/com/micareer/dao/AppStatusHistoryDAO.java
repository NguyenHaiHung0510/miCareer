package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.AppStatusHistory;

public class AppStatusHistoryDAO {

    public boolean insertHistory(AppStatusHistory history) throws SQLException {
        String sql = "INSERT INTO AppStatusHistory (jobAppId, hrId, oldStat, newStat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, history.getJobAppId());
            ps.setLong(2, history.getHrId());
            ps.setString(3, history.getOldStat());
            ps.setString(4, history.getNewStat());
            return ps.executeUpdate() > 0;
        }
    }
}
