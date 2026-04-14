package vn.com.micareer.model;

import java.io.Serializable;

public class Company implements Serializable {

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

    public Company(Integer compId, String compName, String taxCode, String webUrl, String logoUrl,
                   String contactEmail, String provId, String ward, String street) {
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

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}