package vn.com.micareer.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class JobPosting {

    private String jobPostId;
    private String compId;
    private String title;
    private String desc;
    private String hrId;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String workLoc;
    private String workMode;
    private String stat;
    private Timestamp createdAt;
    private Timestamp expAt;
    private String catId;
    private String levelId;
    private String compName;
    private String catName;
    private String levelName;

    public JobPosting() {
    }

    public JobPosting(String jobPostId, String compId, String title, String desc,
            BigDecimal minSalary, BigDecimal maxSalary,
            String workLoc, String workMode, String stat,
            Timestamp createdAt, Timestamp expAt,
            String catId, String levelId) {
        this.jobPostId = jobPostId;
        this.compId = compId;
        this.title = title;
        this.desc = desc;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.workLoc = workLoc;
        this.workMode = workMode;
        this.stat = stat;
        this.createdAt = createdAt;
        this.expAt = expAt;
        this.catId = catId;
        this.levelId = levelId;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpAt() {
        return expAt;
    }

    public void setExpAt(Timestamp expAt) {
        this.expAt = expAt;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
