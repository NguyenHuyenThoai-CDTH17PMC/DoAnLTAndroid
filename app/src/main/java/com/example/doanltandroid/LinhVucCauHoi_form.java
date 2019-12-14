package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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

public class LinhVucCauHoi_form extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton btnplay;
    private RecyclerView recyclerView;
    private LinhVucAdapter linhVucAdapter;
    private ArrayList<LinhVuc>linhVucs;
    private String đuongdan="http://10.0.2.2:8080/Do_An_PHP/public/api/linh-vuc";
    private String id_nguoichoi;
    private String duongdan = "http://192.168.56.1/Do_An_PHP/public/api/linh-vuc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi_form);
        recyclerView=findViewById(R.id.recyclerviewdslinhvuc);
        GetAPILinhVuc getAPILinhVuc= (GetAPILinhVuc) new GetAPILinhVuc(LinhVucCauHoi_form.this,recyclerView).execute(duongdan);
        btnplay = (ImageButton) findViewById(R.id.btnplay);

    }
    public void play(View view){
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song);
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.play);
                Toast.makeText(this,"Đã bật âm thanh",Toast.LENGTH_SHORT).show();

            }else {
               stopplayer();
               btnplay.setImageResource(R.drawable.mute);
            }

    }
    public void stopplayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
            Toast.makeText(this,"Đã tắt âm thanh",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopplayer();
    }
}
