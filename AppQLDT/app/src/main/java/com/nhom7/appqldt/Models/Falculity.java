package com.nhom7.appqldt.Models;

public class Falculity {
    private String falculityCode;
    private String name;

    public Falculity() {
    }

    public Falculity(String falculityCode, String name) {
        this.falculityCode = falculityCode;
        this.name = name;
    }

    public String getFalculityCode() {
        return falculityCode;
    }

    public void setFalculityCode(String falculityCode) {
        this.falculityCode = falculityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
