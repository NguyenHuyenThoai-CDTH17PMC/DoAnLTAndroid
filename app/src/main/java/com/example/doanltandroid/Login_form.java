package com.example.doanltandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login_form extends AppCompatActivity {

    EditText edt;
    EditText edt2;

    String đuongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        edt = findViewById(R.id.edtTendangnhap);
        edt2 = findViewById(R.id.edtMatkhau);
        SignInButton signInButton = findViewById(R.id.sign_in_button);



    }

    public void Login(View view) throws NoSuchAlgorithmException {
        String tendn = edt.getText().toString();
        String mk = StringMD5.convertHashToString(edt2.getText().toString());
        GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this, tendn, mk).execute(đuongdan); }

    public void Dangky(View view) {
        Intent intent = new Intent(Login_form.this, DangKy_form.class);
        startActivity(intent);
    }
    private String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }




}
