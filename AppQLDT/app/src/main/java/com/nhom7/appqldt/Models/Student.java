package com.nhom7.appqldt.Models;

import com.nhom7.appqldt.Activitys.DTO.ClassDTO;

public class Student {
    private String studentCode;
    private ClassDTO classDTO;
    private String name;
    private String birthday;
    private String address;
    private String idNum;
    private String phoneNum;
    private String email;
    private String sex;

    public Student() {
    }

    public Student(String studentCode, ClassDTO classDTO, String name, String birthday, String address, String idNum, String phoneNum, String email, String sex) {
        this.studentCode = studentCode;
        this.classDTO = classDTO;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public ClassDTO getClassDTO() {
        return classDTO;
    }

    public void setClassDTO(ClassDTO classDTO) {
        this.classDTO = classDTO;
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
