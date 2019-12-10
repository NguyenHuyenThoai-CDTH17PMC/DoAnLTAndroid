package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CauHoiLayTheoIDLinhVuc extends AppCompatActivity  {
    private  TextView cauhoi_id;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private  TextView cauhoi;
    private ArrayList<CauHoi>cauHois;
    private int vitri=0;
    private int socaudung=0;
    private String chon;
    private  TextView txtscore;
<<<<<<< HEAD
    final String jokes[] = {"A","B","C","D"};
=======

    private ProgressBar progressBar;
    private TextView txtTongThoiGian;
    CountDownTimer countDownTimer;
>>>>>>> 322431a1e85e1b2f0e769a186e60350a41d9225b

    public CauHoiLayTheoIDLinhVuc() {
        this.cauHois =new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_lay_theo_idlinh_vuc);
        txtscore=findViewById(R.id.txtScore);
        cauhoi_id=findViewById(R.id.txtid);
        cauhoi=findViewById(R.id.txtCauHoi);
        Button1=findViewById(R.id.btnA);
        Button2=findViewById(R.id.btnB);
        Button3=findViewById(R.id.btnC);
        Button4=findViewById(R.id.btnD);
        Intent intent=getIntent();
        String JSON = intent.getStringExtra("JSON");
        progressBar=findViewById(R.id.progressBar);
        final CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setMax(60);
                progressBar.setProgress((int) (millisUntilFinished / 1000));
                txtTongThoiGian=findViewById(R.id.txttongthoigian);
                txtTongThoiGian.setText(String.valueOf(millisUntilFinished / 1000));
            }
            @Override
            public void onFinish() {

                Toast.makeText(CauHoiLayTheoIDLinhVuc.this, "Over time !", Toast.LENGTH_SHORT).show();
                dialogketthuc();
            }
        }.start();
        if(kiemtraJSON(JSON)==true){
           cauhoi_id.setText(cauHois.get( vitri).getId());
           cauhoi.setText(cauHois.get( vitri).getNoi_dung());
           Button1.setText("A: "+cauHois.get( vitri).getPhuong_an_a());
           Button2.setText("B: "+cauHois.get( vitri).getPhuong_an_b());
           Button3.setText("C: "+cauHois.get( vitri).getPhuong_an_c());
           Button4.setText("D: "+cauHois.get( vitri).getPhuong_an_d());
           vitri++;
        }
        txtscore.setText(String.valueOf(socaudung));
    }
    public void trogiup(View view){
        dialog_goinguoithan();
    }
    public void dialog_goinguoithan(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.diglog_goinguoithan);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Random random =new Random();
        int num = random.nextInt(4);
        TextView txt = dialog.findViewById(R.id.txtrandomdapan);
        txt.setText(jokes[num]);
        Button btnend = dialog.findViewById(R.id.btnend);
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    public  void Tieptuc(View view){
        if(ChonDung(vitri-1,view)==true){
            Toast.makeText(this, ""+chon+""+socaudung, Toast.LENGTH_SHORT).show();
            txtscore.setText(String.valueOf(socaudung));
        }
        try {
                cauhoi_id.setText(cauHois.get(vitri).getId());
                cauhoi.setText(cauHois.get( vitri).getNoi_dung());
                Button1.setText("A: "+cauHois.get( vitri).getPhuong_an_a());
                Button2.setText("B: "+cauHois.get( vitri).getPhuong_an_b());
                Button3.setText("C: "+cauHois.get( vitri).getPhuong_an_c());
                Button4.setText("D: "+cauHois.get( vitri).getPhuong_an_d());
                vitri++;
            }catch (Exception e){
               dialogketthuc();
            }
    }

    public boolean ChonDung(int vitri,View view){
        switch (view.getId()){
            case R.id.btnA:
                chon="A";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnB:
                chon="B";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnC:
                chon="C";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnD:
                chon="D";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
        }

        return false;
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
    public void dialogketthuc(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_ketthucluotchoi);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //ánh xạ
        TextView txtdiem=dialog.findViewById(R.id.txtDiem);
        Button btnthemluotmoi=dialog.findViewById(R.id.btnThemLuotMoi);
        Button btnketthuc=dialog.findViewById(R.id.btnKetThucLuot);
        //gán dữ liệu

        txtdiem.setText(String.valueOf(socaudung));

        btnketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luuluotchoi_chitietluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,ManHinhChinh_form.class);
                startActivity(intent);
            }
        });



    }
    public  void Luuluotchoi_chitietluotchoi(){
        String thoigianhientai;
        SimpleDateFormat laythoigianhientai=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        thoigianhientai=laythoigianhientai.format(new Date());
<<<<<<< HEAD
        String duongdan="http://172.19.200.255:8080/Do_An_PHP/public/api/luot-choi/them-luot-choi";
=======
        String duongdan="http://192.168.56.1/Do_An_PHP/public/api/luot-choi/them-luot-choi";
>>>>>>> 322431a1e85e1b2f0e769a186e60350a41d9225b
        PostAPILuotChoi postAPILuotChoi= (PostAPILuotChoi) new PostAPILuotChoi(this,duongdan,"1",String.valueOf(socaudung),String.valueOf(socaudung),thoigianhientai).execute();
    }







}
