/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ats.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JobApplication {

    private Long id;                         // PK
    private Long candidateProfileId;         // FK
    private Long jobPostingId;               // FK

    private String status;                   // APPLIED, SCREENING, INTERVIEW, OFFER, HIRED, REJECTED
    private String cvSnapshotJson;           // Snapshot tại thời điểm apply

    private BigDecimal expectedSalary;       // Có thể NULL
    private String source;                   // LinkedIn, Website, Referral,...

    private LocalDateTime appliedAt;         // NOT NULL
    private LocalDateTime updatedAt;         // NULLABLE
    private Boolean active;                  // Soft delete / trạng thái logic

    public JobApplication() {
    }

    public JobApplication(Long id,
                          Long candidateProfileId,
                          Long jobPostingId,
                          String status,
                          String cvSnapshotJson,
                          BigDecimal expectedSalary,
                          String source,
                          LocalDateTime appliedAt,
                          LocalDateTime updatedAt,
                          Boolean active) {

        this.id = id;
        this.candidateProfileId = candidateProfileId;
        this.jobPostingId = jobPostingId;
        this.status = status;
        this.cvSnapshotJson = cvSnapshotJson;
        this.expectedSalary = expectedSalary;
        this.source = source;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateProfileId() {
        return candidateProfileId;
    }

    public void setCandidateProfileId(Long candidateProfileId) {
        this.candidateProfileId = candidateProfileId;
    }

    public Long getJobPostingId() {
        return jobPostingId;
    }

    public void setJobPostingId(Long jobPostingId) {
        this.jobPostingId = jobPostingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCvSnapshotJson() {
        return cvSnapshotJson;
    }

    public void setCvSnapshotJson(String cvSnapshotJson) {
        this.cvSnapshotJson = cvSnapshotJson;
    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", candidateProfileId=" + candidateProfileId +
                ", jobPostingId=" + jobPostingId +
                ", status='" + status + '\'' +
                ", expectedSalary=" + expectedSalary +
                ", appliedAt=" + appliedAt +
                ", active=" + active +
                '}';
    }
}