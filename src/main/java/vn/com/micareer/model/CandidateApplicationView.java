package vn.com.micareer.model;

import java.time.LocalDateTime;

/**
 * View model cho ứng viên xem danh sách đơn ứng tuyển của mình.
 */
public class CandidateApplicationView {

    private long jobAppId;
    private long jobPostId;
    private String jobTitle;
    private String compName;
    private LocalDateTime appliedAt;
    private String stat;
    private String cvSnapUrl;

    public long getJobAppId() { return jobAppId; }
    public void setJobAppId(long jobAppId) { this.jobAppId = jobAppId; }

    public long getJobPostId() { return jobPostId; }
    public void setJobPostId(long jobPostId) { this.jobPostId = jobPostId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompName() { return compName; }
    public void setCompName(String compName) { this.compName = compName; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public String getStat() { return stat; }
    public void setStat(String stat) { this.stat = stat; }

    public String getCvSnapUrl() { return cvSnapUrl; }
    public void setCvSnapUrl(String cvSnapUrl) { this.cvSnapUrl = cvSnapUrl; }
}
