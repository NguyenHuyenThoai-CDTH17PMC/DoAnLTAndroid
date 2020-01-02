package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class LichSuChoi_form extends AppCompatActivity {

    String nguoi_choi_id;
    private RecyclerView recyclerView;
    private String duongdan="http://192.168.56.1/Do_An_PHP/public/api/luot-choi/lay-luot-choi?nguoichoi_id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi_form);
        recyclerView=findViewById(R.id.recy_lsluotchoi);
        Intent intent=getIntent();
        nguoi_choi_id = intent.getStringExtra("id_nguoichoi");
        GetAPILichSuLuotChoi getAPILichSuLuotChoi= (GetAPILichSuLuotChoi) new GetAPILichSuLuotChoi(this,recyclerView).execute(duongdan+nguoi_choi_id);

    }
}
