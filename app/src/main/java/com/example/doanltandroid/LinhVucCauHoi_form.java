package com.example.doanltandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;

public class LinhVucCauHoi_form extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton btnplay;
    AnimationDrawable background;
    private SoundManager mSoundManager;
    private RecyclerView recyclerView;
    private LinhVucAdapter linhVucAdapter;
    private ArrayList<LinhVuc>linhVucs;
    private String id_nguoichoi;
    private String duongdan="http://192.168.56.1:8080/Do_An_PHP/public/api/linh-vuc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi_form);
        recyclerView=findViewById(R.id.recyclerviewdslinhvuc);
        GetAPILinhVuc getAPILinhVuc= (GetAPILinhVuc) new GetAPILinhVuc(LinhVucCauHoi_form.this,recyclerView).execute(duongdan);
        btnplay = (ImageButton) findViewById(R.id.btnplay);
        //animation translate
        Button btn = (Button) findViewById(R.id.button);
        //final Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        //btn.startAnimation(animation);
        //animation background

        btn.setBackgroundResource(R.drawable.anim_background_red);
        background =(AnimationDrawable) btn.getBackground();
        background.start();
        //animation rorate
        ImageView imageView = findViewById(R.id.imglogo);
        Animation animImage = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        imageView.startAnimation(animImage);

        btnplay = (ImageButton) findViewById(R.id.btnplay);
        GetApiCauHinhApp getApiCauHinhApp = (GetApiCauHinhApp) new GetApiCauHinhApp(this).execute("http://192.168.56.1:8080/Do_An_PHP/public/api/cau-hinh-app");
    }
    public void play(View view){
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
                mediaPlayer.start();
                view.setBackgroundResource(R.drawable.play);
            }else {
               stopplayer();
                view.setBackgroundResource(R.drawable.mute);
            }

    }
    public void stopplayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopplayer();
    }
}
