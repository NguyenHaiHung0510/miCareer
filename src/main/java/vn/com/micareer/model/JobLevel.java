package vn.com.micareer.model;

import java.io.Serializable;

public class JobLevel implements Serializable {

    private Integer levelId;
    private String levelName;
    private String desc;

    public JobLevel() {
    }

    public JobLevel(Integer levelId, String levelName, String desc) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.desc = desc;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
