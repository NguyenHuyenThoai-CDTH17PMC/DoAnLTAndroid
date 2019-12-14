package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    final String jokes[] = {"A","B","C","D"};

    private ProgressBar progressBar;
    private TextView txtTongThoiGian;
    CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;

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
    public void trogiupkhangia(View view)
    {
        dialog_khangia();
    }

    //trợ giúp từ người thân
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
    //trợ giúp khán giả
    public void dialog_khangia(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_khangia);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        BarChart barChart = dialog.findViewById(R.id.barchart);
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int c = random.nextInt(100);
        int d = random.nextInt(100);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(a, 0));
        entries.add(new BarEntry(b, 1));
        entries.add(new BarEntry(c, 2));
        entries.add(new BarEntry(d, 3));
        BarDataSet bardataset = new BarDataSet(entries, "Cells");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        BarData data = new BarData(labels,bardataset);
        barChart.setData(data);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(7000);
        Button btnendkhangia = dialog.findViewById(R.id.btnendkhangia);
        btnendkhangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    //trợ giúp 50/50
    public void TroGiup5050(View view){
        int chondung=Integer.parseInt(cauHois.get(vitri-1).getDap_an());
        Random random =new Random();
        int so_dap_an_muon_an=2;
        do {
            int vitridapAnAn=random.nextInt(4);
            if(vitridapAnAn!= chondung){
                if(vitridapAnAn==0 && vitridapAnAn!= chondung && Button4.getVisibility()==View.VISIBLE ){
                    vitridapAnAn=4;
                    Button4.setVisibility(View.INVISIBLE );
                    so_dap_an_muon_an--;

                }
                if(vitridapAnAn==1 && vitridapAnAn!= chondung && Button1.getVisibility()==View.VISIBLE){
                    Button1.setVisibility(View.INVISIBLE );
                    so_dap_an_muon_an--;
                }
                if(vitridapAnAn==2 && vitridapAnAn!= chondung && Button2.getVisibility()==View.VISIBLE){
                    Button2.setVisibility(View.INVISIBLE );
                    so_dap_an_muon_an--;
                }
                if(vitridapAnAn==3 && vitridapAnAn!= chondung && Button3.getVisibility()==View.VISIBLE){
                    Button3.setVisibility(View.INVISIBLE );
                    so_dap_an_muon_an--;
                }
            }
        }while (so_dap_an_muon_an>0);
    }
    //xử lý câu trả lời
    public  void Tieptuc(View view){
        Button1.setVisibility(View.VISIBLE);
        Button2.setVisibility(View.VISIBLE);
        Button3.setVisibility(View.VISIBLE);
        Button4.setVisibility(View.VISIBLE);
        if(ChonDung(vitri-1,view)==true){
            txtscore.setText(String.valueOf(socaudung));
        }
        LuuChiTietLuotChoi(cauHois.get(vitri-1).getId(),chon);
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
                chon="1";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnB:
                chon="2";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnC:
                chon="3";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    return true;
                }
                break;
            case R.id.btnD:
                chon="4";
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
                Luuluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,ManHinhChinh_form.class);
                startActivity(intent);
            }
        });
    }
    public  void Luuluotchoi(){
        sharedPreferences=getSharedPreferences("nguoichoi",MODE_PRIVATE);
        String nguoichoi_id= sharedPreferences.getString("id_nguoichoi","");
        String thoigianhientai;
        SimpleDateFormat laythoigianhientai=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        thoigianhientai=laythoigianhientai.format(new Date());
        String duongdanluotchoi="http://192.168.56.1/Do_An_PHP/public/api/luot-choi/them-luot-choi";
        PostAPILuotChoi postAPILuotChoi= (PostAPILuotChoi) new PostAPILuotChoi(this,duongdanluotchoi,nguoichoi_id,String.valueOf(socaudung),String.valueOf(socaudung),thoigianhientai).execute();

    }
    public void LuuChiTietLuotChoi(String cauhoi_id,String phuong_an){
        String duongdanchitietluotchoi="http://192.168.56.1/Do_An_PHP/public/api/chi-tiet-luot-choi/them-chi-tiet-luot-choi";
        String diem_cau_nay="0";
        String luot_choi_id="12";
        PostAPIChiTietLuotChoi postAPIChiTietLuotChoi= (PostAPIChiTietLuotChoi) new PostAPIChiTietLuotChoi(CauHoiLayTheoIDLinhVuc.this, duongdanchitietluotchoi,luot_choi_id,cauhoi_id,phuong_an,diem_cau_nay).execute();
    }

}
