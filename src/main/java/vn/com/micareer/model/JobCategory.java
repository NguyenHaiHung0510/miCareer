package vn.com.micareer.model;

import java.io.Serializable;

public class JobCategory implements Serializable {

    private Integer catId;
    private String catName;
    private String desc;

    public JobCategory() {
    }

    public JobCategory(Integer catId, String catName, String desc) {
        this.catId = catId;
        this.catName = catName;
        this.desc = desc;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
