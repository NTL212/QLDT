package com.nhom7.appqldt.Models;

public class Major {
    private String majorCode;
    private String name;
    private Faculty falc;

    public Major(String majorCode, String name, Faculty falc) {
        this.majorCode = majorCode;
        this.name = name;
        this.falc = falc;
    }

    public Major() {
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFalc() {
        return falc;
    }

    public void setFalc(Faculty falc) {
        this.falc = falc;
    }
}
