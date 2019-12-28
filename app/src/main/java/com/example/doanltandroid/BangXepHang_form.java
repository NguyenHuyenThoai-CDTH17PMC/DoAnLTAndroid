package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BangXepHang_form extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang_form);
        recyclerView=findViewById(R.id.recyclerviewxh);
        GetAPIXepHang getAPILinhVuc= (GetAPIXepHang) new GetAPIXepHang(BangXepHang_form.this,recyclerView).execute(duongdan);
    }
}
