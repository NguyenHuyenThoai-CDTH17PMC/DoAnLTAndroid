package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy_form extends AppCompatActivity {
    private String duongdan="http://192.168.1.3:8080/Do_An_PHP/public/api/nguoi-choi/them-nguoi-choi";
    private EditText edit_tendangnhap;
    private EditText edit_email;
    private EditText edit_matkhau;
    private EditText edit_xacnhanmatkhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_form);
        edit_tendangnhap=findViewById(R.id.edtTendangnhap);
        edit_email=findViewById(R.id.edtEmail);
        edit_matkhau=findViewById(R.id.edtMatkhau);
        edit_xacnhanmatkhau=findViewById(R.id.edtXacnhanLaiMatKhau);
    }
    public void DangKi(View view) {
       String makhau=edit_matkhau.getText().toString();
       String xacnhanlaimatkhau=edit_xacnhanmatkhau.getText().toString();
       if(makhau.equals(xacnhanlaimatkhau)){
           PostAPINguoiChoi postAPINguoiChoi= (PostAPINguoiChoi) new PostAPINguoiChoi(DangKy_form.this,duongdan,edit_tendangnhap,edit_email,edit_matkhau).execute();
       }
       else {
           Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
       }

    }
}
