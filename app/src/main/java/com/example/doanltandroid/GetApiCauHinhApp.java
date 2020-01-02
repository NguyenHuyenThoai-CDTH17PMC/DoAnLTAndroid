package com.example.doanltandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetApiCauHinhApp extends AsyncTask<String,String,String> {
    private Context context;
    String duongdan;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<CauHinhApp> cauHinhApps;
    String id;
    String co_hoi_sai;
    String thoi_gian_tra_loi;
    public GetApiCauHinhApp(Context context){
        cauHinhApps=new ArrayList<>();
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
                CauHinhApp nguoiChoi = new CauHinhApp();
                JSONObject jsonObject = jsonarraydata.getJSONObject(i);

                id = String.valueOf(jsonObject.getInt("id"));
                co_hoi_sai = String.valueOf(jsonObject.getInt("co_hoi_sai"));
                thoi_gian_tra_loi = String.valueOf(jsonObject.getInt("thoi_gian_tra_loi"));

                nguoiChoi.setId(id);
                nguoiChoi.setCo_hoi_sai(co_hoi_sai);
                nguoiChoi.setTra_loi_cau_hoi(thoi_gian_tra_loi);
                cauHinhApps.add(nguoiChoi);
            }
            sharedPreferences = context.getSharedPreferences("cauhinhapp", context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("co_hoi_sai",cauHinhApps.get(0).getCo_hoi_sai());
            editor.putString("thoi_gian_tra_loi",cauHinhApps.get(0).getTra_loi_cau_hoi());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
