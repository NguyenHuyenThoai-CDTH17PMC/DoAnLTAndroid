package com.example.doanltandroid;

public class LinhVuc {
  private String id;
  private String tenlinhvuc;


    public LinhVuc(String id, String tenlinhvuc) {
        this.id = id;
        this.tenlinhvuc = tenlinhvuc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenlinhvuc() {
        return tenlinhvuc;
    }

    public void setTenlinhvuc(String tenlinhvuc) {
        this.tenlinhvuc = tenlinhvuc;
    }
}
