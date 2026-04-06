/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

/**
 *
 * @author Dang Tuan Minh
 */
public class Level {
    private String levelId;
    private String levelName;

    public Level() {
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Level(String levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }
}
