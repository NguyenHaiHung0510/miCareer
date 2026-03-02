
package com.ats.model;

import java.time.LocalDateTime;

public class CandidateProfile {

    private Long id;                    // PK
    private Long userId;                // FK -> User

    private String fullName;
    private String email;
    private String phone;
    private String summary;

    private Integer totalExperienceYears;   // Nullable
    private String currentPosition;         // Nullable
    private String currentCompany;          // Nullable
    private String location;                // Nullable

    private String cvFileUrl;               // Link file CV nếu có upload

    private LocalDateTime createdAt;        // NOT NULL
    private LocalDateTime updatedAt;        // Nullable
    private Boolean active;                 // Soft delete


    public CandidateProfile() {
    }

    public CandidateProfile(Long id,
                            Long userId,
                            String fullName,
                            String email,
                            String phone,
                            String summary,
                            Integer totalExperienceYears,
                            String currentPosition,
                            String currentCompany,
                            String location,
                            String cvFileUrl,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt,
                            Boolean active) {

        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
        this.totalExperienceYears = totalExperienceYears;
        this.currentPosition = currentPosition;
        this.currentCompany = currentCompany;
        this.location = location;
        this.cvFileUrl = cvFileUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTotalExperienceYears() {
        return totalExperienceYears;
    }

    public void setTotalExperienceYears(Integer totalExperienceYears) {
        this.totalExperienceYears = totalExperienceYears;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCvFileUrl() {
        return cvFileUrl;
    }

    public void setCvFileUrl(String cvFileUrl) {
        this.cvFileUrl = cvFileUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return "CandidateProfile{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", totalExperienceYears=" + totalExperienceYears +
                ", currentPosition='" + currentPosition + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}