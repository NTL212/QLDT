package com.nhom7.appqldt.Models;

public class Lecturer {
    private String lecturerCode;
    private Faculty faculty;
    private String name;
    private String birthday;
    private String address;
    private String idNum;
    private String phoneNum;
    private String email;
    private String sex;

    public Lecturer(String lecturerCode, Faculty faculty, String name, String birthday, String address, String idNum, String phoneNum, String email, String sex) {
        this.lecturerCode = lecturerCode;
        this.faculty = faculty;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
    }

    public Lecturer() {
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
