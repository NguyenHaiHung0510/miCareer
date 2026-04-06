package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.JobCardView;
import vn.com.micareer.model.JobDetailView;
import vn.com.micareer.model.JobSearchCriteria;
import vn.com.micareer.model.LookupItemView;

public class JobPostingDAO {

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
                .append("WHERE jp.stat IN ('PUBLISHED', 'DRAFT') ")
                .append("AND (jp.expAt IS NULL OR jp.expAt >= CURRENT_TIMESTAMP) ");

        appendFilters(sql, params, criteria);

        sql.append(" ORDER BY jp.createdAt DESC ");
        if (limit > 0) {
            sql.append(" LIMIT ? ");
            params.add(limit);
        }

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
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
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                locations.add(rs.getString("workLoc"));
            }
        }
        return locations;
    }

    private List<String> findSkillNamesByJobPostId(long jobPostId) throws SQLException {
        List<String> skills = new ArrayList<>();
        String sql = "SELECT s.skillName FROM JobRequirement jr "
                + "INNER JOIN Skill s ON s.skillId = jr.skillId "
                + "WHERE jr.jobPostId = ? ORDER BY s.skillName";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
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
}
