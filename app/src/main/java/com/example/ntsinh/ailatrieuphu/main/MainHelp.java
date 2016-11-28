package com.example.ntsinh.ailatrieuphu.main;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ntsinh.ailatrieuphu.R;

/**
 * Created by ntsinh on 11/18/2016.
 */

public class MainHelp extends AppCompatActivity implements View.OnClickListener{
    private Button btnReady;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_help);
        mediaPlayer= MediaPlayer.create(this,R.raw.luatchoi);
        mediaPlayer.start();
        initView();
    }
    private void initView() {
        btnReady= (Button) findViewById(R.id.btnSanSang);
        btnReady.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mediaPlayer.stop();
        onBackPressed();
        playSound(R.raw.bg_music);
    }
    public void playSound(int type){
        mediaPlayer = MediaPlayer.create(this, type);
        mediaPlayer.start();
    }
}
