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

public class GetAPICredit extends AsyncTask<String,String,String> {
    private Context context;
    ArrayList<Credit> credits;
    LinhVucAdapter linhVucAdapter;
    RecyclerView recyclerView;

    public GetAPICredit(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        credits=new ArrayList<>();
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
                String ten_goi=jsonObject.getString("ten_goi");
                String credit=jsonObject.getString("credit");
                String so_tien=jsonObject.getString("so_tien");
                String id=String.valueOf(jsonObject.getInt("id"));
                credits.add(new Credit(id,ten_goi,credit,so_tien));
            }
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            CreditAdapter creditAdapter=new CreditAdapter(credits,context);
            recyclerView.setAdapter(creditAdapter);

            //Làm đẹp recyclerview
            DividerItemDecoration dividerItemDecoration=new
                    DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}