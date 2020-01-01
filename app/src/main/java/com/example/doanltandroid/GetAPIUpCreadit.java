package com.example.doanltandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAPIUpCreadit extends AsyncTask<String,String,String> {
    private Context context;
    String id;
    ArrayList<NguoiChoi> nguoiChois;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public GetAPIUpCreadit(Context context, String id) {
        nguoiChois = new ArrayList<>();
        this.id = id;
        this.context = context;
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
                String credit = String.valueOf(jsonObject.getInt("credit"));
                nguoiChoi.setId(id);
                nguoiChoi.setCredit(credit);
                nguoiChois.add(nguoiChoi);
            }
            int x = 0;
            for (int i = 0; i < nguoiChois.size(); i++) {
                if (id.equals(nguoiChois.get(i).getId()) && Integer.parseInt(nguoiChois.get(i).credit) >= 10) {
                    int creditupdate = Integer.parseInt(nguoiChois.get(i).credit) - 10;
                    String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi/update-credit/"+id;
                    PostUpCredit postAPINguoiChoi = (PostUpCredit) new PostUpCredit(context, duongdan, String.valueOf(creditupdate)).execute();
                    sharedPreferences=context.getSharedPreferences("trangthai1",context.MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putString("trangthai","ok");
                    editor.commit();
                }
                else
                {
                    x++;
                }
            }
            if(x==nguoiChois.size()){
                sharedPreferences=context.getSharedPreferences("trangthai1",context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("trangthai","no");
                editor.commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

