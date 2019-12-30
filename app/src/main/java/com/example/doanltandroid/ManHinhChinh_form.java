package com.example.doanltandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ManHinhChinh_form extends AppCompatActivity {
    TextView txt;
    TextView txt2;
    String ten_dang_nhap;
    String email;
    String credit;
    String id;
    String hinh_dai_dien;
    String diem_cao_nhat;
    String mxh_id;
    ImageView img;
    String hinhanhfacebook;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh_form);
        txt=findViewById(R.id.txtUsername);
        txt2=findViewById(R.id.txtCredit);

        sharedPreferences=getSharedPreferences("nguoichoi",MODE_PRIVATE);
        id = sharedPreferences.getString("id_nguoichoi","");
        hinh_dai_dien = sharedPreferences.getString("hinh_dai_dien","");
        diem_cao_nhat = sharedPreferences.getString("diem_cao_nhat","");
        credit = sharedPreferences.getString("credit","");
        ten_dang_nhap = sharedPreferences.getString("ten_dang_nhap","");
        email = sharedPreferences.getString("email","");
        mxh_id=sharedPreferences.getString("mxh_id","");
        txt.setText(ten_dang_nhap);
        txt2.setText(credit);
        img = findViewById(R.id.imghinhdaidienql);
        url = "http://10.0.2.2:8080/Do_An_PHP/public/img/"+hinh_dai_dien;
        Picasso.with(this).load(url).into(img);
    }
    public void QuanLiTaiKhoan(View view){
        Intent intent = new Intent(ManHinhChinh_form.this,QuanLyTaiKhoan_form.class);
        startActivity(intent);
    }
    public void TroChoiMoi(View view){
        Intent intent=new Intent(ManHinhChinh_form.this,LinhVucCauHoi_form.class);
        startActivity(intent);
    }
    public void BangXepHang(View view){
        Intent intent=new Intent(ManHinhChinh_form.this,BangXepHang_form.class);
        startActivity(intent);
    }
    public void MuaThemCredit(View view){
        Intent intent=new Intent(ManHinhChinh_form.this,Credit_form.class);
        startActivity(intent);
    }

}
