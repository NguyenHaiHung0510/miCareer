package vn.com.micareer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vn.com.micareer.context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.model.Candidate;

public class CandidateDAO implements CrudDAO<Candidate, Integer> {

    @Override
    public Integer insert(Candidate c) throws SQLException {
        String sqlUser = "INSERT INTO User(userName, pwd, fName, lName, email, phone, stat, role, provId, ward, street) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String sqlCandidate = "INSERT INTO Candidate(candidateId, bio, cvUrl, dob, expYears) VALUES (?,?,?,?,?)";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);
            Integer userId = null;
            // 1. Insert User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, c.getUserName());
                psUser.setString(2, c.getPwd());
                psUser.setString(3, c.getfName());
                psUser.setString(4, c.getlName());
                psUser.setString(5, c.getEmail());
                psUser.setString(6, c.getPhone());
                psUser.setString(7, c.getStat());
                psUser.setString(8, c.getRole());
                psUser.setString(9, c.getProvId());
                psUser.setString(10, c.getWard());
                psUser.setString(11, c.getStreet());
                psUser.executeUpdate();
                
                ResultSet rs = psUser.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }

            // 2. Insert Candidate
            try (PreparedStatement psCan = con.prepareStatement(sqlCandidate)) {
                psCan.setInt(1, userId); //  sửa
                psCan.setString(2, c.getBio());
                psCan.setString(3, c.getCvUrl());

                if (c.getDob() != null) {
                    psCan.setDate(4, Date.valueOf(c.getDob()));
                } else {
                    psCan.setNull(4, Types.DATE);
                }

                if (c.getExpYears() != null) {
                    psCan.setDouble(5, c.getExpYears());
                } else {
                    psCan.setNull(5, Types.DOUBLE);
                }

                psCan.executeUpdate();
            }

            con.commit();
            return userId; // Integer

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(Candidate c) throws SQLException {
        String sqlUser = "UPDATE User SET userName=?, pwd=?, fName=?, lName=?, email=?, phone=?, stat=?, role=?, provId=?, ward=?, street=? WHERE userId=?";
        String sqlCandidate = "UPDATE Candidate SET bio=?, cvUrl=?, dob=?, expYears=? WHERE candidateId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            // 1. Update User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setString(1, c.getUserName());
                psUser.setString(2, c.getPwd());
                psUser.setString(3, c.getfName());
                psUser.setString(4, c.getlName());
                psUser.setString(5, c.getEmail());
                psUser.setString(6, c.getPhone());
                psUser.setString(7, c.getStat());
                psUser.setString(8, c.getRole());
                psUser.setString(9, c.getProvId());
                psUser.setString(10, c.getWard());
                psUser.setString(11, c.getStreet());
                psUser.setInt(12, c.getUserId()); // ✅ sửa
                psUser.executeUpdate();
            }

            // 2. Update Candidate
            try (PreparedStatement psCan = con.prepareStatement(sqlCandidate)) {
                psCan.setString(1, c.getBio());
                psCan.setString(2, c.getCvUrl());

                if (c.getDob() != null) {
                    psCan.setDate(3, Date.valueOf(c.getDob()));
                } else {
                    psCan.setNull(3, Types.DATE);
                }

                if (c.getExpYears() != null) {
                    psCan.setDouble(4, c.getExpYears());
                } else {
                    psCan.setNull(4, Types.DOUBLE);
                }

                psCan.setInt(5, c.getCandidateId()); // ✅ sửa
                psCan.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sqlCandidate = "DELETE FROM Candidate WHERE candidateId=?";
        String sqlUser = "DELETE FROM User WHERE userId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sqlCandidate)) {
                ps.setInt(1, id); // ✅ sửa
                ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(sqlUser)) {
                ps.setInt(1, id); // ✅ sửa
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    @Override
    public Candidate getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Candidate c JOIN User u ON c.candidateId = u.userId WHERE c.candidateId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // ✅ sửa
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }
        }
        return null;
    }

    private Candidate map(ResultSet rs) throws SQLException {
        Candidate c = new Candidate();

        // Candidate
        c.setCandidateId(rs.getInt("candidateId")); // ✅ sửa
        c.setBio(rs.getString("bio"));
        c.setCvUrl(rs.getString("cvUrl"));

        Date dob = rs.getDate("dob");
        if (dob != null) {
            c.setDob(dob.toLocalDate());
        }

        c.setExpYears(rs.getDouble("expYears"));

        // User
        c.setUserId(rs.getInt("userId")); // ✅ sửa
        c.setUserName(rs.getString("userName"));
        c.setPwd(rs.getString("pwd"));
        c.setfName(rs.getString("fName"));
        c.setlName(rs.getString("lName"));
        c.setEmail(rs.getString("email"));
        c.setPhone(rs.getString("phone"));
        c.setStat(rs.getString("stat"));
        c.setRole(rs.getString("role"));
        c.setProvId(rs.getString("provId"));
        c.setWard(rs.getString("ward"));
        c.setStreet(rs.getString("street"));

        return c;
    }

    @Override
    public List<Candidate> getAll() throws SQLException {
        String sql = "SELECT * FROM Candidate c JOIN User u ON c.candidateId = u.userId";
        List<Candidate> ans = new ArrayList<>();
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ans.add(map(rs));
            }
        }
        return ans;
    }

    public boolean existsById(long candidateId) throws SQLException {
        String sql = "SELECT 1 FROM Candidate WHERE candidateId = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, candidateId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        }
    }
}
