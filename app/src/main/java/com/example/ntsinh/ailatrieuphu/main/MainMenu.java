

package com.example.ntsinh.ailatrieuphu.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ntsinh.ailatrieuphu.R;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{
    Button Start,Help,Exit;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        playSound(R.raw.bg_music);
        event();
    }

    private void event() {
        Start = (Button) findViewById(R.id.btn_batDau);
        Help = (Button) findViewById(R.id.btn_troGiup);
        Exit = (Button) findViewById(R.id.btn_thoat);
        Start.setOnClickListener(this);
        Help.setOnClickListener(this);
        Exit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_batDau:
                mediaPlayer.stop();
                playSound(R.raw.batdauchoi);
                Intent intent = new Intent(getApplicationContext(),MainStart.class);
                startActivity(intent);
                break;
            case R.id.btn_troGiup:
                mediaPlayer.stop();
                Intent intent1 = new Intent(getApplicationContext(),MainHelp.class);
                startActivity(intent1);
                break;
            case R.id.btn_thoat:
                System.exit(0);
                break;
        }
    }
    public void playSound(int type){
        mediaPlayer = MediaPlayer.create(this, type);
        mediaPlayer.start();
    }
}
