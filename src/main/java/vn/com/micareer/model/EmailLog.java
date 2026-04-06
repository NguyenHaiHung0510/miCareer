package vn.com.micareer.model;

import java.time.LocalDateTime;

public class EmailLog {
    private long logId;
    private long tmplId;
    private long jobAppId;
    private long userId;
    private String content;
    private LocalDateTime sentAt;
    private String rcvEmail;

    // Getters and Setters
    public long getLogId() { return logId; }
    public void setLogId(long logId) { this.logId = logId; }
    public long getTmplId() { return tmplId; }
    public void setTmplId(long tmplId) { this.tmplId = tmplId; }
    public long getJobAppId() { return jobAppId; }
    public void setJobAppId(long jobAppId) { this.jobAppId = jobAppId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public String getRcvEmail() { return rcvEmail; }
    public void setRcvEmail(String rcvEmail) { this.rcvEmail = rcvEmail; }
}
