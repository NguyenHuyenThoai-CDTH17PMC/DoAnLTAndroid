package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuenMatKhau_form extends AppCompatActivity {
    String ten_dang_nhap;
    String email;
    String mknew;
    String xn_mknew;
    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edt4;
    String duongdan="http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau_form);
        edt1 = findViewById(R.id.edtTendangnhapqm);
        edt2 = findViewById(R.id.edtEmailqm);
        edt3 = findViewById(R.id.edtmatkhauqm);
        edt4 = findViewById(R.id.edtxn_matkhauqm);
    }
    public void laymk(View view)
    {
        ten_dang_nhap = edt1.getText().toString();
        email = edt2.getText().toString();
        mknew = edt3.getText().toString();
        xn_mknew = edt4.getText().toString();
        if(mknew.equals(xn_mknew)) {
            GetAPINguoiChoiEP nguoiChoi = (GetAPINguoiChoiEP) new GetAPINguoiChoiEP(this, ten_dang_nhap, email, mknew).execute(duongdan);
        }else
        {
            Toast.makeText(getApplicationContext(),"2 mật khẩu không trùng khớp!!!",Toast.LENGTH_SHORT).show();
        }
    }
}
