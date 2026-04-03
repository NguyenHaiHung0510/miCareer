package vn.com.micareer.dao;

import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO implements CrudDAO<Candidate, String> {

    @Override
    public String insert(Candidate c) throws SQLException {
        String sqlUser = "INSERT INTO User(userId, userName, pwd, fName, lName, email, phone, stat, role, provId, ward, street) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String sqlCandidate = "INSERT INTO Candidate(candidateId, bio, cvUrl, dob, expYears) VALUES (?,?,?,?,?)";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            // 1. Insert User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setString(1, c.getUserId());
                psUser.setString(2, c.getUserName());
                psUser.setString(3, c.getPwd());
                psUser.setString(4, c.getfName());
                psUser.setString(5, c.getlName());
                psUser.setString(6, c.getEmail());
                psUser.setString(7, c.getPhone());
                psUser.setString(8, c.getStat());
                psUser.setString(9, c.getRole());
                psUser.setString(10, c.getProvId());
                psUser.setString(11, c.getWard());
                psUser.setString(12, c.getStreet());
                psUser.executeUpdate();
            }

            // 2. Insert Candidate
            try (PreparedStatement psCan = con.prepareStatement(sqlCandidate)) {
                psCan.setString(1, c.getCandidateId()); // = userId
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

                System.out.println(psCan.executeUpdate());
            }

            con.commit();
            return c.getCandidateId();

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
                psUser.setString(12, c.getUserId());
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

                psCan.setString(5, c.getCandidateId());

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
    public boolean delete(String id) throws SQLException {
        String sqlCandidate = "DELETE FROM Candidate WHERE candidateId=?";
        String sqlUser = "DELETE FROM User WHERE userId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            // 1. Xóa Candidate trước
            try (PreparedStatement ps = con.prepareStatement(sqlCandidate)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }

            // 2. Xóa User
            try (PreparedStatement ps = con.prepareStatement(sqlUser)) {
                ps.setString(1, id);
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
    public List<Candidate> getAll() throws SQLException {
        List<Candidate> list = new ArrayList<>();

        String sql = "SELECT * FROM Candidate c JOIN User u ON c.candidateId = u.userId";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public Candidate getById(String id) throws SQLException {
        String sql = "SELECT * FROM Candidate c JOIN User u ON c.candidateId = u.userId WHERE c.candidateId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }
        }
        return null;
    }
    public boolean isUsernameExist(String userName) {
        String sql = "SELECT 1 FROM User WHERE userName = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // có dữ liệu → tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    private Candidate map(ResultSet rs) throws SQLException{
        Candidate c = new Candidate();

        // ?Candidate fields
        c.setCandidateId(rs.getString("candidateId"));
        c.setBio(rs.getString("bio"));
        c.setCvUrl(rs.getString("cvUrl"));

        Date dob = rs.getDate("dob");
        if (dob != null) {
            c.setDob(dob.toLocalDate());
        }

        c.setExpYears(rs.getDouble("expYears"));

        // User fields
        c.setUserId(rs.getString("userId"));
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
}