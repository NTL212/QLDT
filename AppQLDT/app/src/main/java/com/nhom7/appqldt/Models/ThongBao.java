package com.nhom7.appqldt.Models;

import java.io.Serializable;
import java.util.Date;

public class ThongBao implements Serializable {
    private String tieuDe;
    private String nguoiGui;
    private String ngayGui;
    private String noiDung;


    public ThongBao() {
    }

    public ThongBao(String tieuDe, String nguoiGui, String ngayGui, String noiDung) {
        this.tieuDe = tieuDe;
        this.nguoiGui = nguoiGui;
        this.ngayGui = ngayGui;
        this.noiDung = noiDung;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getNguoiGui() {
        return nguoiGui;
    }

    public String getNgayGui() {
        return ngayGui;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
    }

    public void setNgayGui(String ngayGui) {
        this.ngayGui = ngayGui;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
