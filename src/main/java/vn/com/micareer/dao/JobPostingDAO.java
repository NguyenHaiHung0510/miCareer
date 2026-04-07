package vn.com.micareer.dao;

import java.sql.*;
import java.util.*;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobPosting;

public class JobPostingDAO {

    // ================= GET JOBS BY HR =================
    public List<JobPosting> getByHr(int hrId) {
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

            ps.setInt(1, hrId);

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
    public JobPosting getById(int jobPostId) {
        String sql = """
            SELECT j.*, c.compName, cat.catName, l.levelName
            FROM JobPosting j
            LEFT JOIN Company c ON j.compId = c.compId
            LEFT JOIN JobCategory cat ON j.catId = cat.catId
            LEFT JOIN JobLevel l ON j.levelId = l.levelId
            WHERE j.jobPostId=?
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobPostId);

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
    public int insert(JobPosting job) {

        String sql = """
            INSERT INTO JobPosting
            (compId, hrId, catId, levelId, title, `desc`,
             minSalary, maxSalary, workLoc, workMode, stat, expAt)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, job.getCompId());
            ps.setInt(2, job.getHrId());
            ps.setInt(3, job.getCatId());
            ps.setInt(4, job.getLevelId());
            ps.setString(5, job.getTitle());
            ps.setString(6, job.getDesc());
            ps.setBigDecimal(7, job.getMinSalary());
            ps.setBigDecimal(8, job.getMaxSalary());
            ps.setString(9, job.getWorkLoc());
            ps.setString(10, job.getWorkMode());
            ps.setString(11, job.getStat()); // nhớ: DRAFT, PUBLISHED...
            ps.setTimestamp(12, job.getExpAt());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("insert error: " + e.getMessage());
        }

        return -1;
    }

    // ================= UPDATE =================
    public void update(JobPosting job) {

        String sql = """
            UPDATE JobPosting
            SET title=?, `desc`=?, minSalary=?, maxSalary=?,
                workLoc=?, workMode=?, expAt=?, catId=?, levelId=?
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
            ps.setInt(8, job.getCatId());
            ps.setInt(9, job.getLevelId());
            ps.setInt(10, job.getJobPostId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("update error: " + e.getMessage());
        }
    }

    // ================= UPDATE STATUS =================
    public void updateStatus(int jobPostId, String status) {

        String sql = "UPDATE JobPosting SET stat=? WHERE jobPostId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, jobPostId);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateStatus error: " + e.getMessage());
        }
    }

    // ================= MAP =================
    private JobPosting map(ResultSet rs) throws SQLException {

        JobPosting j = new JobPosting();

        j.setJobPostId(rs.getInt("jobPostId"));
        j.setCompId(rs.getInt("compId"));
        j.setHrId(rs.getInt("hrId"));
        j.setCatId(rs.getInt("catId"));
        j.setLevelId(rs.getInt("levelId"));

        j.setTitle(rs.getString("title"));
        j.setDesc(rs.getString("desc"));
        j.setMinSalary(rs.getBigDecimal("minSalary"));
        j.setMaxSalary(rs.getBigDecimal("maxSalary"));
        j.setWorkLoc(rs.getString("workLoc"));
        j.setWorkMode(rs.getString("workMode"));
        j.setStat(rs.getString("stat"));
        j.setCreatedAt(rs.getTimestamp("createdAt"));
        j.setExpAt(rs.getTimestamp("expAt"));

        j.setCompName(rs.getString("compName"));
        j.setCatName(rs.getString("catName"));
        j.setLevelName(rs.getString("levelName"));

        return j;
    }
}
