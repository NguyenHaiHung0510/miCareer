package vn.com.micareer.model;

import java.time.LocalDateTime;

public class Interview {

    private long intervId;
    private long jobAppId;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String stat;
    private String mode;
    private String linkMeet;
    private String loc;

    public long getIntervId() {
        return intervId;
    }

    public void setIntervId(long intervId) {
        this.intervId = intervId;
    }

    public long getJobAppId() {
        return jobAppId;
    }

    public void setJobAppId(long jobAppId) {
        this.jobAppId = jobAppId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLinkMeet() {
        return linkMeet;
    }

    public void setLinkMeet(String linkMeet) {
        this.linkMeet = linkMeet;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
