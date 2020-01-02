package com.example.doanltandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageView;
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

import com.google.android.gms.common.SignInButton;

public class Login_form extends AppCompatActivity {
    int RC_SIGN_IN = 0;
    EditText edt;
    EditText edt2;
    AnimationDrawable background;
    String đuongdan="http://192.168.56.1/Do_An_PHP/public/api/nguoi-choi";
   // String đuongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        edt = findViewById(R.id.edtTendangnhap);
        edt2 = findViewById(R.id.edtMatkhau);
        ImageView imageView = findViewById(R.id.imglogo);
        Animation animImage = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        imageView.startAnimation(animImage);

    }
    public void Login(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        String tendn = edt.getText().toString();
        String mk = edt2.getText().toString();
        GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this, tendn, mk).execute(đuongdan); }

    public void Dangky(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        Intent intent = new Intent(Login_form.this, DangKy_form.class);
        startActivity(intent);
    }

}
