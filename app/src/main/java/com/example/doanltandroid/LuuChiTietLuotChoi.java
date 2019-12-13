package com.example.doanltandroid;

public class LuuChiTietLuotChoi {
   private String cauhoi_id;
   private String phuong_an;
   private String diem;
    public LuuChiTietLuotChoi(String cauhoi_id, String phuong_an, String diem) {
        this.cauhoi_id = cauhoi_id;
        this.phuong_an = phuong_an;
        this.diem = diem;
    }

    public String getCauhoi_id() {
        return cauhoi_id;
    }

    public void setCauhoi_id(String cauhoi_id) {
        this.cauhoi_id = cauhoi_id;
    }

    public String getPhuong_an() {
        return phuong_an;
    }

    public void setPhuong_an(String phuong_an) {
        this.phuong_an = phuong_an;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }
}
