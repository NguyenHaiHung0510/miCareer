/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

public class Admin extends User {
    private Integer adminId;
    private String roleId;

    public Admin() {}

    public Admin(Integer adminId, String roleId) {
        this.adminId = adminId;
        this.roleId = roleId;
    }

    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }

    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}