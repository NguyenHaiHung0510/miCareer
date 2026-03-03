/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

import java.time.LocalDateTime;

public class JobApplication {

    private Long jobAppId;          // PK
    private Long jobPostId;         // FK -> JobPosting
    private Long candidateId;       // FK -> Candidate

    private LocalDateTime appliedAt;   // NOT NULL
    private String stat;               // APPLIED, INTERVIEW, OFFER, HIRED, REJECTED

    private String cvSnapUrl;          // Snapshot file URL
    private String coverLetter;        // Có thể NULL

    // Constructor không tham số
    public JobApplication() {
    }

    // Constructor đầy đủ tham số
    public JobApplication(Long jobAppId,
                          Long jobPostId,
                          Long candidateId,
                          LocalDateTime appliedAt,
                          String stat,
                          String cvSnapUrl,
                          String coverLetter) {
        this.jobAppId = jobAppId;
        this.jobPostId = jobPostId;
        this.candidateId = candidateId;
        this.appliedAt = appliedAt;
        this.stat = stat;
        this.cvSnapUrl = cvSnapUrl;
        this.coverLetter = coverLetter;
    }

    public Long getJobAppId() {
        return jobAppId;
    }

    public void setJobAppId(Long jobAppId) {
        this.jobAppId = jobAppId;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
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

    @Override
    public String toString() {
        return "JobApplication{" +
                "jobAppId=" + jobAppId +
                ", jobPostId=" + jobPostId +
                ", candidateId=" + candidateId +
                ", appliedAt=" + appliedAt +
                ", stat='" + stat + '\'' +
                ", cvSnapUrl='" + cvSnapUrl + '\'' +
                '}';
    }
}