package com.nhom7.appqldt.Activitys.DTO;

import com.nhom7.appqldt.Models.Major;

public class ClassDTO {
    private String classCode;
    private String name;
    private Major major;

    public ClassDTO() {
    }

    public ClassDTO(String classCode, String name, Major major) {
        this.classCode = classCode;
        this.name = name;
        this.major = major;
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
