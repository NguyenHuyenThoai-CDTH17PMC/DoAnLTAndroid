package com.example.doanltandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class BangXepHang_form extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String duongdan = "http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang_form);
        recyclerView=findViewById(R.id.recyclerviewxh);
        GetAPIXepHang getAPILinhVuc= (GetAPIXepHang) new GetAPIXepHang(BangXepHang_form.this,recyclerView).execute(duongdan);
    }
}
