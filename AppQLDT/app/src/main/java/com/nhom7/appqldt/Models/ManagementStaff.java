package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class ManagementStaff implements Serializable {
    private String empCode;
    private String name;
    private String birthday;
    private String address;
    private String idNum;
    private String phoneNum;
    private String email;
    private String sex;

    public ManagementStaff(String empCode, String name, String birthday, String address, String idNum, String phoneNum, String email, String sex) {
        this.empCode = empCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
    }

    public ManagementStaff() {
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
