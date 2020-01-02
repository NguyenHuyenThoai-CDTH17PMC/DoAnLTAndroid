package com.example.doanltandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class QuanLyTaiKhoan_form extends AppCompatActivity {
    EditText ten_dang_nhap;
    EditText email;
    EditText mk;
    EditText mknew;
    public String id;
    String matkhau;
    String xn_maukhau;
    String hinh_dai_dien;
    String diem_cao_nhat;
    String credit;
    String tendangnhap;
    Button btnCN;
    String Email;
    ImageView img;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;
    String hinhmoi;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan_form);

        mk = findViewById(R.id.edtdoimatkhau);
        mknew = findViewById(R.id.edtXacnhanMK);
        email=findViewById(R.id.edtEmailqm);
        ten_dang_nhap=findViewById(R.id.edtTendangnhapqm);
        btnCN=findViewById(R.id.btnCapnhat);

        sharedPreferences=getSharedPreferences("nguoichoi",MODE_PRIVATE);
        id = sharedPreferences.getString("id_nguoichoi","");
        hinh_dai_dien = sharedPreferences.getString("hinh_dai_dien","");
        diem_cao_nhat = sharedPreferences.getString("diem_cao_nhat","");
        credit = sharedPreferences.getString("credit","");
        String ten_dang_nhap_get = sharedPreferences.getString("ten_dang_nhap","");
        String email_get = sharedPreferences.getString("email","");

        ten_dang_nhap.setText(ten_dang_nhap_get);
        email.setText(email_get);

        tendangnhap = ten_dang_nhap.getText().toString();
        Email = email.getText().toString();
        img = findViewById(R.id.imghinhdaidienql);


        String url = "http://192.168.56.1:8080/Do_An_PHP/public/img/"+hinh_dai_dien;
        Picasso.with(this).load(url).into(img);

        btnCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkhau = mk.getText().toString();
                xn_maukhau = mknew.getText().toString();
                if(matkhau.equals(xn_maukhau)){
                    if(hinhmoi!=null){
                        String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi/chinhsua-nguoichoi/"+id;
                        try {
                            PostAPIChinhSuaNguoiChoi postAPINguoiChoi = (PostAPIChinhSuaNguoiChoi) new PostAPIChinhSuaNguoiChoi(QuanLyTaiKhoan_form.this, duongdan, ten_dang_nhap.getText().toString(), email.getText().toString(), mk.getText().toString(),hinhmoi,diem_cao_nhat,credit).execute();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }else{
                        String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi/chinhsua-nguoichoi/"+id;
                        try {
                            PostAPIChinhSuaNguoiChoi postAPINguoiChoi = (PostAPIChinhSuaNguoiChoi) new PostAPIChinhSuaNguoiChoi(QuanLyTaiKhoan_form.this, duongdan, ten_dang_nhap.getText().toString(), email.getText().toString(), mk.getText().toString(),hinh_dai_dien,diem_cao_nhat,credit).execute();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "2 mật khẩu không trùng khớp!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            String duongdanurl;
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                hinhmoi=encodeBitmapToString(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void chupanh(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CODE_CAMERA);
    }
    public String encodeBitmapToString(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }
    public void chonanh(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE_FOLDER);
    }
}
