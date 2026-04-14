package vn.com.micareer.model;

import java.io.Serializable;
import java.util.List;

public class AdminRole implements Serializable{
    private int roleId; // Đổi từ String sang int
    private String roleName;
    private String desc;
    private List<Permission> permissions;

    public AdminRole() {}

    public AdminRole(int roleId, String roleName, String desc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.desc = desc;
    }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public List<Permission> getPermissions() { return permissions; }
    public void setPermissions(List<Permission> permissions) { this.permissions = permissions; }

    @Override
    public String toString() {
        return "AdminRole{" +
                "roleId=" + roleId + // Bổ sung ID vào toString để dễ debug
                ", roleName='" + roleName + '\'' +
                '}';
    }
}