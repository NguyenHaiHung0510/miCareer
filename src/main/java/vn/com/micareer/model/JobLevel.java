/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package vn.com.micareer.model;

import java.io.Serializable;

/**
 *
 * @author os
 */
public class JobLevel implements Serializable {

    /**
     * @param args the command line arguments
     */
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

    public String getLevelName() {
        return levelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "JobLevel{" + "levelId=" + levelId + ", levelName=" + levelName + ", desc=" + desc + '}';
    }

}
