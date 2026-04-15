package vn.com.micareer.model;

import java.time.LocalDateTime;

public class AppStatusHistory {

    private long histId;
    private long jobAppId;
    private long hrId;
    private String oldStat;
    private String newStat;
    private LocalDateTime changeAt;

    public long getHistId() {
        return histId;
    }

    public void setHistId(long histId) {
        this.histId = histId;
    }

    public long getJobAppId() {
        return jobAppId;
    }

    public void setJobAppId(long jobAppId) {
        this.jobAppId = jobAppId;
    }

    public long getHrId() {
        return hrId;
    }

    public void setHrId(long hrId) {
        this.hrId = hrId;
    }

    public String getOldStat() {
        return oldStat;
    }

    public void setOldStat(String oldStat) {
        this.oldStat = oldStat;
    }

    public String getNewStat() {
        return newStat;
    }

    public void setNewStat(String newStat) {
        this.newStat = newStat;
    }

    public LocalDateTime getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(LocalDateTime changeAt) {
        this.changeAt = changeAt;
    }
}
