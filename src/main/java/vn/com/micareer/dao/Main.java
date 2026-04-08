/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.com.micareer.model.*;


public class Main {

    public static void main(String[] args) {

        try {
            testUserDAO();
            testPermissionDAO();
            testCandidateDAO();
            testAdminDAO();
            testAdminRoleDAO();
            testProvinceDAO();
            testRegionDAO();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= USER =================
    public static void testUserDAO() throws Exception {
        System.out.println("\n===== TEST USER DAO =====");

        UserDAO dao = new UserDAO();

        // INSERT
        User u = new User();
        u.setUserName("testuser");
        u.setPwd("123");
        u.setfName("Test");
        u.setlName("User");
        u.setEmail("test@gmail.com");
        u.setPhone("0123456789");
        u.setStat("active");
        u.setRole("candidate");

        Integer userId = dao.insert(u);
        System.out.println("Inserted User ID: " + userId);

        // GET BY ID
        User found = dao.getById(userId);
        System.out.println("GetById: " + found);

        // UPDATE
        found.setfName("UpdatedName");
        dao.update(found);
        System.out.println("After Update: " + dao.getById(userId));

        // GET ALL
        List<User> list = dao.getAll();
        System.out.println("All Users:");
        list.forEach(System.out::println);

        // DELETE
        dao.delete(userId);
        System.out.println("Deleted userId=" + userId);
    }

    // ================= PERMISSION =================
    public static void testPermissionDAO() throws Exception {
        System.out.println("\n===== TEST PERMISSION DAO =====");

        PermissionDAO dao = new PermissionDAO();

        // INSERT
        Permission p = new Permission();
        p.setPermCode("TEST_CODE");
        p.setDesc("Test permission");

        Integer id = dao.insert(p);
        System.out.println("Inserted Permission ID: " + id);

        // GET BY ID
        Permission found = dao.getById(id);
        System.out.println("GetById: " + found);

        // UPDATE
        found.setDesc("Updated desc");
        dao.update(found);
        System.out.println("After Update: " + dao.getById(id));

        // GET ALL
        dao.getAll().forEach(System.out::println);

        // DELETE
        dao.delete(id);
        System.out.println("Deleted permissionId=" + id);
    }

    // ================= CANDIDATE =================
    public static void testCandidateDAO() throws Exception {
        System.out.println("\n===== TEST CANDIDATE DAO =====");

        CandidateDAO dao = new CandidateDAO();

        Candidate c = new Candidate();
        c.setUserName("candidate1");
        c.setPwd("123");
        c.setfName("Can");
        c.setlName("Didate");
        c.setEmail("can@gmail.com");
        c.setPhone("099999999");
        c.setRole("candidate");
        c.setStat("active");

        c.setBio("Hello");
        c.setCvUrl("cv.pdf");

        Integer id = dao.insert(c);
        System.out.println("Inserted Candidate ID: " + id);

        Candidate found = dao.getById(id);
        System.out.println("GetById: " + found);

        found.setBio("Updated bio");
        dao.update(found);

        dao.getAll().forEach(System.out::println);

        dao.delete(id);
        System.out.println("Deleted candidateId=" + id);
    }

    // ================= ADMIN =================
    public static void testAdminDAO() throws Exception {
        System.out.println("\n===== TEST ADMIN DAO =====");

        AdminDAO dao = new AdminDAO();

        Admin a = new Admin();
        a.setUserName("admin1");
        a.setPwd("123");
        a.setfName("Ad");
        a.setlName("Min");
        a.setEmail("admin@gmail.com");
        a.setPhone("011111111");
        a.setRole("ADMIN");
        a.setStat("active");


        a.setRoleId("1");

        // INSERT
        Integer id = dao.insert(a);
        System.out.println("Inserted Admin ID: " + id);

        // GET BY ID
        Admin found = dao.getById(id);
        System.out.println("GetById: " + found);

        // UPDATE
        found.setRoleId("2"); // đổi role
        dao.update(found);
        System.out.println("After Update: " + dao.getById(id));

        // GET ALL
        System.out.println("All Admin:");
        dao.getAll().forEach(System.out::println);

        // DELETE
        dao.delete(id);
        System.out.println("Deleted adminId=" + id);
    }
    
    public static void testAdminRoleDAO() throws Exception {
        System.out.println("\n===== TEST ADMIN ROLE DAO =====");

        AdminRoleDAO dao = new AdminRoleDAO();

        // ===== INSERT =====
        AdminRole role = new AdminRole();
        role.setRoleName("TEST_ROLE"); // ⚠️ tránh trùng
        role.setDesc("Test role");

        Integer id = dao.insert(role);
        System.out.println("Inserted roleId: " + id);

        // ===== GET BY ID =====
        AdminRole found = dao.getById(id);
        System.out.println("GetById: " + found);

        // ===== UPDATE =====
        found.setRoleName("UPDATED_TEST_ROLE");
        found.setDesc("Updated description");
        dao.update(found);

        System.out.println("After Update: " + dao.getById(id));

        // ===== GET ALL =====
        System.out.println("\nAll Roles:");
        dao.getAll().forEach(System.out::println);

        // ===== DELETE =====
        dao.delete(id);
        System.out.println("Deleted roleId=" + id);
    }
    
    public static void testRegionDAO() throws Exception {
        System.out.println("\n===== TEST REGION DAO =====");

        RegionDAO dao = new RegionDAO();

        // ===== INSERT =====
        Region r = new Region();
        r.setRegId("TEST");
        r.setRegName("Test Region");

        String id = dao.insert(r);
        System.out.println("Inserted Region ID: " + id);

        // ===== GET BY ID =====
        Region found = dao.getById(id);
        System.out.println("GetById: " + found);

        // ===== UPDATE =====
        found.setRegName("Updated Region");
        dao.update(found);
        System.out.println("After Update: " + dao.getById(id));

        // ===== GET ALL =====
        System.out.println("All Regions:");
        dao.getAll().forEach(System.out::println);

        // ===== DELETE =====
        dao.delete(id);
        System.out.println("Deleted regId=" + id);
    }
    
    public static void testProvinceDAO() throws Exception {
        System.out.println("\n===== TEST PROVINCE DAO =====");

        ProvinceDAO dao = new ProvinceDAO();

        // ===== INSERT =====
        Province p = new Province();
        p.setProvId("TEST"); // ⚠️ không được trùng
        p.setProvName("Test Province");
        p.setRegId("NORTH"); // ⚠️ phải tồn tại trong Region

        String id = dao.insert(p);
        System.out.println("Inserted Province ID: " + id);

        // ===== GET BY ID =====
        Province found = dao.getById(id);
        System.out.println("GetById: " + found);

        // ===== UPDATE =====
        found.setProvName("Updated Province");
        found.setRegId("CENTRAL");
        dao.update(found);
        System.out.println("After Update: " + dao.getById(id));

        // ===== GET ALL =====
        System.out.println("All Provinces:");
        dao.getAll().forEach(System.out::println);

        // ===== GET BY REGION (🔥 useful)
        System.out.println("\nProvinces in NORTH:");
        dao.getByRegion("NORTH").forEach(System.out::println);

        // ===== DELETE =====
        dao.delete(id);
        System.out.println("Deleted provId=" + id);
    }
}