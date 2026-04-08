/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String pwd;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String stat;
    private String role;
    private String provId;
    private String ward;
    private String street;
    private LocalDateTime createdAt;

    public User() {}
    
    // Getter & Setter
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public String getfName() { return fName; }
    public void setfName(String fName) { this.fName = fName; }

    public String getlName() { return lName; }
    public void setlName(String lName) { this.lName = lName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStat() { return stat; }
    public void setStat(String stat) { this.stat = stat; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getProvId() { return provId; }
    public void setProvId(String provId) { this.provId = provId; }

    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", pwd=" + pwd + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", phone=" + phone + ", stat=" + stat + ", role=" + role + ", provId=" + provId + ", ward=" + ward + ", street=" + street + ", createdAt=" + createdAt + '}';
    }
    
}