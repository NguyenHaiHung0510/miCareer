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
public class JobCategory implements Serializable {

    /**
     * @param args the command line arguments
     */
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

    public String getCatName() {
        return catName;
    }

    public String getDesc() {
        return desc;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "JobCategory{" + "catId=" + catId + ", catName=" + catName + ", desc=" + desc + '}';
    }
    
    
}
