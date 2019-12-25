package com.example.doanltandroid;

public class XepHang {
    private String ten_dang_nhap;
    private String diem_cao_nhat;

    public XepHang(String ten_dang_nhap, String diem_cao_nhat) {
        this.ten_dang_nhap = ten_dang_nhap;
        this.diem_cao_nhat = diem_cao_nhat;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getDiem_cao_nhat() {
        return diem_cao_nhat;
    }

    public void setDiem_cao_nhat(String diem_cao_nhat) {
        this.diem_cao_nhat = diem_cao_nhat;
    }
}
