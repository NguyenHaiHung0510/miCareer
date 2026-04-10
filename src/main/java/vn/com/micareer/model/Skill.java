package vn.com.micareer.model;

import java.io.Serializable;

public class Skill implements Serializable {

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

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
