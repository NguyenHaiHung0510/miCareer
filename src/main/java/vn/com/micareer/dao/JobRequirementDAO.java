package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Skill;

public class JobRequirementDAO {

    // ================= INSERT MULTI SKILL =================
    public void insert(int jobPostId, List<Integer> skillIds) {

        String sql = "INSERT INTO JobRequirement (jobPostId, skillId) VALUES (?, ?)";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int skillId : skillIds) {
                ps.setInt(1, jobPostId);
                ps.setInt(2, skillId);
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (Exception e) {
            System.out.println("insert requirement error: " + e.getMessage());
        }
    }
    
    // ================= GET SKILLS BY JOB =================
    public List<String> getSkillsByJob(int jobPostId) {

        List<String> list = new ArrayList<>();

        String sql = """
            SELECT s.skillName
            FROM JobRequirement jr
            JOIN Skill s ON jr.skillId = s.skillId
            WHERE jr.jobPostId = ?
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobPostId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("skillName"));
            }

        } catch (Exception e) {
            System.out.println("getSkills error: " + e.getMessage());
        }

        return list;
    }

    // ================= DELETE ALL SKILLS OF JOB =================
    public void deleteByJob(int jobPostId) {

        String sql = "DELETE FROM JobRequirement WHERE jobPostId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobPostId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("delete requirement error: " + e.getMessage());
        }
    }

    // ================= GET ALL SKILLS =================
    public List<Skill> getAllSkills() {

        List<Skill> list = new ArrayList<>();
        String sql = "SELECT skillId, skillName FROM Skill";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Skill s = new Skill();
                s.setSkillId(rs.getInt("skillId"));   // ✅ FIX
                s.setSkillName(rs.getString("skillName"));
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("getAllSkills error: " + e.getMessage());
        }

        return list;
    }
}
