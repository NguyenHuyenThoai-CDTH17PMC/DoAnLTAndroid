package com.example.doanltandroid;

import org.json.JSONException;
import org.json.JSONObject;

public class CauHoi {

    private String cauhoi;
    private String panA;
    private String panB;
    private String panC;
    private String panD;
    private String dapan;

    public CauHoi(String cauhoi, String panA, String panB, String panC, String panD, String dapan) {
        this.cauhoi = cauhoi;
        this.panA = panA;
        this.panB = panB;
        this.panC = panC;
        this.panD = panD;
        this.dapan = dapan;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
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

    public static CauHoi ParseJSON(JSONObject job) throws JSONException {
        return  new CauHoi(
                job.getString("cauhoi"),
                job.getString("pA"),
                job.getString("pB"),
                job.getString("pC"),
                job.getString("pD"),
                job.getString("dapan")
        );
    }

}