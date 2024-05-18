package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class ChuDe implements Serializable {
    private int stt;
    private String tenChuDe;
    private int soLuongDeTai;
    private boolean dangMoDangKy;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public int getSoLuongDeTai() {
        return soLuongDeTai;
    }

    public void setSoLuongDeTai(int soLuongDeTai) {
        this.soLuongDeTai = soLuongDeTai;
    }

    public boolean isDangMoDangKy() {
        return dangMoDangKy;
    }

    public void setDangMoDangKy(boolean dangMoDangKy) {
        this.dangMoDangKy = dangMoDangKy;
    }

    public ChuDe(int stt, String tenChuDe, int soLuongDeTai, boolean dangMoDangKy) {
        this.stt = stt;
        this.tenChuDe = tenChuDe;
        this.soLuongDeTai = soLuongDeTai;
        this.dangMoDangKy = dangMoDangKy;
    }
}
