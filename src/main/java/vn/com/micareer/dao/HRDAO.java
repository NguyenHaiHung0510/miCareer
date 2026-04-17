package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.HR;

public class HRDAO implements CrudDAO<HR, Integer>{

    // ================= INSERT =================
    @Override
    public Integer insert(HR hr) throws SQLException {

        String sqlUser = "INSERT INTO User(userName, pwd, fName, lName, email, phone, stat, role, provId, ward, street) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String sqlHR = "INSERT INTO HR(hrId, posId, compId, emailSign) VALUES (?,?,?,?)";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            Integer userId = null;

            // 1. Insert User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {

                psUser.setString(1, hr.getUserName());
                psUser.setString(2, hr.getPwd());
                psUser.setString(3, hr.getfName());
                psUser.setString(4, hr.getlName());
                psUser.setString(5, hr.getEmail());
                psUser.setString(6, hr.getPhone());
                psUser.setString(7, hr.getStat());
                psUser.setString(8, hr.getRole());
                psUser.setString(9, hr.getProvId());
                psUser.setString(10, hr.getWard());
                psUser.setString(11, hr.getStreet());

                psUser.executeUpdate();

                ResultSet rs = psUser.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }

            // 2. Insert HR
            try (PreparedStatement psHR = con.prepareStatement(sqlHR)) {
                psHR.setInt(1, userId);
                psHR.setInt(2, hr.getPosId());
                psHR.setInt(3, hr.getCompId());
                psHR.setString(4, hr.getEmailSign());
                psHR.executeUpdate();
            }

            con.commit();
            return userId;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    // ================= UPDATE =================
    @Override
    public boolean update(HR hr) throws SQLException {

        String sqlUser = "UPDATE User SET userName=?, pwd=?, fName=?, lName=?, email=?, phone=?, stat=?, role=?, provId=?, ward=?, street=? WHERE userId=?";
        String sqlHR = "UPDATE HR SET posId=?, compId=?, emailSign=? WHERE hrId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            // 1. Update User
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {

                psUser.setString(1, hr.getUserName());
                psUser.setString(2, hr.getPwd());
                psUser.setString(3, hr.getfName());
                psUser.setString(4, hr.getlName());
                psUser.setString(5, hr.getEmail());
                psUser.setString(6, hr.getPhone());
                psUser.setString(7, hr.getStat());
                psUser.setString(8, hr.getRole());
                psUser.setString(9, hr.getProvId());
                psUser.setString(10, hr.getWard());
                psUser.setString(11, hr.getStreet());
                psUser.setInt(12, hr.getHrId()); // 🔥 userId = hrId

                psUser.executeUpdate();
            }

            // 2. Update HR
            try (PreparedStatement psHR = con.prepareStatement(sqlHR)) {

                psHR.setInt(1, hr.getPosId());
                psHR.setInt(2, hr.getCompId());
                psHR.setString(3, hr.getEmailSign());
                psHR.setInt(4, hr.getHrId());

                psHR.executeUpdate();
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

    // ================= DELETE =================
    @Override
    public boolean delete(Integer id) throws SQLException {

        String sqlHR = "DELETE FROM HR WHERE hrId=?";
        String sqlUser = "DELETE FROM User WHERE userId=?";

        Connection con = null;

        try {
            con = DBContext.getConnection();
            con.setAutoCommit(false);

            // 1. Delete HR trước
            try (PreparedStatement psHR = con.prepareStatement(sqlHR)) {
                psHR.setInt(1, id);
                psHR.executeUpdate();
            }

            // 2. Delete User sau
            try (PreparedStatement psUser = con.prepareStatement(sqlUser)) {
                psUser.setInt(1, id);
                psUser.executeUpdate();
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

    // ================= GET ALL =================
    @Override
    public List<HR> getAll() throws SQLException {

        List<HR> list = new ArrayList<>();

        String sql = "SELECT * FROM HR h JOIN User u ON h.hrId = u.userId";

        try (Connection con = DBContext.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    // ================= GET BY ID =================
    @Override
    public HR getById(Integer id) throws SQLException {

        String sql = "SELECT * FROM HR h JOIN User u ON h.hrId = u.userId WHERE h.hrId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    // ================= MAP =================
    private HR map(ResultSet rs) throws SQLException {

        HR hr = new HR();

        // HR
        hr.setHrId(rs.getInt("hrId"));
        hr.setPosId(rs.getInt("posId"));
        hr.setCompId(rs.getInt("compId"));
        hr.setEmailSign(rs.getString("emailSign"));

        // User
        hr.setUserId(rs.getInt("userId"));
        hr.setUserName(rs.getString("userName"));
        hr.setPwd(rs.getString("pwd"));
        hr.setfName(rs.getString("fName"));
        hr.setlName(rs.getString("lName"));
        hr.setEmail(rs.getString("email"));
        hr.setPhone(rs.getString("phone"));
        hr.setStat(rs.getString("stat"));
        hr.setRole(rs.getString("role"));
        hr.setProvId(rs.getString("provId"));
        hr.setWard(rs.getString("ward"));
        hr.setStreet(rs.getString("street"));

        return hr;
    }

    // 🔥 Optional: lấy HR theo company
    public List<HR> getByCompany(int compId) throws SQLException {
        List<HR> list = new ArrayList<>();
        String sql = "SELECT * FROM HR WHERE compId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, compId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }
    public Integer getCompIdByHr(int hrId) {
        String sql = "SELECT compId FROM HR WHERE hrId = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hrId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("compId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
