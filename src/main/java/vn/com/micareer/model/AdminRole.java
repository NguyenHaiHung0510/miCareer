/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

import java.util.List;

public class AdminRole {
    private String roleId;
    private String roleName;
    private String desc;
    private List<Permission> permissions;

    public AdminRole() {}

    public AdminRole(String roleId, String roleName, String desc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.desc = desc;
    }

    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public List<Permission> getPermissions() { return permissions; }
    public void setPermissions(List<Permission> permissions) { this.permissions = permissions; }

    @Override
    public String toString() {
        return "AdminRole{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}