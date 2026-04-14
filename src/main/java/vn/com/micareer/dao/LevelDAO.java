package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Level;

public class LevelDAO {

    public List<Level> getAll() {

        List<Level> list = new ArrayList<>();
        String sql = "SELECT levelId, levelName FROM JobLevel";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Level l = new Level();
                l.setLevelId(rs.getInt("levelId"));   // ✅ FIX
                l.setLevelName(rs.getString("levelName"));
                list.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
