package com.example.doanltandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Filterable;
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
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class Login_form extends AppCompatActivity {
    int RC_SIGN_IN = 0;
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

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void Login(View view) {
        String tendn = edt.getText().toString();
        String mk = edt2.getText().toString();
        GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this, tendn, mk).execute(đuongdan); }

    public void Dangky(View view) {
        Intent intent = new Intent(Login_form.this, DangKy_form.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(Login_form.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(Login_form.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }


}
