/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.context.DBContext;
import vn.com.micareer.model.User;

public class UserDAO implements CrudDAO<User, String> {

    @Override
    public String insert(User u) throws SQLException {
        String sql = "INSERT INTO User(userId,userName,pwd,fName,lName,email,phone,stat,role,provId,ward,street) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUserId());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getPwd());
            ps.setString(4, u.getfName());
            ps.setString(5, u.getlName());
            ps.setString(6, u.getEmail());
            ps.setString(7, u.getPhone());
            ps.setString(8, u.getStat());
            ps.setString(9, u.getRole());
            ps.setString(10, u.getProvId());
            ps.setString(11, u.getWard());
            ps.setString(12, u.getStreet());

            return ps.executeUpdate() > 0 ? u.getUserId() : null;
        }
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
            ps.setString(9, u.getUserId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM User WHERE userId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
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
                User u = map(rs);
                list.add(u);
            }
        }
        return list;
    }

    @Override
    public User getById(String id) throws SQLException {
        String sql = "SELECT * FROM User WHERE userId=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
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
                User u = map(rs);
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getString("userId"));
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
}