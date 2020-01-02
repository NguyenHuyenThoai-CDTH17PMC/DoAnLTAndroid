package com.example.doanltandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAPILichSuLuotChoi extends AsyncTask<String,String,String> {
    private Context context;
    ArrayList<LuotChoi> luotChois;
    LuotChoiAdapter luotChoiAdapter;
    RecyclerView recyclerView;

    public GetAPILichSuLuotChoi(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        luotChois=new ArrayList<>();

    }

    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonLinhVuc=new JSONObject(s);

            JSONArray jsonarraydata=jsonLinhVuc.getJSONArray("data");
            for(int i=0;i<jsonarraydata.length();i++){
                JSONObject jsonObject=jsonarraydata.getJSONObject(i);
                String id=String.valueOf(jsonObject.getInt("id"));
                String nguoi_choi_id=String.valueOf(jsonObject.getInt("nguoi_choi_id"));
                String so_cau=String.valueOf(jsonObject.getInt("so_cau"));
                String diem=String.valueOf(jsonObject.getInt("diem"));
                String ngay_gio=jsonObject.getString("ngay_gio");
                LuotChoi luotChoi=new LuotChoi();
                luotChoi.setId(id);
                luotChoi.setNguoi_choi_id(nguoi_choi_id);
                luotChoi.setSo_cau(so_cau);
                luotChoi.setDiem(diem);
                luotChoi.setNgay_gio(ngay_gio);
                luotChois.add(luotChoi);
            }
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            luotChoiAdapter=new LuotChoiAdapter(luotChois,context);
            recyclerView.setAdapter(luotChoiAdapter);

            //Làm đẹp recyclerview
            DividerItemDecoration dividerItemDecoration=new
                    DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

