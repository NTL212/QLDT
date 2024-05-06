package com.nhom7.appqldt.ui_qlkh.ui.quanlydetai;

import java.io.Serializable;

public class DeTaiModel implements Serializable {
    private String maDeTai;
    private String tenDeTai;
    private String maChuDe;
    private String tinhTrang;

    public String getMaDeTai() {
        return maDeTai;
    }

    public void setMaDeTai(String maDeTai) {
        this.maDeTai = maDeTai;
    }

    public String getTenDeTai() {
        return tenDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public DeTaiModel(String maDeTai, String tenDeTai, String maChuDe, String tinhTrang) {
        this.maDeTai = maDeTai;
        this.tenDeTai = tenDeTai;
        this.maChuDe = maChuDe;
        this.tinhTrang = tinhTrang;
    }
}
