package com.example.hotel.po;


import com.example.hotel.enums.UserType;

public class User {
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private double credit;
    private UserType userType;
    // 会员等级，分三类非会员，普通会员，企业会员
    private String memberType;
    private String birthday; // 生日（普通会员才有）
    private String companyName; // 企业名称（企业会员才有）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return memberType;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
