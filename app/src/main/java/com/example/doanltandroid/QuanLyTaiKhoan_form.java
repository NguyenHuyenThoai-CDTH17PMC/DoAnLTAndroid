package com.example.doanltandroid;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import android.os.Environment;
import android.provider.MediaStore;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    ImageView img;
    TextView imgdoianh;
    String imageFilePath;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan_form);
        Intent intent = getIntent();
        imgdoianh = findViewById(R.id.txtdoianh);
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
        img = findViewById(R.id.imghinhdaidienql);
        String url = "http://192.168.56.1:8080/Do_An_PHP/public/img/"+hinh_dai_dien;
        Picasso.with(this).load(url).into(img);
        btnCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String duongdan = "http://192.168.56.1/Do_An_PHP/public/api/nguoi-choi/chinhsua-nguoichoi/"+id;

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
            ten_dang_nhap.setText(bitmap.toString());
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                DocumentFile documentFile = DocumentFile.fromSingleUri(this,uri);
                String fileName = documentFile.getName();
                hinh_dai_dien = fileName.toString();
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
    public void chonanh(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE_FOLDER);
    }
}
