package com.example.doanltandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class GetAPIFacebook extends AsyncTask<String,String,String> {
    private Context context;
    String email;
    String Email;
    String firstname;
    String userID ;
    String hinh_dai_dien_fb;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<NguoiChoi> nguoiChois;
    public GetAPIFacebook(Context context, String email){
        nguoiChois=new ArrayList<>();
        this.email=email;
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
                if(email.equals(nguoiChois.get(i).getEmail())){
                    sharedPreferences=context.getSharedPreferences("nguoichoi",context.MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putString("id_nguoichoi",nguoiChois.get(i).id);
                    editor.putString("ten_dang_nhap",nguoiChois.get(i).ten_dang_nhap);
                    editor.putString("credit",nguoiChois.get(i).credit);
                    editor.putString("email",nguoiChois.get(i).email);
                    editor.putString("matkhau",nguoiChois.get(i).mat_khau);
                    editor.putString("diem_cao_nhat",nguoiChois.get(i).diem_cao_nhat);
                    editor.putString("hinh_dai_dien",nguoiChois.get(i).hinh_dai_dien);
                    editor.putString("mxh_id",nguoiChois.get(i).mxh_id);
                    editor.commit();

                    Intent intent = new Intent(context,ManHinhChinh_form.class);

                    context.startActivity(intent);
                }
                else
                {
                    x++;
                }
            }
            if(x == nguoiChois.size())
            {
                String duongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi/them-nguoi-choi";
                sharedPreferences=context.getSharedPreferences("nguoichoifacebook",context.MODE_PRIVATE);
                Email = sharedPreferences.getString("email","");
                firstname = sharedPreferences.getString("firstname","");
                userID = sharedPreferences.getString("id_facebook","");
                hinh_dai_dien_fb = sharedPreferences.getString("hinh_dai_dien","");
                PostAPIFacebook postAPIFacebook= (PostAPIFacebook) new PostAPIFacebook(context,duongdan,firstname,Email,userID,hinh_dai_dien_fb).execute();
            }
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
