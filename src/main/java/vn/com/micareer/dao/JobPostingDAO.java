package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Category;
import vn.com.micareer.model.JobCardView;
import vn.com.micareer.model.JobDetailView;
import vn.com.micareer.model.JobPosting;
import vn.com.micareer.model.Level;
import vn.com.micareer.model.LookupItemView;
import vn.com.micareer.model.Skill;

public class JobPostingDAO implements CrudDAO<JobPosting, Integer> {

    private static final String SELECT_JOB_WITH_LOOKUPS = """
        SELECT j.*, c.compName, cat.catName, l.levelName
        FROM JobPosting j
        LEFT JOIN Company c ON j.compId = c.compId
        LEFT JOIN JobCategory cat ON j.catId = cat.catId
        LEFT JOIN JobLevel l ON j.levelId = l.levelId
        """;

    // MODULE 5
    public List<JobCardView> findByHrId(long hrId) throws SQLException {
        List<JobCardView> jobs = new ArrayList<>();
        String sql = "SELECT DISTINCT jp.jobPostId, jp.title, c.compName, jc.catName, jl.levelName, "
                   + "jp.workLoc, jp.workMode, jp.minSalary, jp.maxSalary, jp.createdAt, jp.expAt "
                   + "FROM JobPosting jp "
                   + "INNER JOIN Company c ON c.compId = jp.compId "
                   + "LEFT JOIN JobCategory jc ON jc.catId = jp.catId "
                   + "LEFT JOIN JobLevel jl ON jl.levelId = jp.levelId "
                   + "WHERE jp.hrId = ? "
                   + "ORDER BY jp.createdAt DESC";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, hrId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    jobs.add(mapJobCard(rs));
                }
            }
        }
        return jobs;
    }
    // ============================================================

    public List<JobCardView> findPublishedJobs(String keyword, String location, String workMode, Long catId, Long levelId, Long skillId, int page, int pageSize) throws SQLException {
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

        if (hasText(keyword)) {
            sql.append("AND (LOWER(jp.title) LIKE ? OR LOWER(jp.`desc`) LIKE ? OR LOWER(c.compName) LIKE ?) ");
            String kw = "%" + keyword.trim().toLowerCase() + "%";
            params.add(kw);
            params.add(kw);
            params.add(kw);
        }

        if (hasText(location)) {
            sql.append("AND LOWER(jp.workLoc) LIKE ? ");
            params.add("%" + location.trim().toLowerCase() + "%");
        }

        if (catId != null && catId > 0) {
            sql.append("AND jp.catId = ? ");
            params.add(catId);
        }

        if (levelId != null && levelId > 0) {
            sql.append("AND jp.levelId = ? ");
            params.add(levelId);
        }

        if (skillId != null && skillId > 0) {
            sql.append("AND jr.skillId = ? ");
            params.add(skillId);
        }

        if (hasText(workMode)) {
            sql.append("AND LOWER(jp.workMode) = ? ");
            params.add(workMode.trim().toLowerCase());
        }

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

    public JobDetailView findJobDetailById(long jobPostId) throws SQLException {
        String sql = "SELECT jp.jobPostId, jp.compId, jp.title, jp.`desc`, jp.minSalary, jp.maxSalary, "
                + "jp.workLoc, jp.workMode, jp.stat, jp.createdAt, jp.expAt, jp.catId, jc.catName, "
                + "jp.levelId, jl.levelName, c.compName, c.webUrl, c.logoUrl "
                + "FROM JobPosting jp "
                + "INNER JOIN Company c ON c.compId = jp.compId "
                + "LEFT JOIN JobCategory jc ON jc.catId = jp.catId "
                + "LEFT JOIN JobLevel jl ON jl.levelId = jp.levelId "
                // HOÀNG LƯU Ý: Thêm 'DRAFT' vào đây để HR có thể xem chi tiết Job đang nháp
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
        List<LookupItemView> items = new ArrayList<>();
        for (Category category : new CategoryDAO().getAll()) {
            items.add(new LookupItemView(category.getCatId(), category.getCatName()));
        }
        items.sort(Comparator.comparing(LookupItemView::getName, String.CASE_INSENSITIVE_ORDER));
        return items;
    }

    public List<LookupItemView> findAllLevels() throws SQLException {
        List<LookupItemView> items = new ArrayList<>();
        for (Level level : new LevelDAO().getAll()) {
            items.add(new LookupItemView(level.getLevelId(), level.getLevelName()));
        }
        items.sort(Comparator.comparing(LookupItemView::getName, String.CASE_INSENSITIVE_ORDER));
        return items;
    }

    public List<LookupItemView> findAllSkills() throws SQLException {
        List<LookupItemView> items = new ArrayList<>();
        for (Skill skill : new SkillDAO().getAll()) {
            items.add(new LookupItemView(skill.getSkillId(), skill.getSkillName()));
        }
        items.sort(Comparator.comparing(LookupItemView::getName, String.CASE_INSENSITIVE_ORDER));
        return items;
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

    public int countPublishedJobs(String keyword, String location, String workMode, Long catId, Long levelId, Long skillId) throws SQLException {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(DISTINCT jp.jobPostId) AS total ")
                .append("FROM JobPosting jp ")
                .append("INNER JOIN Company c ON c.compId = jp.compId ")
                .append("LEFT JOIN JobRequirement jr ON jr.jobPostId = jp.jobPostId ")
                .append("WHERE jp.stat IN ('PUBLISHED') ")
                .append("AND (jp.expAt IS NULL OR jp.expAt >= CURRENT_TIMESTAMP) ");

        if (hasText(keyword)) {
            sql.append("AND (LOWER(jp.title) LIKE ? OR LOWER(jp.`desc`) LIKE ? OR LOWER(c.compName) LIKE ?) ");
            String kw = "%" + keyword.trim().toLowerCase() + "%";
            params.add(kw);
            params.add(kw);
            params.add(kw);
        }

        if (hasText(location)) {
            sql.append("AND LOWER(jp.workLoc) LIKE ? ");
            params.add("%" + location.trim().toLowerCase() + "%");
        }

        if (catId != null && catId > 0) {
            sql.append("AND jp.catId = ? ");
            params.add(catId);
        }

        if (levelId != null && levelId > 0) {
            sql.append("AND jp.levelId = ? ");
            params.add(levelId);
        }

        if (skillId != null && skillId > 0) {
            sql.append("AND jr.skillId = ? ");
            params.add(skillId);
        }

        if (hasText(workMode)) {
            sql.append("AND LOWER(jp.workMode) = ? ");
            params.add(workMode.trim().toLowerCase());
        }

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

    @Override
    public List<JobPosting> getAll() {
        List<JobPosting> list = new ArrayList<>();
        String sql = SELECT_JOB_WITH_LOOKUPS + " ORDER BY j.createdAt DESC";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            System.out.println("getAll error: " + e.getMessage());
        }

        return list;
    }

    // ================= GET JOBS BY HR =================
    public List<JobPosting> getByHr(int hrId) {
        List<JobPosting> list = new ArrayList<>();

        String sql = SELECT_JOB_WITH_LOOKUPS
                + " WHERE j.hrId=?"
                + " ORDER BY j.createdAt DESC";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hrId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("getByHr error: " + e.getMessage());
        }

        return list;
    }

    // ================= GET BY ID =================
    @Override
    public JobPosting getById(Integer jobPostId) {
        String sql = SELECT_JOB_WITH_LOOKUPS + " WHERE j.jobPostId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobPostId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("getById error: " + e.getMessage());
        }
        return null;
    }

    // ================= INSERT =================
    @Override
    public Integer insert(JobPosting job) {

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

            if (ps.executeUpdate() <= 0) {
                return null;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("insert error: " + e.getMessage());
        }

        return null;
    }

    // ================= UPDATE =================
    @Override
    public boolean update(JobPosting job) {
        throw new UnsupportedOperationException("Use updateByHr instead");
    }

    public boolean updateByHR(JobPosting job, int hrId) {

        String sql = """
        UPDATE JobPosting
        SET title=?, `desc`=?, minSalary=?, maxSalary=?,
            workLoc=?, workMode=?, expAt=?, catId=?, levelId=?
        WHERE jobPostId=? AND hrId=?
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
            ps.setInt(11, hrId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer jobPostId) {
        String sql = "DELETE FROM JobPosting WHERE jobPostId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobPostId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("delete error: " + e.getMessage());
        }

        return false;
    }

    // ================= UPDATE STATUS =================
    public boolean updateStatus(int jobPostId, String status, int hrId) {
        String sql = "UPDATE JobPosting SET stat=? WHERE jobPostId=? AND hrId=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, jobPostId);
            ps.setInt(3, hrId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
     // ================= AUTO UPDATE STATUS =================
    public int closeExpiredJobs() {
        String sql = """
        UPDATE JobPosting
        SET stat = 'CLOSED'
        WHERE stat = 'PUBLISHED'
          AND expAt IS NOT NULL
          AND expAt < CURRENT_TIMESTAMP
    """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
