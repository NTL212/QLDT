package com.nhom7.appqldt.Models;

public class Faculty {
    private String falculityCode;
    private String name;

    public Faculty() {
    }

    public Faculty(String falculityCode, String name) {
        this.falculityCode = falculityCode;
        this.name = name;
    }

    public String getFalculityCode() {
        return falculityCode;
    }

    public String getName() {
        return name;
    }

    public void setFalculityCode(String falculityCode) {
        this.falculityCode = falculityCode;
    }

    public void setName(String name) {
        this.name = name;
    }
}
