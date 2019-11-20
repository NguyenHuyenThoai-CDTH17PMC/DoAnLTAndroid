package com.example.doanltandroid;

public class CauHoiLayChon {
    private  int id;
    private String noidung;
    private int linh_vuc_id;
    private String panA;
    private String panB;
    private String panC;
    private String panD;
    private String dapan;


    public CauHoiLayChon(int id, String noidung, int linh_vuc_id, String panA, String panB, String panC, String panD, String dapan) {
        this.id = id;

        this.noidung = noidung;
        this.linh_vuc_id = linh_vuc_id;
        this.panA = panA;
        this.panB = panB;
        this.panC = panC;
        this.panD = panD;
        this.dapan = dapan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getLinh_vuc_id() {
        return linh_vuc_id;
    }

    public void setLinh_vuc_id(int linh_vuc_id) {
        this.linh_vuc_id = linh_vuc_id;
    }

    public String getPanA() {
        return panA;
    }

    public void setPanA(String panA) {
        this.panA = panA;
    }

    public String getPanB() {
        return panB;
    }

    public void setPanB(String panB) {
        this.panB = panB;
    }

    public String getPanC() {
        return panC;
    }

    public void setPanC(String panC) {
        this.panC = panC;
    }

    public String getPanD() {
        return panD;
    }

    public void setPanD(String panD) {
        this.panD = panD;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
}
