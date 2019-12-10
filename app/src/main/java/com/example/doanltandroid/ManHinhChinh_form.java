package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ManHinhChinh_form extends AppCompatActivity {
    TextView txt;
    TextView txt2;
    String ten_dang_nhap;
    String email;
    String credit;
    String id;
    String hinh_dai_dien;
    String diem_cao_nhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh_form);
        txt=findViewById(R.id.txtUsername);
        txt2=findViewById(R.id.txtCredit);
        Intent intent = getIntent();
        hinh_dai_dien = intent.getStringExtra("hinh_dai_dien");
        diem_cao_nhat = intent.getStringExtra("diem_cao_nhat");
        credit = intent.getStringExtra("credit");
        ten_dang_nhap = intent.getStringExtra("ten_dang_nhap");
        email = intent.getStringExtra("email");
        credit = intent.getStringExtra("credit");
        txt.setText(ten_dang_nhap);
        txt2.setText(credit);
        id = intent.getStringExtra("id");
    }
    public void QuanLiTaiKhoan(View view){
        Intent intent = new Intent(ManHinhChinh_form.this,QuanLyTaiKhoan_form.class);
        intent.putExtra("ten_dang_nhap",ten_dang_nhap);
        intent.putExtra("email",email);
        intent.putExtra("id",id);
        intent.putExtra("diem_cao_nhat",diem_cao_nhat);
        intent.putExtra("hinh_dai_dien",hinh_dai_dien);
        intent.putExtra("credit",credit);
        startActivity(intent);
    }
}
