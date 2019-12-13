package com.example.doanltandroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class ChiTietLuotChoiPackage {
    ChiTietLuotChoi chiTietLuotChoi;

    public ChiTietLuotChoiPackage(ChiTietLuotChoi chiTietLuotChoi) {
        this.chiTietLuotChoi = chiTietLuotChoi;
    }
    public  String getPackage(){
        JSONObject jsonChiTietLuotChoi=new JSONObject();
        StringBuilder stringBuilder=new StringBuilder();
        try {
            jsonChiTietLuotChoi.put("luot_choi_id",chiTietLuotChoi.getLuot_choi_id());
            jsonChiTietLuotChoi.put("cau_hoi_id",chiTietLuotChoi.getCau_hoi_id());
            jsonChiTietLuotChoi.put("phuong_an",chiTietLuotChoi.getPhuong_an());
            jsonChiTietLuotChoi.put("diem",chiTietLuotChoi.getDiem());

            Boolean giatridau=true;
            Iterator iterator=jsonChiTietLuotChoi.keys();
            do {
                String key=iterator.next().toString();
                String value=jsonChiTietLuotChoi.get(key).toString();
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
