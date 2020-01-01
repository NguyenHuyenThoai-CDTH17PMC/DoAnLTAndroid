package com.example.doanltandroid;

public class Credit {
    private String id;
    private String ten_goi;
    private String credit;
    private String so_tien;

    public Credit(String id, String ten_goi, String credit, String so_tien) {
        this.id = id;
        this.ten_goi = ten_goi;
        this.credit = credit;
        this.so_tien = so_tien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_goi() {
        return ten_goi;
    }

    public void setTen_goi(String ten_goi) {
        this.ten_goi = ten_goi;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }
}
