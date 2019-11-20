package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LinhVucCauHoi_form extends AppCompatActivity {

    private RequestQueue requestQueue;
    private final ArrayList<LinhVuc>linhVucs;
    private LinhVucAdapter linhVucAdapter;
    private RecyclerView recyclerView;
    public LinhVucCauHoi_form() {
        this.linhVucs = new ArrayList<>();
    }
    private String url="http://192.168.1.6/Do_An_PHP/public/api/linh-vuc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi_form);
        ReadJSON();

    }

    public void ReadJSON(){
        requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject item=jsonArray.getJSONObject(i);
                        int id=item.getInt("id") ;
                        String ten_linh_vuc=item.getString("ten_linh_vuc");
                        linhVucs.add(new LinhVuc( id,ten_linh_vuc));
                        recyclerView=findViewById(R.id.recyclerviewdslinhvuc);
                        recyclerView.setLayoutManager(new LinearLayoutManager(LinhVucCauHoi_form.this));
                        linhVucAdapter=new LinhVucAdapter(linhVucs,LinhVucCauHoi_form.this);
                        recyclerView.setAdapter(linhVucAdapter);
                    }

                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {

                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(request);
    }
    public void ChonLinhVuc_LayCauHoi(View view){

        Intent  intent=new Intent(LinhVucCauHoi_form.this,CauHoiLayTheoIDLinhVuc.class);
        startActivity(intent);
    }


}
