package vn.com.micareer.model;

public class Permission {
    private int permId; // Đổi từ String sang int
    private String permCode;
    private String desc;

    public Permission() {}

    public Permission(int permId, String permCode, String desc) {
        this.permId = permId;
        this.permCode = permCode;
        this.desc = desc;
    }

    public int getPermId() { return permId; }
    public void setPermId(int permId) { this.permId = permId; }

    public String getPermCode() { return permCode; }
    public void setPermCode(String permCode) { this.permCode = permCode; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    @Override
    public String toString() {
        return "Permission{" +
                "permId=" + permId +
                ", permCode='" + permCode + '\'' +
                '}';
    }
}