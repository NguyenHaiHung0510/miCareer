package vn.com.micareer.model;

import java.time.LocalDate;

public class Candidate extends User{
    private Integer candidateId;
    private String bio;
    private String cvUrl;
    private LocalDate dob;
    private Double expYears;

    public Candidate() {}

    public Integer getCandidateId() { return candidateId; }
    public void setCandidateId(Integer candidateId) { this.candidateId = candidateId; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public Double getExpYears() { return expYears; }
    public void setExpYears(Double expYears) { this.expYears = expYears; }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId='" + candidateId + '\'' +
                ", bio='" + bio + '\'' +
                ", cvUrl='" + cvUrl + '\'' +
                ", dob=" + dob +
                ", expYears=" + expYears +
                '}';
    }
}