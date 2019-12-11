package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.RelativeLayout;

public class QuanLyTaiKhoan_form extends AppCompatActivity {
=======
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuanLyTaiKhoan_form extends AppCompatActivity {
    EditText ten_dang_nhap;
    EditText email;
    EditText mk;
    EditText mknew;
    String id;
    String Tendangnhap;
    String Email;
    String matkhau;
    String xn_maukhau;
    String hinh_dai_dien;
    String diem_cao_nhat;
    String credit;
    Button btnCN;
>>>>>>> f5e41092b76e7abf278e7dd8b7a93a267ea5865d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan_form);
        Intent intent = getIntent();

        mk = findViewById(R.id.edtdoimatkhau);
        mknew=findViewById(R.id.edtXacnhanMK);
        email=findViewById(R.id.edtEmail);
        ten_dang_nhap=findViewById(R.id.edtTendangnhap);
        btnCN=findViewById(R.id.btnCapnhat);

        ten_dang_nhap.setText(intent.getStringExtra("ten_dang_nhap"));
        Tendangnhap=ten_dang_nhap.getText().toString();
        email.setText(intent.getStringExtra("email"));
        Email=email.getText().toString();
        id = intent.getStringExtra("id");
        hinh_dai_dien = intent.getStringExtra("hinh_dai_dien");
        diem_cao_nhat = intent.getStringExtra("diem_cao_nhat");
        credit = intent.getStringExtra("credit");




        btnCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duongdan = "http://192.168.1.17:8080/Do_An_PHP/public/api/nguoi-choi/chinhsua-nguoichoi/"+id;
                matkhau=mk.getText().toString();
                xn_maukhau=mknew.getText().toString();
               if(matkhau.equals(xn_maukhau)){
                   PostAPIChinhSuaNguoiChoi postAPINguoiChoi= (PostAPIChinhSuaNguoiChoi) new PostAPIChinhSuaNguoiChoi(QuanLyTaiKhoan_form.this,duongdan, Tendangnhap,Email, mk.getText().toString(),hinh_dai_dien,diem_cao_nhat,credit).execute();
               }
               else {
                   Toast.makeText(QuanLyTaiKhoan_form.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

}
