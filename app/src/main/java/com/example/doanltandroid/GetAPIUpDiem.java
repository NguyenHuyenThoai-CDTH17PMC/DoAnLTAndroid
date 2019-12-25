package com.example.doanltandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class GetAPIUpDiem extends AsyncTask<String,String,String> {
    private Context context;
    String diem_cao_nhat;
    String id;
    ArrayList<NguoiChoi> nguoiChois;
    SharedPreferences sharedPreferences;
    public GetAPIUpDiem(Context context, String diem_cao_nhat,String id){
        nguoiChois=new ArrayList<>();
        this.diem_cao_nhat=diem_cao_nhat;
        this.id = id;
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
            for(int i=0;i<nguoiChois.size();i++) {
                if (id.equals(nguoiChois.get(i).id) && Integer.parseInt(diem_cao_nhat) > Integer.parseInt(nguoiChois.get(i).diem_cao_nhat)) {
                    String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi/update-diem/5";
                    PostUpDiem postAPINguoiChoi = (PostUpDiem) new PostUpDiem(context, duongdan, diem_cao_nhat).execute();
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
