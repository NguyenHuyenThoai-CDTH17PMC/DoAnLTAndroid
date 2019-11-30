package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private  Button panA;
    private  Button panB;
    private  Button panC;
    private  Button panD;
    private  TextView cauhoi;
    private ArrayList<CauHoi>cauHois;
    private  int i=0;


    public CauHoiLayTheoIDLinhVuc() {
        this.cauHois =new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_lay_theo_idlinh_vuc);
        cauhoi_id=findViewById(R.id.cauhoi_id);
        cauhoi=findViewById(R.id.cauhoi);
        panA=findViewById(R.id.panA);
        panB=findViewById(R.id.panB);
        panC=findViewById(R.id.panC);
        panD=findViewById(R.id.panD);
        Intent intent=getIntent();
        String id = intent.getStringExtra("id");
        new LOADJSON_CAU_HOI().execute("http://192.168.1.19:8080/Do_An_PHP/public/api/cau-hoi?linh_vuc="+id);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();



    }

    public void NEXT(View view){
        cauhoi_id = findViewById(R.id.cauhoi_id);
        cauhoi=findViewById(R.id.cauhoi);
        panA = findViewById(R.id.panA);
        panB = findViewById(R.id.panB);
        panC = findViewById(R.id.panC);
        panD = findViewById(R.id.panD);
        i=i+1;
        if(i<cauHois.size())
        {
            cauhoi_id.setText(String.valueOf(cauHois.get(i).getId()));
            cauhoi.setText(cauHois.get(i).getNoi_dung());
            panA.setText(cauHois.get(i).getPhuong_an_a());
            panB.setText(cauHois.get(i).getPhuong_an_b());
            panC.setText(cauHois.get(i).getPhuong_an_c());
            panD.setText(cauHois.get(i).getPhuong_an_d());
        }
        else {
            Toast.makeText(this, "Hết câu hỏi rồi", Toast.LENGTH_SHORT).show();
        }

    }
    public class LOADJSON_CAU_HOI extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                URLConnection urlConnection=url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object_data=jsonArray.getJSONObject(i);
                    int id=object_data.getInt("id");
                    String noi_dung=object_data.getString("noi_dung");
                    int linh_vuc_id=object_data.getInt("linh_vuc_id");
                    String phuong_an_a=object_data.getString("phuong_an_a");
                    String phuong_an_b=object_data.getString("phuong_an_b");
                    String phuong_an_c=object_data.getString("phuong_an_c");
                    String phuong_an_d=object_data.getString("phuong_an_d");
                    String dap_an=object_data.getString("dap_an");
                    cauHois.add(new CauHoi(id,noi_dung,linh_vuc_id,phuong_an_a,phuong_an_b,phuong_an_c,phuong_an_d,dap_an));

                }

                        cauhoi_id.setText(String.valueOf(cauHois.get(0).getId()));
                        cauhoi.setText(cauHois.get(0).getNoi_dung());
                        panA.setText(cauHois.get(0).getPhuong_an_a());
                        panB.setText(cauHois.get(0).getPhuong_an_b());
                        panC.setText(cauHois.get(0).getPhuong_an_c());
                        panD.setText(cauHois.get(0).getPhuong_an_d());





            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }





}
