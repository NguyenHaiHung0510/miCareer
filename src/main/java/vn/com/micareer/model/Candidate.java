/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;


import java.time.LocalDate;

public class Candidate {

    private Long userId;     // PK + FK -> User
    private String bio;      // Mô tả ngắn
    private String cvUrl;    // Đường dẫn CV
    private LocalDate dob;   // Date of Birth
    private Integer expYears; // Số năm kinh nghiệm (nullable)

    // Constructor không tham số
    public Candidate() {
    }

    // Constructor đầy đủ tham số
    public Candidate(Long userId,
                     String bio,
                     String cvUrl,
                     LocalDate dob,
                     Integer expYears) {

        this.userId = userId;
        this.bio = bio;
        this.cvUrl = cvUrl;
        this.dob = dob;
        this.expYears = expYears;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getExpYears() {
        return expYears;
    }

    public void setExpYears(Integer expYears) {
        this.expYears = expYears;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "userId=" + userId +
                ", bio='" + bio + '\'' +
                ", cvUrl='" + cvUrl + '\'' +
                ", dob=" + dob +
                ", expYears=" + expYears +
                '}';
    }
}