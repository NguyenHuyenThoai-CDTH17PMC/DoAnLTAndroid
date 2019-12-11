package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login_form extends AppCompatActivity {
    EditText edt;
    EditText edt2;
    String đuongdan="http://192.168.1.17:8080/Do_An_PHP/public/api/nguoi-choi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        edt = findViewById(R.id.edtTendangnhap);
        edt2 = findViewById(R.id.edtMatkhau);
    }
    public void Login(View view){
        String tendn = edt.getText().toString();
        String mk = edt2.getText().toString();
        GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this,tendn,mk).execute(đuongdan);
    }
}
