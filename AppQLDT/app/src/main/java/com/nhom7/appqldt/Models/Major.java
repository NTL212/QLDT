package com.nhom7.appqldt.Models;

public class Major {
    private String majorCode;
    private String name;
    private Falculity falc;

    public Major() {
    }

    public Major(String majorCode, String name, Falculity falculity) {
        this.majorCode = majorCode;
        this.name = name;
        this.falc = falculity;
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

    public Falculity getFalculity() {
        return falc;
    }

    public void setFalculity(Falculity falculity) {
        this.falc = falculity;
    }
}
