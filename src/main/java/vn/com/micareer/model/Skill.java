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
public class Skill implements Serializable {

    /**
     * @param args the command line arguments
     */
    private Integer skillId;
    private String skillName;
    private String desc;

    public Skill() {
    }

    public Skill(Integer skillId, String skillName, String desc) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.desc = desc;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getDesc() {
        return desc;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Skill{" + "skillId=" + skillId + ", skillName=" + skillName + ", desc=" + desc + '}';
    }

}
