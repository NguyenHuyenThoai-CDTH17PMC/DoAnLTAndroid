package com.example.doanltandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAPINguoiChoiEP extends AsyncTask<String,String,String> {
    private Context context;
    String ten_dap_nhap;
    String Email;
    String matkhaunew;
    ArrayList<NguoiChoi> nguoiChois;
    public GetAPINguoiChoiEP(Context context, String ten_dap_nhap, String email,String matkhaunew){
        nguoiChois=new ArrayList<>();
        this.ten_dap_nhap=ten_dap_nhap;
        this.Email=email;
        this.matkhaunew=matkhaunew;
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
            int x=0;
            for(int i=0;i<nguoiChois.size();i++){
                if(ten_dap_nhap.equals(nguoiChois.get(i).ten_dang_nhap) && Email.equals(nguoiChois.get(i).email)){
                    String duongdan="http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi/quen-mat-khau/"+nguoiChois.get(i).id;
                    PostAPIQuenMatKhau postAPINguoiChoi = (PostAPIQuenMatKhau) new PostAPIQuenMatKhau(context, duongdan,matkhaunew).execute();
                }
                else
                {
                    x++;
                }
            }
            if(x == nguoiChois.size())
            {
                Toast.makeText(context.getApplicationContext(),"Sai tài khoản hoặc mật khẩu!!!",Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
