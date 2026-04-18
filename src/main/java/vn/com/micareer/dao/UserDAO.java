package vn.com.micareer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.User;

public class UserDAO implements CrudDAO<User, Integer> {

   @Override
    public Integer insert(User u) throws SQLException {
        String sql = "INSERT INTO User(userName,pwd,fName,lName,email,phone,stat,role,provId,ward,street) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getfName());
            ps.setString(4, u.getlName());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPhone());
            ps.setString(7, u.getStat());
            ps.setString(8, u.getRole());
            ps.setString(9, u.getProvId());
            ps.setString(10, u.getWard());
            ps.setString(11, u.getStreet());

            int affected = ps.executeUpdate();

            if (affected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // ✅ ID DB sinh ra
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(User u) throws SQLException {
        String sql = "UPDATE User SET userName=?,pwd=?,fName=?,lName=?,email=?,phone=?,stat=?,role=? WHERE userId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getfName());
            ps.setString(4, u.getlName());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPhone());
            ps.setString(7, u.getStat());
            ps.setString(8, u.getRole());
            ps.setInt(9, u.getUserId()); // ✅ sửa

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM User WHERE userId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // ✅ sửa
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM User";

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
    public User getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM User WHERE userId=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // ✅ sửa
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }
        return null;
    }


    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username=?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }
        return null;
    }
    public User login(String userName, String pwd) {
        String sql = "SELECT * FROM User WHERE userName = ? AND pwd = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userName);
            ps.setString(2, pwd);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return map(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private User map(ResultSet rs) throws SQLException {
        User u = new User();

        u.setUserId(rs.getInt("userId")); // ✅ sửa
        u.setUserName(rs.getString("userName"));
        u.setPwd(rs.getString("pwd"));
        u.setfName(rs.getString("fName"));
        u.setlName(rs.getString("lName"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));
        u.setStat(rs.getString("stat"));
        u.setRole(rs.getString("role"));

        return u;
    }
    
    public boolean isUsernameExist(String userName) {
        String sql = "SELECT 1 FROM User WHERE userName = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // có record → username đã tồn tại

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean isEmailExist(String email) {
        String sql = "SELECT 1 FROM User WHERE email = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // có record → email đã tồn tại

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}