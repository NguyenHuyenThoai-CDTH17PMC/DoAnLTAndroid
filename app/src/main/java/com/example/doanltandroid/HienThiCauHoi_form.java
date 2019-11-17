package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class HienThiCauHoi_form extends AppCompatActivity {

    QuestionManager questionManager;

    TextView txvCauHoi;
    TextView txvPanA;
    TextView txvPanB;
    TextView txvPanC;
    TextView txvPanD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi_form);
        Log.v("Log", GetContentDataQuestion());

        try {
            questionManager = QuestionManager.CreateByJSON(GetContentDataQuestion());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txvCauHoi = findViewById(R.id.cauhoi);
        txvPanA = findViewById(R.id.panA);
        txvPanB = findViewById(R.id.panB);
        txvPanC = findViewById(R.id.panC);
        txvPanD = findViewById(R.id.panD);

        OnNext(null);
    }
    public void OnNext(View view){
        if(questionManager.hasNext()){
            resetUI(questionManager.next());
        }
    }

    public void resetUI(CauHoi cauHoi){
        txvCauHoi.setText(cauHoi.getCauhoi());
        txvPanA.setText(cauHoi.getPanA());
        txvPanB.setText(cauHoi.getPanB());
        txvPanC.setText(cauHoi.getPanC());
        txvPanD.setText(cauHoi.getPanD());
    }


    public String GetContentDataQuestion(){
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}
