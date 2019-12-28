package com.example.doanltandroid;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DangKy_form extends AppCompatActivity {
    private String duongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi/them-nguoi-choi";
    private EditText edit_tendangnhap;
    private EditText edit_email;
    private EditText edit_matkhau;
    private EditText edit_xacnhanmatkhau;

    public static final String UPLOAD_KEY = "hinh_anh";
    public static final String UPLOAD_URL = "upload";
    final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    private Bitmap bitmap;
    private static String hinhanh;


    private ImageView imgavartar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_form);
        edit_tendangnhap=findViewById(R.id.edtTendangnhap);
        edit_email=findViewById(R.id.edtEmail);
        edit_matkhau=findViewById(R.id.edtMatkhau);
        edit_xacnhanmatkhau=findViewById(R.id.edtXacnhanLaiMatKhau);
        imgavartar=findViewById(R.id.imganhdaidien);




    }
    public void DangKi(View view) throws NoSuchAlgorithmException {
       String  makhau= edit_matkhau.getText().toString();

       String xacnhanlaimatkhau=edit_xacnhanmatkhau.getText().toString();
       if(makhau.equals(xacnhanlaimatkhau)){

 //          PostAPINguoiChoi postAPINguoiChoi= (PostAPINguoiChoi) new PostAPINguoiChoi(DangKy_form.this,duongdan,edit_tendangnhap,edit_email,edit_matkhau,hinhanh).execute();
           Map<String,String> jsonNguoiChoi= new HashMap<>();

           jsonNguoiChoi.put("ten_dang_nhap","concu");
           jsonNguoiChoi.put("mat_khau","1234566");
           jsonNguoiChoi.put("email","concu8.35cm@gmail.com");
           jsonNguoiChoi.put("hinh_dai_dien", ReadAPI.encodeBitmapToString(bitmap)); // hình đại diện thì nó truyền sao? don gian, may nen chuoi base 64 thoi
           jsonNguoiChoi.put("diem_cao_nhat","835");
           jsonNguoiChoi.put("credit","835");
           jsonNguoiChoi.put("mxh_id","0");
           ReadAPI.PostAPI(this,jsonNguoiChoi,duongdan);
       }
       else {
           Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
       }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            // Lay Uri den file duoc chon
            filePath = data.getData();


            try {
                // Lay hinh anh Bitmap tu Uri
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                hinhanh=ReadAPI.encodeBitmapToString(bitmap);


                // Hien thi hinh anh len ImageView
                imgavartar.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void chonanh(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select image"),
                PICK_IMAGE_REQUEST);
    }
    // Lay ten tap tin tu Uri
    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }




}
