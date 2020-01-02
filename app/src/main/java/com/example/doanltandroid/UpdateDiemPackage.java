package com.example.doanltandroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class UpdateDiemPackage {
    NguoiChoi nguoiChoi;
    public UpdateDiemPackage(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public  String getPackage(){
        JSONObject jsonNguoiChoi=new JSONObject();
        StringBuilder stringBuilder=new StringBuilder();
        try {
            jsonNguoiChoi.put("diem_cao_nhat",nguoiChoi.getDiem_cao_nhat());
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
