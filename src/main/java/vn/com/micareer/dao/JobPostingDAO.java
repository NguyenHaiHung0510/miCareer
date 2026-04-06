package vn.com.micareer.dao;

import java.sql.*;
import java.util.*;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobPosting;

public class JobPostingDAO {

    // ================= GET JOBS BY HR =================
    public List<JobPosting> getByHr(String hrId) {
        List<JobPosting> list = new ArrayList<>();
        String sql = """
    SELECT j.*, c.compName, cat.catName, l.levelName
    FROM JobPosting j
    LEFT JOIN Company c ON j.compId = c.compId
    LEFT JOIN JobCategory cat ON j.catId = cat.catId
    LEFT JOIN JobLevel l ON j.levelId = l.levelId
    WHERE j.hrId=?
    ORDER BY j.createdAt DESC
""";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hrId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            System.out.println("getByHr error: " + e.getMessage());
        }

        return list;
    }

    // ================= GET BY ID =================
    public JobPosting getById(String jobPostId) {
        String sql = """
        SELECT j.*, c.compName, cat.catName, l.levelName
        FROM JobPosting j
        LEFT JOIN Company c ON j.compId = c.compId
        LEFT JOIN JobCategory cat ON j.catId = cat.catId
        LEFT JOIN JobLevel l ON j.levelId = l.levelId
        WHERE j.jobPostId=?
    """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jobPostId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (Exception e) {
            System.out.println("getById error: " + e.getMessage());
        }

        return null;
    }

    // ================= INSERT =================
    public String insert(JobPosting job) {
        String sql = """
        INSERT INTO JobPosting
        (jobPostId, compId, hrId, title, `desc`, minSalary, maxSalary,
         workLoc, workMode, stat, expAt, catId, levelId)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, job.getJobPostId()); // UUID đã sinh
            ps.setString(2, job.getCompId());
            ps.setString(3, job.getHrId());
            ps.setString(4, job.getTitle());
            ps.setString(5, job.getDesc());
            ps.setBigDecimal(6, job.getMinSalary());
            ps.setBigDecimal(7, job.getMaxSalary());
            ps.setString(8, job.getWorkLoc());
            ps.setString(9, job.getWorkMode());
            ps.setString(10, job.getStat());
            ps.setTimestamp(11, job.getExpAt());
            ps.setString(12, job.getCatId());
            ps.setString(13, job.getLevelId());

            ps.executeUpdate();
            return job.getJobPostId();

        } catch (Exception e) {
            System.out.println("insert error: " + e.getMessage());
        }
        return null;
    }

    // ================= UPDATE =================
    public void update(JobPosting job) {
        String sql = """
            UPDATE JobPosting
            SET title=?, `desc`=?, minSalary=?, maxSalary=?,
                workLoc=?, workMode=?, expAt=?, catId=?, levelId=?, hrId=?
            WHERE jobPostId=?
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDesc());
            ps.setBigDecimal(3, job.getMinSalary());
            ps.setBigDecimal(4, job.getMaxSalary());
            ps.setString(5, job.getWorkLoc());
            ps.setString(6, job.getWorkMode());
            ps.setTimestamp(7, job.getExpAt());
            ps.setString(8, job.getCatId());
            ps.setString(9, job.getLevelId());
            ps.setString(10, job.getHrId());
            ps.setString(11, job.getJobPostId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("update error: " + e.getMessage());
        }
    }

    // ================= UPDATE STATUS =================
    public void updateStatus(String jobPostId, String status) {
        String sql = "UPDATE JobPosting SET stat=? WHERE jobPostId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, jobPostId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateStatus error: " + e.getMessage());
        }
    }

    // ================= MAP RESULTSET TO OBJECT =================
    private JobPosting map(ResultSet rs) throws SQLException {
        JobPosting j = new JobPosting();
        j.setJobPostId(rs.getString("jobPostId"));
        j.setCompId(rs.getString("compId"));
        j.setHrId(rs.getString("hrId"));      // map hrId
        j.setTitle(rs.getString("title"));
        j.setDesc(rs.getString("desc"));
        j.setMinSalary(rs.getBigDecimal("minSalary"));
        j.setMaxSalary(rs.getBigDecimal("maxSalary"));
        j.setWorkLoc(rs.getString("workLoc"));
        j.setWorkMode(rs.getString("workMode"));
        j.setStat(rs.getString("stat"));
        j.setCreatedAt(rs.getTimestamp("createdAt"));
        j.setExpAt(rs.getTimestamp("expAt"));
        j.setCatId(rs.getString("catId"));
        j.setLevelId(rs.getString("levelId"));

        j.setCompName(rs.getString("compName"));
        j.setCatName(rs.getString("catName"));
        j.setLevelName(rs.getString("levelName"));
        return j;
    }
}
