/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

public class Permission {
    private String permId;
    private String permCode;
    private String desc;

    public Permission() {}

    public Permission(String permId, String permCode, String desc) {
        this.permId = permId;
        this.permCode = permCode;
        this.desc = desc;
    }

    public String getPermId() { return permId; }
    public void setPermId(String permId) { this.permId = permId; }

    public String getPermCode() { return permCode; }
    public void setPermCode(String permCode) { this.permCode = permCode; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    @Override
    public String toString() {
        return "Permission{" +
                "permCode='" + permCode + '\'' +
                '}';
    }
}