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

public class GetAPILinhVuc extends AsyncTask<String,String,String> {
    private Context context;
    ArrayList<LinhVuc> linhVucs;
    LinhVucAdapter linhVucAdapter;
    RecyclerView recyclerView;

    public GetAPILinhVuc(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        linhVucs=new ArrayList<>();
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
             String ten_linh_vuc=jsonObject.getString("ten_linh_vuc");
             String id=String.valueOf(jsonObject.getInt("id"));
             linhVucs.add(new LinhVuc(id,ten_linh_vuc));
            }
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            linhVucAdapter=new LinhVucAdapter(linhVucs,context);
            recyclerView.setAdapter(linhVucAdapter);

            //Làm đẹp recyclerview
            DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
