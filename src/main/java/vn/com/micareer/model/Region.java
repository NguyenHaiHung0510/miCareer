package vn.com.micareer.model;

public class Region {
    private String regId;
    private String regName;

    public Region() {}

    public Region(String regId, String regName) {
        this.regId = regId;
        this.regName = regName;
    }

    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }

    public String getRegName() { return regName; }
    public void setRegName(String regName) { this.regName = regName; }

    @Override
    public String toString() {
        return "Region{" +
                "regId='" + regId + '\'' +
                ", regName='" + regName + '\'' +
                '}';
    }
}