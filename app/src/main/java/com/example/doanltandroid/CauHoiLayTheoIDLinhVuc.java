package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CauHoiLayTheoIDLinhVuc extends AppCompatActivity  {
    private  TextView cauhoi_id;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private  TextView cauhoi;
    private ArrayList<CauHoi>cauHois;
    private int vitri=0;
    private int socaudung=0;


    public CauHoiLayTheoIDLinhVuc() {
        this.cauHois =new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_lay_theo_idlinh_vuc);
        cauhoi_id=findViewById(R.id.cauhoi_id);
        cauhoi=findViewById(R.id.cauhoi);
        radioButton1=findViewById(R.id.radA);
        radioButton2=findViewById(R.id.radB);
        radioButton3=findViewById(R.id.radC);
        radioButton4=findViewById(R.id.radD
        );
        Intent intent=getIntent();
        String JSON = intent.getStringExtra("JSON");
        if(kiemtraJSON(JSON)==true){
           cauhoi_id.setText(cauHois.get( vitri).getId());
           cauhoi.setText(cauHois.get( vitri).getNoi_dung());
           radioButton1.setText(cauHois.get( vitri).getPhuong_an_a());
           radioButton2.setText(cauHois.get( vitri).getPhuong_an_b());
           radioButton3.setText(cauHois.get( vitri).getPhuong_an_c());
           radioButton4.setText(cauHois.get( vitri).getPhuong_an_d());
           vitri++;
        }

    }
    public  void Tieptuc(View view){
        if(view.getId()==R.id.btnNEXT){
            if(ChonDung(vitri-1)==true){
                Toast.makeText(this, ""+socaudung, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Bạn chọn sai", Toast.LENGTH_SHORT).show();
            }
            try {
                cauhoi_id.setText(cauHois.get( vitri).getId());
                cauhoi.setText(cauHois.get( vitri).getNoi_dung());
                radioButton1.setText(cauHois.get( vitri).getPhuong_an_a());
                radioButton2.setText(cauHois.get( vitri).getPhuong_an_b());
                radioButton3.setText(cauHois.get( vitri).getPhuong_an_c());
                radioButton4.setText(cauHois.get( vitri).getPhuong_an_d());
                vitri++;

            }catch (Exception e){
                Toast.makeText(this, "Bạn chọn sai", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public boolean ChonDung(int vitri){
       String chon="";
        if(radioButton1.isChecked()){
            chon="A";
            if(chon.equals(cauHois.get(vitri).getDap_an())){
                socaudung++;
                return true;
            }

        }
        if(radioButton2.isChecked()){
            chon="B";
            if(chon.equals(cauHois.get(vitri).getDap_an())){
                socaudung++;
                return true;
            }

        }
        if(radioButton3.isChecked()){
            chon="C";
            if(chon.equals(cauHois.get(vitri).getDap_an())){
                socaudung++;
                return true;
            }

        }
        if(radioButton4.isChecked()){
            chon="D";
            if(chon.equals(cauHois.get(vitri).getDap_an())){
                socaudung++;
                return true;
            }

        }
        return true;
    }
    public boolean kiemtraJSON(String JSON){
        try {
            JSONObject jsonObjectLInhVuc=new JSONObject(JSON);
            JSONArray jsonArraydata=jsonObjectLInhVuc.getJSONArray("data");
            for(int i=0;i<jsonArraydata.length();i++){
                JSONObject object=jsonArraydata.getJSONObject(i);
               String id= String.valueOf(object.getInt("id"));
               String noi_dung= object.getString("noi_dung");
               String linh_vuc_id=String.valueOf(object.getInt("linh_vuc_id"));
               String phuong_an_a= object.getString("phuong_an_a");
               String phuong_an_b= object.getString("phuong_an_b");
               String phuong_an_c=object.getString("phuong_an_c");
               String phuong_an_d= object.getString("phuong_an_d");
               String dap_an= object.getString("dap_an");
               cauHois.add(new CauHoi(id,noi_dung,linh_vuc_id,phuong_an_a,phuong_an_b,phuong_an_c,phuong_an_d,dap_an));


            }

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }







}
