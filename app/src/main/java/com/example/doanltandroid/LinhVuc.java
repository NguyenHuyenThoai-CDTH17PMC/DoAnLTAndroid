package com.example.doanltandroid;

public class LinhVuc {
  private int id;
  private String tenlinhvuc;

    public LinhVuc(int id, String tenlinhvuc) {
        this.id = id;
        this.tenlinhvuc = tenlinhvuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTenlinhvuc() {
        return tenlinhvuc;
    }

    public void setTenlinhvuc(String tenlinhvuc) {
        this.tenlinhvuc = tenlinhvuc;
    }
}
