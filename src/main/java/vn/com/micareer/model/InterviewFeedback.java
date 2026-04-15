package vn.com.micareer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InterviewFeedback {
    private long feedbackId;
    private long intervId;
    private long hrId;
    private BigDecimal score;
    private String cmt;
    private LocalDateTime subAt;

    // Getters and Setters
    public long getFeedbackId() { return feedbackId; }
    public void setFeedbackId(long feedbackId) { this.feedbackId = feedbackId; }
    public long getIntervId() { return intervId; }
    public void setIntervId(long intervId) { this.intervId = intervId; }
    public long getHrId() { return hrId; }
    public void setHrId(long hrId) { this.hrId = hrId; }
    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }
    public String getCmt() { return cmt; }
    public void setCmt(String cmt) { this.cmt = cmt; }
    public LocalDateTime getSubAt() { return subAt; }
    public void setSubAt(LocalDateTime subAt) { this.subAt = subAt; }
}
