package com.example.doanltandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAPINguoiChoi extends AsyncTask<String,String,String> {
    private Context context;
    String ten_dap_nhap;
    String mat_khau;
    String duongdan;
    ArrayList<NguoiChoi> nguoiChois;
    public GetAPINguoiChoi(Context context, String ten_dap_nhap, String mat_khau){
        nguoiChois=new ArrayList<>();
        this.ten_dap_nhap=ten_dap_nhap;
        this.mat_khau=mat_khau;
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonnguoichoi = new JSONObject(s);
            JSONArray jsonarraydata = jsonnguoichoi.getJSONArray("data");
            for (int i = 0; i < jsonarraydata.length(); i++) {
                NguoiChoi nguoiChoi = new NguoiChoi();
                JSONObject jsonObject = jsonarraydata.getJSONObject(i);
                String id = String.valueOf(jsonObject.getInt("id"));
                String ten_dang_nhap = jsonObject.getString("ten_dang_nhap");
                String mat_khau = jsonObject.getString("mat_khau");
                String email = jsonObject.getString("email");
                String hinh_dai_dien = jsonObject.getString("hinh_dai_dien");
                String diem_cao_nhat = String.valueOf(jsonObject.getInt("diem_cao_nhat"));
                String credit = String.valueOf(jsonObject.getInt("credit"));

                nguoiChoi.setId(id);
                nguoiChoi.setTen_dang_nhap(ten_dang_nhap);
                nguoiChoi.setMat_khau(mat_khau);
                nguoiChoi.setEmail(email);
                nguoiChoi.setCredit(credit);
                nguoiChoi.setDiem_cao_nhat(diem_cao_nhat);
                nguoiChoi.setHinh_dai_dien(hinh_dai_dien);
                nguoiChois.add(nguoiChoi);
            }
            for(int i=0;i<nguoiChois.size();i++){
                    if(ten_dap_nhap.equals(nguoiChois.get(i).ten_dang_nhap) && mat_khau.equals(nguoiChois.get(i).mat_khau)){
                        Intent intent = new Intent(context,ManHinhChinh_form.class);
                        intent.putExtra("ten_dang_nhap",nguoiChois.get(i).ten_dang_nhap);
                        intent.putExtra("credit",nguoiChois.get(i).credit);
                        intent.putExtra("email",nguoiChois.get(i).email);
                        intent.putExtra("id",nguoiChois.get(i).id);
                        intent.putExtra("diem_cao_nhat",nguoiChois.get(i).diem_cao_nhat);
                        intent.putExtra("hinh_dai_dien",nguoiChois.get(i).hinh_dai_dien);
                        intent.putExtra("credit",nguoiChois.get(i).credit);
                        context.startActivity(intent);
                    }

                }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
