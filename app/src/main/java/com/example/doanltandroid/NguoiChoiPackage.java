package com.example.doanltandroid;



import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class NguoiChoiPackage {
    NguoiChoi nguoiChoi;
    public NguoiChoiPackage(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public  String getPackage(){
        JSONObject jsonNguoiChoi=new JSONObject();
        StringBuilder stringBuilder=new StringBuilder();
        try {
            jsonNguoiChoi.put("ten_dang_nhap",nguoiChoi.getTen_dang_nhap());
            jsonNguoiChoi.put("mat_khau",nguoiChoi.getMat_khau());
            jsonNguoiChoi.put("email",nguoiChoi.getEmail());
            jsonNguoiChoi.put("hinh_dai_dien", nguoiChoi.getHinh_dai_dien());
            jsonNguoiChoi.put("diem_cao_nhat",nguoiChoi.getDiem_cao_nhat());
            jsonNguoiChoi.put("credit",nguoiChoi.getCredit());
            Log.d("hinhanh",nguoiChoi.getHinh_dai_dien());
            Boolean giatridau=true;
            Iterator iterator=jsonNguoiChoi.keys();
             do {
                 String key=iterator.next().toString();
                 String value=jsonNguoiChoi.get(key).toString();
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
