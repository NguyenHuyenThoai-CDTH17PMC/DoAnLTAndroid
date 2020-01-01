package com.example.doanltandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Credit_form extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String duongdan = "http://10.0.2.2:8080/Do_An_PHP/public/api/credit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_form);
        recyclerView=findViewById(R.id.recyclerviewCredit);
        GetAPICredit getAPICredit= (GetAPICredit) new GetAPICredit(Credit_form.this,recyclerView).execute(duongdan);
    }
}
