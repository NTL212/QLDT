package com.nhom7.appqldt.Models;

import java.io.Serializable;
import java.util.Date;

public class ThanhVienDeTai implements Serializable {
    private int stt;
    private String MSSV;
    private String hoTen;

    private String lop;
    private String khoa;
    private String ngayThamGia;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(String ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public ThanhVienDeTai(int stt, String MSSV, String hoTen, String lop, String khoa, String ngayThamGia) {
        this.stt = stt;
        this.MSSV = MSSV;
        this.hoTen = hoTen;
        this.lop = lop;
        this.khoa = khoa;
        this.ngayThamGia = ngayThamGia;
    }


}
