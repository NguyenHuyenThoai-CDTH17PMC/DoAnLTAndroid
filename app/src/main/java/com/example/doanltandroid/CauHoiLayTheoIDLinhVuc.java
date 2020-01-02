package com.example.doanltandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class CauHoiLayTheoIDLinhVuc extends AppCompatActivity  {
    private String trangthaiclick;
    private  TextView cauhoi_id;
    private  TextView ten_dang_nhap;
    private ImageView hinh_dai_dien_min;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private  TextView cauhoi;
    private ArrayList<CauHoi>cauHois;
    private int vitri=0;
    private  int stt =1;
    private int socaudung=0;
    private String chon;
    private  TextView txtscore;
    final String jokes[] = {"A","B","C","D"};
    private ProgressBar progressBar;
    private TextView txtTongThoiGian;
    CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;
    private int thoigiantieptuc=0;
    ArrayList<LuuChiTietLuotChoi>luuChiTietLuotChois;
    ArrayList<LuotChoi>luotChois;
    String nguoichoi_id;
    int demlansai;
    String duongdannguoichoionclick = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi";
    String duongdancauhinh;
    AnimationDrawable animationDrawable;
    ArrayList<Integer> mRandom;
    Animation animationchondung;
    ImageView img_hinhcheck;
    String co_hoi_sai;
    String thoi_gian_tra_loi;
    public CauHoiLayTheoIDLinhVuc() {
        this.cauHois =new ArrayList<>();
        this.luuChiTietLuotChois=new ArrayList<>();
        mRandom = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_lay_theo_idlinh_vuc);
        GetApiCauHinhApp getApiCauHinhApp = (GetApiCauHinhApp) new GetApiCauHinhApp(this).execute("http://192.168.56.1:8080/Do_An_PHP/public/api/cau-hinh-app");
        //xử lí animation chọn đúng
        animationchondung= AnimationUtils.loadAnimation(this,R.anim.animation_hinhcheck);
        img_hinhcheck=findViewById(R.id.img_daucheck);
        txtscore=findViewById(R.id.txtScore);
        cauhoi_id=findViewById(R.id.txtid);
        cauhoi=findViewById(R.id.txtCauHoi);
        Button1=findViewById(R.id.btnA);
        Button2=findViewById(R.id.btnB);
        Button3=findViewById(R.id.btnC);
        Button4=findViewById(R.id.btnD);
        ten_dang_nhap=findViewById(R.id.name_play);
        hinh_dai_dien_min=findViewById(R.id.ic_play);
        sharedPreferences=getSharedPreferences("nguoichoi",MODE_PRIVATE);
        String ten_dang_nhap_get = sharedPreferences.getString("ten_dang_nhap","");
        String hinh_dai_dien_get = sharedPreferences.getString("hinh_dai_dien","");
        nguoichoi_id= sharedPreferences.getString("id_nguoichoi","");
        ten_dang_nhap.setText(ten_dang_nhap_get);
        String url = "http://192.168.56.1:8080/Do_An_PHP/public/img/"+hinh_dai_dien_get;
        Picasso.with(this).load(url).into(hinh_dai_dien_min);
        Intent intent=getIntent();
        String JSON = intent.getStringExtra("JSON");
        progressBar=findViewById(R.id.progressBar);
        txtTongThoiGian=findViewById(R.id.txttongthoigian);

        sharedPreferences = getSharedPreferences("cauhinhapp",MODE_PRIVATE);
        co_hoi_sai = sharedPreferences.getString("co_hoi_sai","");
        thoi_gian_tra_loi = sharedPreferences.getString("thoi_gian_tra_loi","");
        int thoigian = Integer.parseInt(thoi_gian_tra_loi)*1000;
        demthoigian(thoigian);
        if(kiemtraJSON(JSON)==true){ RandomCauHoi();
           cauhoi_id.setText(""+stt); //
           cauhoi.setText(cauHois.get( mRandom.get(vitri)).getNoi_dung());
           Button1.setText("A: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_a());
           Button2.setText("B: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_b());
           Button3.setText("C: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_c());
           Button4.setText("D: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_d());
           vitri++;
           stt++;
        }
        txtscore.setText(String.valueOf(socaudung));//sao ko lam recycleverview ?
    }
    public void stop(View view){
        thoigiantieptuc=Integer.parseInt(txtTongThoiGian.getText().toString())*1000;
        countDownTimer.cancel();
        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Tạm ngưng");
        dialog.setPositiveButton("tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                demthoigian(thoigiantieptuc);
            }
        });
        dialog.show();
    }
    public void demthoigian(long thoigian){
        countDownTimer=new CountDownTimer(thoigian,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTongThoiGian.setText(String.valueOf(millisUntilFinished / 1000));
            }
            @Override
            public void onFinish() {

                Toast.makeText(CauHoiLayTheoIDLinhVuc.this, "Over time !", Toast.LENGTH_SHORT).show();
                dialogketthuc();
            }
        }.start();
    }
    public void trogiup(View view){
        dialog_goinguoithan();
        ImageView img = (ImageView) findViewById(R.id.btnCall);
        img.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
        img.setEnabled(false);

    }
    //trợ giúp từ người thân
    public void trogiupkhangia(View view)
    {
        switch ( cauHois.get(mRandom.get(vitri-1)).getDap_an()){
            case "1": dialog_khangia("A"); break;
            case "2": dialog_khangia("B"); break;
            case "3": dialog_khangia("C"); break;
            case "4": dialog_khangia("D"); break;
        }
        ImageView img = (ImageView) findViewById(R.id.btnpeople);
        img.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
        img.setEnabled(false);
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
    public void randomm(int[] a,String b){
        switch (b){
            case "A":
                a[0]= new Random().nextInt(15)+10;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "B":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+10;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "C":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+10;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "D":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+10;
                break;
        }
        int sum=0;
        do{
            sum=a[0]+a[1]+a[2]+a[3];
            if(sum==100){break;}
            a[0]++;
            a[1]++;
            a[2]++;
            a[3]++;
            System.out.println("A" + a[0]);
            System.out.println("B" +a[1]);
            System.out.println("C" +a[2]);
            System.out.println("D" +a[3]+"\n");
        }
        while(sum<100);
    }

    public void dialog_khangia(String b){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_khangia);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        BarChart barChart = dialog.findViewById(R.id.barchart);
       int[] a = new int[4]; //cho nao choi?la sao luc nhan nut cho nao nhan' ? class dau
       randomm(a,b);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(a[0], 0));
        entries.add(new BarEntry(a[1], 1));
        entries.add(new BarEntry(a[2], 2));
        entries.add(new BarEntry(a[3], 3));
        BarDataSet bardataset = new BarDataSet(entries, "Cells");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        BarData data = new BarData(labels,bardataset);
        barChart.setData(data);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardataset.setValueTextSize(20);
        barChart.animateY(1000);
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
        ImageView img = (ImageView) findViewById(R.id.btn50_50);
        img.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
        img.setEnabled(false);

    }
    //xử lý câu trả lời
    public  void Tieptuc(View view){
        Button1.setVisibility(View.VISIBLE);
        Button2.setVisibility(View.VISIBLE);
        Button3.setVisibility(View.VISIBLE);
        Button4.setVisibility(View.VISIBLE);
        if(ChonDung(vitri-1,view)==true){
            txtscore.setText(String.valueOf(socaudung*10));
        }
        else
        {
            demlansai++;
        }
        if(demlansai==Integer.parseInt(co_hoi_sai)){
            dialogsaicauhoi();
        }
        luuChiTietLuotChois.add(new LuuChiTietLuotChoi(cauHois.get(vitri-1).getId(),chon,String.valueOf(socaudung)));
        try {
                cauhoi_id.setText(""+stt);
                cauhoi.setText(cauHois.get( mRandom.get(vitri)).getNoi_dung());
                Button1.setText("A: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_a());
                Button2.setText("B: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_b());
                Button3.setText("C: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_c());
                Button4.setText("D: "+cauHois.get( mRandom.get(vitri)).getPhuong_an_d());
                vitri++;
                stt++;

            }catch (Exception e){
               dialogketthuc();
            }
    }
    //Tạm dừng cuộc chơi
    public boolean ChonDung(int vitri,View view){
        switch (view.getId()){
            case R.id.btnA:
                chon="1";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    img_hinhcheck.startAnimation(animationchondung);
                    return true;
                }

                break;
            case R.id.btnB:
                chon="2";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    img_hinhcheck.startAnimation(animationchondung);
                    return true;
                }
                break;
            case R.id.btnC:
                chon="3";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    img_hinhcheck.startAnimation(animationchondung);
                    return true;
                }
                break;
            case R.id.btnD:
                chon="4";
                if(chon.equals(cauHois.get(vitri).getDap_an())) {
                    socaudung++;
                    img_hinhcheck.startAnimation(animationchondung);
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
        btnthemluotmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luuluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,LinhVucCauHoi_form.class);
                startActivity(intent);
            }
        });
        btnketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luuluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,ManHinhChinh_form.class);
                startActivity(intent);
            }
        });
    }
    public void dialogsaicauhoi(){
        thoigiantieptuc=Integer.parseInt(txtTongThoiGian.getText().toString())*1000;
        countDownTimer.cancel();
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_saicauhoi);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //ánh xạ
        TextView txtdiem=dialog.findViewById(R.id.txtDiem);
        Button btnTieptuc = dialog.findViewById(R.id.btntieptuc);
        Button btnthemluotmoi=dialog.findViewById(R.id.btnThemLuotMoisch);
        Button btnketthuc=dialog.findViewById(R.id.btnKetThucLuotsch);
        //gán dữ liệu
        txtdiem.setText(String.valueOf(socaudung*10));
        btnthemluotmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luuluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,LinhVucCauHoi_form.class);
                startActivity(intent);
            }
        });
        btnketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luuluotchoi();
                Intent intent=new Intent(CauHoiLayTheoIDLinhVuc.this,ManHinhChinh_form.class);
                startActivity(intent);
            }
        });
        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAPIUpCreadit getAPIUpCreadit= (GetAPIUpCreadit) new GetAPIUpCreadit(CauHoiLayTheoIDLinhVuc.this,nguoichoi_id).execute(duongdannguoichoionclick);
                sharedPreferences=getSharedPreferences("trangthai1",MODE_PRIVATE);
                trangthaiclick = sharedPreferences.getString("trangthai","");
                if(trangthaiclick.equals("ok"))
                {
                    dialog.cancel();
                    demthoigian(thoigiantieptuc);
                }
                if(trangthaiclick.equals("no"))
                {
                    Toast.makeText(CauHoiLayTheoIDLinhVuc.this,"Bạn không đủ credit , vui lòng mua thêm",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Luuluotchoi(){
        String thoigianhientai;
        SimpleDateFormat laythoigianhientai=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        thoigianhientai=laythoigianhientai.format(new Date());
        String duongdanluotchoi="http://192.168.56.1:8080/Do_An_PHP/public/api/luot-choi/them-luot-choi";
        String duongdan = "http://192.168.56.1:8080/Do_An_PHP/public/api/nguoi-choi";
        PostAPILuotChoi postAPILuotChoi= (PostAPILuotChoi) new PostAPILuotChoi(this,duongdanluotchoi,nguoichoi_id,String.valueOf(socaudung),String.valueOf(socaudung),thoigianhientai).execute();
        GetAPIUpDiem getAPIUpDiem = (GetAPIUpDiem) new GetAPIUpDiem(this,String.valueOf(socaudung*10),nguoichoi_id).execute(duongdan);
        //Lấy danh sách lượt chơi của người này -- sau đó duyệt lấy lượt chơi cuối cùng là lượt chơi vừa chơi xong post từng cái chi tiết lên
        //Lấy danh sách lượt chơi của người này -- sau đó duyệt lấy lượt chơi cuối cùng là lượt chơi vừa chơi xong post từng cái chi tiết lên
        GetAPILuotChoiTheoNguoiChoi layid= (GetAPILuotChoiTheoNguoiChoi) new GetAPILuotChoiTheoNguoiChoi(CauHoiLayTheoIDLinhVuc.this,luotChois).execute("http://192.168.56.1:8080/Do_An_PHP/public/api/luot-choi/lay-luot-choi?nguoichoi_id="+nguoichoi_id);
   }
    public void RandomCauHoi(){
        for(int i=0;i<cauHois.size();i++){
            mRandom.add(i);
        }
        Collections.shuffle(mRandom);
    }
    public class GetAPILuotChoiTheoNguoiChoi extends AsyncTask<String,String,String> {
        ArrayList<LuotChoi> list_luotchoi;
        LuotChoi luotChoi;
        Context context;
        public GetAPILuotChoiTheoNguoiChoi(Context context,ArrayList<LuotChoi>list_luotchoi) {
            this.context=context;
            this.list_luotchoi=list_luotchoi;
        }
        @Override
        protected String doInBackground(String... strings) {
            return ReadAPI.GetJSON(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonnguoichoi = new JSONObject(s);
                JSONArray jsonarraydata = jsonnguoichoi.getJSONArray("data");
                list_luotchoi=new ArrayList<>();
                for (int i = 0; i < jsonarraydata.length(); i++) {
                    JSONObject jsonObject = jsonarraydata.getJSONObject(i);
                    String id = String.valueOf(jsonObject.getInt("id"));
                    String nguoi_choi_id = String.valueOf(jsonObject.getInt("nguoi_choi_id"));
                    String so_cau = String.valueOf(jsonObject.getInt("so_cau"));
                    String diem = String.valueOf(jsonObject.getInt("diem"));
                    String ngay_gio = jsonObject.getString("ngay_gio");
                    luotChoi=new LuotChoi();
                    luotChoi.setId(id);
                    luotChoi.setNguoi_choi_id(nguoi_choi_id);
                    luotChoi.setSo_cau(so_cau);
                    luotChoi.setDiem(diem);
                    luotChoi.setNgay_gio(ngay_gio);
                    list_luotchoi.add(luotChoi);
                }
                String duongdanthemchitiet="http://192.168.56.1:8080/Do_An_PHP/public/api/chi-tiet-luot-choi/them-chi-tiet-luot-choi";
                String luot_choi_id=list_luotchoi.get(list_luotchoi.size()-1).getId();
                for (int i=0;i<luuChiTietLuotChois.size();i++){
                    PostAPIChiTietLuotChoi postAPIChiTietLuotChoi= (PostAPIChiTietLuotChoi) new PostAPIChiTietLuotChoi(context,duongdanthemchitiet,luot_choi_id,luuChiTietLuotChois.get(i).getCauhoi_id(),luuChiTietLuotChois.get(i).getPhuong_an(),luuChiTietLuotChois.get(i).getDiem()).execute();
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
