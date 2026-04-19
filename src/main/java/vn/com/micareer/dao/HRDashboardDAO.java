package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HRDashboardDAO {

    // 1. Tổng số job (có filter status)
    public int countJobs(int compId, String status) {
        String sql = """
            SELECT COUNT(*)
            FROM JobPosting
            WHERE compId = ?
              AND (? IS NULL OR stat = ?)
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, compId);
            ps.setString(2, status);
            ps.setString(3, status);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 2. Tổng application (có filter thời gian)
    public int countApplications(int compId, String fromDate, String toDate) {
        String sql = """
            SELECT COUNT(*)
            FROM JobApplication ja
            JOIN JobPosting jp ON ja.jobPostId = jp.jobPostId
            WHERE jp.compId = ?
              AND (? IS NULL OR ja.appliedAt >= ?)
              AND (? IS NULL OR ja.appliedAt < DATE_ADD(?, INTERVAL 1 DAY))
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, compId);
            ps.setString(2, fromDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, toDate);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 3. Application hôm nay
    public int countApplicationsToday(int compId) {
        String sql = """
            SELECT COUNT(*)
            FROM JobApplication ja
            JOIN JobPosting jp ON ja.jobPostId = jp.jobPostId
            WHERE jp.compId = ?
              AND DATE(ja.appliedAt) = CURRENT_DATE
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, compId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 4. Top job (có filter thời gian)
    public List<Object[]> getTopJobs(int compId, int limit, String fromDate, String toDate) {
        List<Object[]> list = new ArrayList<>();

        String sql = """
            SELECT jp.title, COUNT(ja.jobAppId) AS total
            FROM JobPosting jp
            LEFT JOIN JobApplication ja ON jp.jobPostId = ja.jobPostId
            WHERE jp.compId = ?
              AND (? IS NULL OR ja.appliedAt >= ?)
              AND (? IS NULL OR ja.appliedAt < DATE_ADD(?, INTERVAL 1 DAY))
            GROUP BY jp.jobPostId, jp.title
            ORDER BY total DESC
            LIMIT ?
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, compId);
            ps.setString(2, fromDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, toDate);
            ps.setInt(6, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("title"),
                    rs.getInt("total")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 5. Stats theo trạng thái (có filter thời gian)
    public List<Object[]> getApplicationStats(int compId, String fromDate, String toDate) {
        List<Object[]> list = new ArrayList<>();

        String sql = """
            SELECT ja.stat, COUNT(*) AS total
            FROM JobApplication ja
            JOIN JobPosting jp ON ja.jobPostId = jp.jobPostId
            WHERE jp.compId = ?
              AND (? IS NULL OR ja.appliedAt >= ?)
              AND (? IS NULL OR ja.appliedAt < DATE_ADD(?, INTERVAL 1 DAY))
            GROUP BY ja.stat
        """;

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, compId);
            ps.setString(2, fromDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, toDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("stat"),
                    rs.getInt("total")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
