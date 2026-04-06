package vn.com.micareer.model;

import java.time.LocalDateTime;

public class JobApplication {

    private long jobAppId;
    private long jobPostId;
    private long candidateId;
    private LocalDateTime appliedAt;
    private String stat;
    private String cvSnapUrl;
    private String coverLetter;

    public long getJobAppId() {
        return jobAppId;
    }

    public void setJobAppId(long jobAppId) {
        this.jobAppId = jobAppId;
    }

    public long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getCvSnapUrl() {
        return cvSnapUrl;
    }

    public void setCvSnapUrl(String cvSnapUrl) {
        this.cvSnapUrl = cvSnapUrl;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}