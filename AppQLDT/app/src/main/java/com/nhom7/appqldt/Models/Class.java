package com.nhom7.appqldt.Models;

public class Class {
    private String classCode;
    private String name;
    private Major major;

    public Class(String classCode, String name, Major major) {
        this.classCode = classCode;
        this.name = name;
        this.major = major;
    }

    public Class() {
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
