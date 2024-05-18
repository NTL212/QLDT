package com.nhom7.appqldt.Models;

import java.time.LocalDate;

public class Student {
    private String studentCode;

    protected String name;
    protected String birthday;
    protected String address;
    protected String idNum;
    protected String phoneNum;

    protected String email;
    protected String sex;
    private Class classDTO;

    public Student(String studentCode, String name, String birthday, String address, String idNum, String phoneNum, String email, String sex, Class classDTO) {
        this.studentCode = studentCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
        this.classDTO = classDTO;
    }

    public Student() {
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    public Class getClassDTO() {
        return classDTO;
    }

    public void setClassDTO(Class classDTO) {
        this.classDTO = classDTO;
    }
}
