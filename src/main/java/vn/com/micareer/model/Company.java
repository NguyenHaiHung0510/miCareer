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
public class Company implements Serializable {

    /**
     * @param args the command line arguments
     */
    private Integer compId;
    private String compName;
    private String taxCode;
    private String webUrl;
    private String logoUrl;
    private String contactEmail;
    private String provId;
    private String ward;
    private String street;

    public Company() {
    }

    public Company(Integer compId, String compName, String taxCode, String webUrl, String logoUrl, String contactEmail, String provId, String ward, String street) {
        this.compId = compId;
        this.compName = compName;
        this.taxCode = taxCode;
        this.webUrl = webUrl;
        this.logoUrl = logoUrl;
        this.contactEmail = contactEmail;
        this.provId = provId;
        this.ward = ward;
        this.street = street;
    }

    public Integer getCompId() {
        return compId;
    }

    public String getCompName() {
        return compName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getProvId() {
        return provId;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Company{" + "compId=" + compId + ", compName=" + compName + ", taxCode=" + taxCode + ", webUrl=" + webUrl + ", logoUrl=" + logoUrl + ", contactEmail=" + contactEmail + ", provId=" + provId + ", ward=" + ward + ", street=" + street + '}';
    }

}
