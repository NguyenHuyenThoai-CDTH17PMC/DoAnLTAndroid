package com.example.doanltandroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class LuotChoiPackage {
    LuotChoi luotChoi;
    public LuotChoiPackage(LuotChoi luotChoi) {
        this.luotChoi = luotChoi;
    }

    public  String getPackage(){
        JSONObject jsonLuotChoi=new JSONObject();
        StringBuilder stringBuilder=new StringBuilder();
        try {
            jsonLuotChoi.put("nguoi_choi_id",luotChoi.getNguoi_choi_id());
            jsonLuotChoi.put("so_cau",luotChoi.getSo_cau());
            jsonLuotChoi.put("diem",luotChoi.getDiem());
            jsonLuotChoi.put("ngay_gio",luotChoi.getNgay_gio());

            Boolean giatridau=true;
            Iterator iterator=jsonLuotChoi.keys();
            do {
                String key=iterator.next().toString();
                String value=jsonLuotChoi.get(key).toString();
                if(giatridau){
                    giatridau=false;
                }else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(URLEncoder.encode(key,"UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(value,"UTF-8"));
            }while (iterator.hasNext());
            return  stringBuilder.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
