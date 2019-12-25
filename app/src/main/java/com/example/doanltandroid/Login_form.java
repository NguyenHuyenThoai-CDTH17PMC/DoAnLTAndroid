package com.example.doanltandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login_form extends AppCompatActivity {
    EditText edt;
    EditText edt2;
    TextView txt;

    String duongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        edt = findViewById(R.id.edtTendangnhapqm);
        edt2 = findViewById(R.id.edtMatkhau);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
    }
    public void Login(View view) throws NoSuchAlgorithmException {
        String tendn = edt.getText().toString();
        String mkk = edt2.getText().toString();
        GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this, tendn, mkk).execute(duongdan);
        String mk = StringMD5.convertHashToString(edt2.getText().toString());
    };

    public void Dangky(View view) {
        Intent intent = new Intent(Login_form.this, DangKy_form.class);
        startActivity(intent);
    }
    public void quenmatkhau(View view) {
        Intent intent = new Intent(Login_form.this, QuenMatKhau_form.class);
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
