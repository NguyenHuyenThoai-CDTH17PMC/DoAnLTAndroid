package com.example.doanltandroid;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {
    private Context mContext;
    private MediaPlayer mmediaPlayer;
    private MediaPlayer mediaPlayer;
    public SoundManager(Context context){
        mContext=context;
    }
    public void playSoundBG(int idSound){
        if(mmediaPlayer!=null){
            mmediaPlayer.release();
        }
        mmediaPlayer=MediaPlayer.create(mContext,idSound);
        mmediaPlayer.setLooping(true);
        mmediaPlayer.start();
    }
    public void stopBG(){
        mmediaPlayer.release();

    }
    public int playSound (int idSound){
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer=MediaPlayer.create(mContext,idSound);
        mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener)mContext);
        mediaPlayer.start();
        return idSound;
    }
}
