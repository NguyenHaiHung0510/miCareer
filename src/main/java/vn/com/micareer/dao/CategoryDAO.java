package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Category;

public class CategoryDAO {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT catId, catName FROM JobCategory";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setCatId(rs.getInt("catId"));
                c.setCatName(rs.getString("catName"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
