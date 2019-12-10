package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class LinhVucCauHoi_form extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinhVucAdapter linhVucAdapter;
    private ArrayList<LinhVuc>linhVucs;
    private String đuongdan="http://172.19.200.255:8080/Do_An_PHP/public/api/linh-vuc";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi_form);
        recyclerView=findViewById(R.id.recyclerviewdslinhvuc);
        GetAPILinhVuc getAPILinhVuc= (GetAPILinhVuc) new GetAPILinhVuc(LinhVucCauHoi_form.this,recyclerView).execute(đuongdan);
    }





}
