package com.example.doanltandroid;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAPIXepHang extends AsyncTask<String,String,String> {
    private Context context;
    ArrayList<XepHang> xepHangs;
    XepHangAdapter xepHangAdapter;
    RecyclerView recyclerView;

    public GetAPIXepHang(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        xepHangs=new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonxephang=new JSONObject(s);
            JSONArray jsonarraydata=jsonxephang.getJSONArray("data");
            for(int i=0;i<jsonarraydata.length();i++){
                JSONObject jsonObject=jsonarraydata.getJSONObject(i);
                String ten_dang_nhap=jsonObject.getString("ten_dang_nhap");
                String diem_cao_nhat=String.valueOf(jsonObject.getInt("diem_cao_nhat"));
                xepHangs.add(new XepHang(ten_dang_nhap,diem_cao_nhat));
            }
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            xepHangAdapter = new XepHangAdapter(xepHangs,context);
            recyclerView.setAdapter(xepHangAdapter);

            //Làm đẹp recyclerview
            DividerItemDecoration dividerItemDecoration=new
                    DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
