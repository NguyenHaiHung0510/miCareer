package vn.com.micareer.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Candidate {

    private long candidateId;
    private String bio;
    private String cvUrl;
    private LocalDate dob;
    private BigDecimal expYears;

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
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

    public BigDecimal getExpYears() {
        return expYears;
    }

    public void setExpYears(BigDecimal expYears) {
        this.expYears = expYears;
    }
}