package vn.com.micareer.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class JobPosting {

    private int jobPostId;
    private int compId;
    private int hrId;
    private int catId;
    private int levelId;

    private String title;
    private String desc;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private String workLoc;
    private String workMode;
    private String stat;

    private Timestamp createdAt;
    private Timestamp expAt;

    // JOIN fields
    private String compName;
    private String catName;
    private String levelName;

    public JobPosting() {
    }

    public JobPosting(int jobPostId, int compId, int hrId, int catId, int levelId,
            String title, String desc,
            BigDecimal minSalary, BigDecimal maxSalary,
            String workLoc, String workMode, String stat,
            Timestamp createdAt, Timestamp expAt) {
        this.jobPostId = jobPostId;
        this.compId = compId;
        this.hrId = hrId;
        this.catId = catId;
        this.levelId = levelId;
        this.title = title;
        this.desc = desc;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.workLoc = workLoc;
        this.workMode = workMode;
        this.stat = stat;
        this.createdAt = createdAt;
        this.expAt = expAt;
    }

    // Getter & Setter
    public int getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(int jobPostId) {
        this.jobPostId = jobPostId;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
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