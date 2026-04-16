package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vn.com.micareer.context.DBContext;

public class HRDAO {

    public Integer getCompIdByHr(int hrId) {
        String sql = "SELECT compId FROM HR WHERE hrId = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hrId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("compId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
