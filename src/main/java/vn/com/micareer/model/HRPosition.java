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
public class HRPosition implements Serializable {

    /**
     * @param args the command line arguments
     */
    private Integer posId;
    private String posName;
    private String desc;

    public HRPosition() {
    }

    public HRPosition(Integer posId, String posName, String desc) {
        this.posId = posId;
        this.posName = posName;
        this.desc = desc;
    }

    public Integer getPosId() {
        return posId;
    }

    public String getPosName() {
        return posName;
    }

    public String getDesc() {
        return desc;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
