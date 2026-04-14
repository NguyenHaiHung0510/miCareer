package vn.com.micareer.model;

import java.math.BigDecimal;

public class Offer {
    private long offerId;
    private long jobAppId;
    private BigDecimal salary;
    private String desc;
    private String stat;
    private Integer ver;
    private long hrId;

    // Getters and Setters
    public long getOfferId() { return offerId; }
    public void setOfferId(long offerId) { this.offerId = offerId; }
    public long getJobAppId() { return jobAppId; }
    public void setJobAppId(long jobAppId) { this.jobAppId = jobAppId; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getStat() { return stat; }
    public void setStat(String stat) { this.stat = stat; }
    public Integer getVer() { return ver; }
    public void setVer(Integer ver) { this.ver = ver; }
    public long getHrId() { return hrId; }
    public void setHrId(long hrId) { this.hrId = hrId; }
}
