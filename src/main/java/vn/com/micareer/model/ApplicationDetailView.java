package vn.com.micareer.model;

import java.time.LocalDateTime;

public class ApplicationDetailView {
    private long jobAppId;
    private long candidateId;
    private String candidateName;
    private String email;
    private String phone;
    private String cvSnapUrl;
    private String coverLetter;
    private String stat;
    private LocalDateTime appliedAt;
    private String jobTitle;

    // Getters and Setters
    public long getJobAppId() { return jobAppId; }
    public void setJobAppId(long jobAppId) { this.jobAppId = jobAppId; }
    public long getCandidateId() { return candidateId; }
    public void setCandidateId(long candidateId) { this.candidateId = candidateId; }
    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCvSnapUrl() { return cvSnapUrl; }
    public void setCvSnapUrl(String cvSnapUrl) { this.cvSnapUrl = cvSnapUrl; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public String getStat() { return stat; }
    public void setStat(String stat) { this.stat = stat; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}
