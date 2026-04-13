package vn.com.micareer.dao;

import java.sql.*;
import java.util.*;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobPosting;

import java.util.List;

import java.util.List;

import java.sql.Connection;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobCardView;
import vn.com.micareer.model.JobDetailView;
import vn.com.micareer.model.JobPosting;
import vn.com.micareer.model.JobSearchCriteria;
import vn.com.micareer.model.LookupItemView;

public class JobPostingDAO {

    public List<JobCardView> findPublishedJobs(JobSearchCriteria criteria, int page, int pageSize) throws SQLException {
        List<JobCardView> jobs = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT jp.jobPostId, jp.title, c.compName, jc.catName, jl.levelName, ")
                .append("jp.workLoc, jp.workMode, jp.minSalary, jp.maxSalary, jp.createdAt, jp.expAt ")
                .append("FROM JobPosting jp ")
                .append("INNER JOIN Company c ON c.compId = jp.compId ")
                .append("LEFT JOIN JobCategory jc ON jc.catId = jp.catId ")
                .append("LEFT JOIN JobLevel jl ON jl.levelId = jp.levelId ")
                .append("LEFT JOIN JobRequirement jr ON jr.jobPostId = jp.jobPostId ")
                .append("WHERE jp.stat IN ('PUBLISHED', 'DRAFT') ")
                .append("AND (jp.expAt IS NULL OR jp.expAt >= CURRENT_TIMESTAMP) ");

        appendFilters(sql, params, criteria);

        sql.append(" ORDER BY jp.createdAt DESC ");

        // Phân trang
        sql.append("LIMIT ? OFFSET ? ");
        params.add(pageSize);
        params.add(page * pageSize);

        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            setParams(statement, params);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    jobs.add(mapJobCard(rs));
                }
            }
        }
        return jobs;
    }

    public List<JobCardView> findPublishedJobs(JobSearchCriteria criteria, int limit) throws SQLException {
        List<JobCardView> jobs = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT jp.jobPostId, jp.title, c.compName, jc.catName, jl.levelName, ")
                .append("jp.workLoc, jp.workMode, jp.minSalary, jp.maxSalary, jp.createdAt, jp.expAt ")
                .append("FROM JobPosting jp ")
                .append("INNER JOIN Company c ON c.compId = jp.compId ")
                .append("LEFT JOIN JobCategory jc ON jc.catId = jp.catId ")
                .append("LEFT JOIN JobLevel jl ON jl.levelId = jp.levelId ")
                .append("LEFT JOIN JobRequirement jr ON jr.jobPostId = jp.jobPostId ")
                .append("WHERE jp.stat IN ('PUBLISHED') ")
                .append("AND (jp.expAt IS NULL OR jp.expAt >= CURRENT_TIMESTAMP) ");

        appendFilters(sql, params, criteria);

        sql.append(" ORDER BY jp.createdAt DESC ");

        // Phân trang
        if (limit > 0) {
            sql.append("LIMIT ? ");
            params.add(limit);
        }

        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            setParams(statement, params);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    jobs.add(mapJobCard(rs));
                }
            }
        }
        return jobs;
    }

    public JobDetailView findJobDetailById(long jobPostId) throws SQLException {
        String sql = "SELECT jp.jobPostId, jp.compId, jp.title, jp.`desc`, jp.minSalary, jp.maxSalary, "
                + "jp.workLoc, jp.workMode, jp.stat, jp.createdAt, jp.expAt, jp.catId, jc.catName, "
                + "jp.levelId, jl.levelName, c.compName, c.webUrl, c.logoUrl "
                + "FROM JobPosting jp "
                + "INNER JOIN Company c ON c.compId = jp.compId "
                + "LEFT JOIN JobCategory jc ON jc.catId = jp.catId "
                + "LEFT JOIN JobLevel jl ON jl.levelId = jp.levelId "
                + "WHERE jp.jobPostId = ? AND jp.stat IN ('PUBLISHED', 'DRAFT')";

        JobDetailView detail = null;
        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobPostId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    detail = new JobDetailView();
                    detail.setJobPostId(rs.getLong("jobPostId"));
                    detail.setCompId(rs.getLong("compId"));
                    detail.setTitle(rs.getString("title"));
                    detail.setDesc(rs.getString("desc"));
                    detail.setMinSalary(rs.getBigDecimal("minSalary"));
                    detail.setMaxSalary(rs.getBigDecimal("maxSalary"));
                    detail.setWorkLoc(rs.getString("workLoc"));
                    detail.setWorkMode(rs.getString("workMode"));
                    detail.setStat(rs.getString("stat"));
                    Timestamp createdAt = rs.getTimestamp("createdAt");
                    if (createdAt != null) {
                        detail.setCreatedAt(createdAt.toLocalDateTime());
                    }
                    Timestamp expAt = rs.getTimestamp("expAt");
                    if (expAt != null) {
                        detail.setExpAt(expAt.toLocalDateTime());
                    }
                    detail.setCatId(rs.getLong("catId"));
                    detail.setCatName(rs.getString("catName"));
                    detail.setLevelId(rs.getLong("levelId"));
                    detail.setLevelName(rs.getString("levelName"));
                    detail.setCompName(rs.getString("compName"));
                    detail.setWebUrl(rs.getString("webUrl"));
                    detail.setLogoUrl(rs.getString("logoUrl"));
                }
            }
        }

        if (detail != null) {
            detail.getSkills().addAll(findSkillNamesByJobPostId(jobPostId));
        }
        return detail;
    }

    public List<LookupItemView> findAllCategories() throws SQLException {
        return findLookups("SELECT catId, catName FROM JobCategory ORDER BY catName", "catId", "catName");
    }

    public List<LookupItemView> findAllLevels() throws SQLException {
        return findLookups("SELECT levelId, levelName FROM JobLevel ORDER BY levelName", "levelId", "levelName");
    }

    public List<LookupItemView> findAllSkills() throws SQLException {
        return findLookups("SELECT skillId, skillName FROM Skill ORDER BY skillName", "skillId", "skillName");
    }

    public List<String> findAllWorkLocations() throws SQLException {
        List<String> locations = new ArrayList<>();
        String sql = "SELECT DISTINCT workLoc FROM JobPosting WHERE workLoc IS NOT NULL AND TRIM(workLoc) <> '' ORDER BY workLoc";
        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                locations.add(rs.getString("workLoc"));
            }
        }
        return locations;
    }

    public int countPublishedJobs(JobSearchCriteria criteria) throws SQLException {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(DISTINCT jp.jobPostId) AS total ")
                .append("FROM JobPosting jp ")
                .append("INNER JOIN Company c ON c.compId = jp.compId ")
                .append("LEFT JOIN JobRequirement jr ON jr.jobPostId = jp.jobPostId ")
                .append("WHERE jp.stat IN ('PUBLISHED', 'DRAFT') ")
                .append("AND (jp.expAt IS NULL OR jp.expAt >= CURRENT_TIMESTAMP) ");

        appendFilters(sql, params, criteria);

        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            setParams(statement, params);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

    private List<String> findSkillNamesByJobPostId(long jobPostId) throws SQLException {
        List<String> skills = new ArrayList<>();
        String sql = "SELECT s.skillName FROM JobRequirement jr "
                + "INNER JOIN Skill s ON s.skillId = jr.skillId "
                + "WHERE jr.jobPostId = ? ORDER BY s.skillName";

        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobPostId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    skills.add(rs.getString("skillName"));
                }
            }
        }
        return skills;
    }

    private void appendFilters(StringBuilder sql, List<Object> params, JobSearchCriteria criteria) {
        if (criteria == null) {
            return;
        }

        if (hasText(criteria.getKeyword())) {
            sql.append("AND (LOWER(jp.title) LIKE ? OR LOWER(jp.`desc`) LIKE ? OR LOWER(c.compName) LIKE ?) ");
            String kw = "%" + criteria.getKeyword().trim().toLowerCase() + "%";
            params.add(kw);
            params.add(kw);
            params.add(kw);
        }

        if (hasText(criteria.getLocation())) {
            sql.append("AND LOWER(jp.workLoc) LIKE ? ");
            params.add("%" + criteria.getLocation().trim().toLowerCase() + "%");
        }

        if (criteria.getCatId() != null && criteria.getCatId() > 0) {
            sql.append("AND jp.catId = ? ");
            params.add(criteria.getCatId());
        }

        if (criteria.getLevelId() != null && criteria.getLevelId() > 0) {
            sql.append("AND jp.levelId = ? ");
            params.add(criteria.getLevelId());
        }

        if (criteria.getSkillId() != null && criteria.getSkillId() > 0) {
            sql.append("AND jr.skillId = ? ");
            params.add(criteria.getSkillId());
        }

        if (hasText(criteria.getWorkMode())) {
            sql.append("AND LOWER(jp.workMode) = ? ");
            params.add(criteria.getWorkMode().trim().toLowerCase());
        }
    }

    private List<LookupItemView> findLookups(String sql, String idColumn, String nameColumn) throws SQLException {
        List<LookupItemView> list = new ArrayList<>();
        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                list.add(new LookupItemView(rs.getLong(idColumn), rs.getString(nameColumn)));
            }
        }
        return list;
    }

    private JobCardView mapJobCard(ResultSet rs) throws SQLException {
        JobCardView item = new JobCardView();
        item.setJobPostId(rs.getLong("jobPostId"));
        item.setTitle(rs.getString("title"));
        item.setCompName(rs.getString("compName"));
        item.setCategoryName(rs.getString("catName"));
        item.setLevelName(rs.getString("levelName"));
        item.setWorkLoc(rs.getString("workLoc"));
        item.setWorkMode(rs.getString("workMode"));
        item.setMinSalary(rs.getBigDecimal("minSalary"));
        item.setMaxSalary(rs.getBigDecimal("maxSalary"));
        Timestamp createdAt = rs.getTimestamp("createdAt");
        if (createdAt != null) {
            item.setCreatedAt(createdAt.toLocalDateTime());
        }
        Timestamp expAt = rs.getTimestamp("expAt");
        if (expAt != null) {
            item.setExpAt(expAt.toLocalDateTime());
        }
        return item;
    }

    private void setParams(PreparedStatement statement, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

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