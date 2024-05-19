package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class DeTai implements Serializable {
    private String maDeTai;
    private String tenDeTai;
    private String tenChuDe;
    private String tinhTrang;

    public DeTai() {
    }

    public DeTai(String maDeTai, String tenDeTai, String tenChuDe, String tinhTrang) {
        this.maDeTai = maDeTai;
        this.tenDeTai = tenDeTai;
        this.tenChuDe = tenChuDe;
        this.tinhTrang = tinhTrang;
    }

    public String getMaDeTai() {
        return maDeTai;
    }

    public String getTenDeTai() {
        return tenDeTai;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setMaDeTai(String maDeTai) {
        this.maDeTai = maDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
