package com.nhom7.appqldt.Models;

import com.nhom7.appqldt.Activitys.GiangVien.DanhSachTVActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ThanhVien implements Serializable {
    private int stt;
    private String hoTen;
    private String MSSV;
    private Date ngaySinh;
    private String lop;
    private String khoa;

    public ThanhVien(List<ThanhVien> listTV, DanhSachTVActivity danhSachTVActivity) {
    }

    public ThanhVien(int stt, String hoTen, String MSSV, Date ngaySinh, String lop, String khoa) {
        this.stt = stt;
        this.hoTen = hoTen;
        this.MSSV = MSSV;
        this.ngaySinh = ngaySinh;
        this.lop = lop;
        this.khoa = khoa;
    }

    public int getStt() {
        return stt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getMSSV() {
        return MSSV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getLop() {
        return lop;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
}
