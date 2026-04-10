package vn.com.micareer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import vn.com.micareer.util.SalaryFormatUtil;

public class JobDetailView {

    private long jobPostId;
    private long compId;
    private String title;
    private String desc;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String workLoc;
    private String workMode;
    private String stat;
    private LocalDateTime createdAt;
    private LocalDateTime expAt;
    private long catId;
    private String catName;
    private long levelId;
    private String levelName;
    private String compName;
    private String webUrl;
    private String logoUrl;
    private final List<String> skills = new ArrayList<>();

    public long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
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

    public String getSalaryRangeText() {
        return SalaryFormatUtil.formatRange(minSalary, maxSalary);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpAt() {
        return expAt;
    }

    public void setExpAt(LocalDateTime expAt) {
        this.expAt = expAt;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<String> getSkills() {
        return skills;
    }
}