package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class CauHoiLayTheoIDLinhVuc extends AppCompatActivity  {

    private RequestQueue requestQueue;
    String url="http://192.168.1.6/Do_An_PHP/public/api/cau-hoi?linh_vuc=1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_lay_theo_idlinh_vuc);
        ReadJSON();
        Button next=findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(CauHoiLayTheoIDLinhVuc.this);
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject item=jsonArray.getJSONObject(i);
                                int id=item.getInt("id");
                                String noi_dung=item.getString("noi_dung");
                                int linh_vuc_id=item.getInt("linh_vuc_id");
                                String phuong_an_a=item.getString("phuong_an_a");
                                String phuong_an_b=item.getString("phuong_an_b");
                                String phuong_an_c=item.getString("phuong_an_c");
                                String phuong_an_d=item.getString("phuong_an_d");
                                String dap_an=item.getString("dap_an");;
                                TextView noidung=findViewById(R.id.cauhoi);
                                TextView dapan_a=findViewById(R.id.panA);
                                TextView dapan_b=findViewById(R.id.panB);
                                TextView dapan_c=findViewById(R.id.panC);
                                TextView dapan_d=findViewById(R.id.panD);
                                TextView stt=findViewById(R.id.txtcauhoi);
                                noidung.setText(noi_dung);
                                dapan_a.setText(phuong_an_a);
                                dapan_b.setText(phuong_an_b);
                                dapan_c.setText(phuong_an_c);
                                dapan_d.setText(phuong_an_d);
                                stt.setText(String.valueOf(id));
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
        });

    }
    public void ReadJSON(){
        requestQueue = Volley.newRequestQueue(CauHoiLayTheoIDLinhVuc.this);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject item=jsonArray.getJSONObject(i);
                        int id=item.getInt("id");
                        String noi_dung=item.getString("noi_dung");
                        int linh_vuc_id=item.getInt("linh_vuc_id");
                        String phuong_an_a=item.getString("phuong_an_a");
                        String phuong_an_b=item.getString("phuong_an_b");
                        String phuong_an_c=item.getString("phuong_an_c");
                        String phuong_an_d=item.getString("phuong_an_d");
                        String dap_an=item.getString("dap_an");;
                        TextView noidung=findViewById(R.id.cauhoi);
                        TextView dapan_a=findViewById(R.id.panA);
                        TextView dapan_b=findViewById(R.id.panB);
                        TextView dapan_c=findViewById(R.id.panC);
                        TextView dapan_d=findViewById(R.id.panD);
                        TextView stt=findViewById(R.id.txtcauhoi);
                        noidung.setText(noi_dung);
                        dapan_a.setText(phuong_an_a);
                        dapan_b.setText(phuong_an_b);
                        dapan_c.setText(phuong_an_c);
                        dapan_d.setText(phuong_an_d);
                        stt.setText(String.valueOf(id));
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




}
