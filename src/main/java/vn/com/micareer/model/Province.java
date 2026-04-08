package vn.com.micareer.model;

public class Province {
    private String provId;
    private String provName;
    private String regId;

    public Province() {}

    public Province(String provId, String provName, String regId) {
        this.provId = provId;
        this.provName = provName;
        this.regId = regId;
    }

    public String getProvId() { return provId; }
    public void setProvId(String provId) { this.provId = provId; }

    public String getProvName() { return provName; }
    public void setProvName(String provName) { this.provName = provName; }

    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }

    @Override
    public String toString() {
        return "Province{" +
                "provId='" + provId + '\'' +
                ", provName='" + provName + '\'' +
                ", regId='" + regId + '\'' +
                '}';
    }
}