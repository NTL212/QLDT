package com.nhom7.appqldt.Activitys.DTO;

import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.Student;

import java.time.LocalDate;

public class StudentDTO {
    private String id;
    private String name;
    private String birthday;
    private Faculty falculity;
    private ClassDTO classObj;

    public StudentDTO(String id, String name, String birthday, Faculty falculity, ClassDTO classObj) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.falculity = falculity;
        this.classObj = classObj;
    }

    public StudentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Faculty getFalculity() {
        return falculity;
    }

    public void setFalculity(Faculty falculity) {
        this.falculity = falculity;
    }

    public ClassDTO getClassObj() {
        return classObj;
    }

    public void setClassObj(ClassDTO classObj) {
        this.classObj = classObj;
    }
}