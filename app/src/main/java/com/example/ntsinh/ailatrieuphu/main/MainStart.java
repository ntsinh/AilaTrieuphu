package com.example.ntsinh.ailatrieuphu.main;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ntsinh.ailatrieuphu.R;
import com.example.ntsinh.ailatrieuphu.dataBase.DataBase;
import com.example.ntsinh.ailatrieuphu.model.Question;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ntsinh on 11/18/2016.
 */

public class MainStart extends AppCompatActivity implements View.OnClickListener{
    private TextView tvQuestion,tvTimedown,tvSoCau,tvCoin;
    private Button btnCaseA,btnCaseB,btnCaseC,btnCaseD;
    private DataBase myDataBase;
    private int trueCase,i,dem,wait,coin,timeRunHelp,idHelp;
    private boolean check,run,checkPickAnswer;
    private Dialog dialog;
    private ArrayList<Question> questions=new ArrayList<>();
    private ImageView imim50,imimpeople,imHelp_call,imHelp_stop,imClock,imvNext;
    private MediaPlayer mediaPlayer;
    private Animation animationButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_start);
        myDataBase=new DataBase(this);
        try {
            questions = myDataBase.getData();
            initView();
            asyncTask.execute();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Khong ket noi dc csdl",Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        i=0;
        dem=30;
        wait=30;
        coin=0;
        timeRunHelp=30;
        imClock= (ImageView) findViewById(R.id.imClock);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.time_round);
        imClock.startAnimation(animation);
        tvQuestion= (TextView) findViewById(R.id.tvCauHoi);
        tvTimedown= (TextView) findViewById(R.id.tvClock);
        tvCoin= (TextView) findViewById(R.id.tvCoin);
        tvSoCau= (TextView) findViewById(R.id.tvSocau);
        btnCaseA= (Button) findViewById(R.id.btnA);
        btnCaseB= (Button) findViewById(R.id.btnB);
        btnCaseC= (Button) findViewById(R.id.btnC);
        btnCaseD= (Button) findViewById(R.id.btnD);
        imim50= (ImageView) findViewById(R.id.im50);
        imimpeople= (ImageView) findViewById(R.id.impeople);
        imHelp_call= (ImageView) findViewById(R.id.imCall);
        imvNext=(ImageView) findViewById(R.id.imNext);
        imHelp_stop= (ImageView) findViewById(R.id.imStop);
        setText(i);
        btnCaseA.setOnClickListener(this);
        btnCaseB.setOnClickListener(this);
        btnCaseC.setOnClickListener(this);
        btnCaseD.setOnClickListener(this);
        imim50.setOnClickListener(this);
        imimpeople.setOnClickListener(this);
        imHelp_call.setOnClickListener(this);
        imvNext.setOnClickListener(this);
        imHelp_stop.setOnClickListener(this);
    }
    public static final int[] SOUND_QUESTIONS={
            R.raw.ques01,
            R.raw.ques02,
            R.raw.ques03,
            R.raw.ques04,
            R.raw.ques05,
            R.raw.ques06,
            R.raw.ques07,
            R.raw.ques08,
            R.raw.ques09,
            R.raw.ques10,
            R.raw.ques11,
            R.raw.ques12,
            R.raw.ques13,
            R.raw.ques14,
            R.raw.ques15,
    };
    private void setText(int i) {
        run=true;
        int tm=i+1;
        tvCoin.setText(coin+"");
        tvSoCau.setText("Câu hỏi số "+tm);
        playSound(SOUND_QUESTIONS[i]);
        playSound(R.raw.moc1);
        checkPickAnswer=true;
        btnCaseA.setBackgroundResource(R.drawable.answer_style);
        btnCaseB.setBackgroundResource(R.drawable.answer_style);
        btnCaseC.setBackgroundResource(R.drawable.answer_style);
        btnCaseD.setBackgroundResource(R.drawable.answer_style);
        tvQuestion.setText(questions.get(i).getQuestion());
        btnCaseA.setText("A. "+questions.get(i).getCaseA());
        btnCaseB.setText("B. "+questions.get(i).getCaseB());
        btnCaseC.setText("C. "+questions.get(i).getCaseC());
        btnCaseD.setText("D. "+questions.get(i).getCaseD());
        btnCaseA.setEnabled(true);
        btnCaseB.setEnabled(true);
        btnCaseC.setEnabled(true);
        btnCaseD.setEnabled(true);
        dem=30;
        switch (questions.get(i).getTrueCase()){
            case 1:
                trueCase=R.id.btnA;
                break;
            case 2:
                trueCase=R.id.btnB;
                break;
            case 3:
                trueCase=R.id.btnC;
                break;
            case 4:
                trueCase=R.id.btnD;
                break;
        }
    }
    private void changeQuestion(int i) {
        run=true;
        tvCoin.setText(coin+"");
        tvSoCau.setText("Câu hỏi số "+i);
        checkPickAnswer=false;
        btnCaseA.setBackgroundResource(R.drawable.answer_style);
        btnCaseB.setBackgroundResource(R.drawable.answer_style);
        btnCaseC.setBackgroundResource(R.drawable.answer_style);
        btnCaseD.setBackgroundResource(R.drawable.answer_style);
        tvQuestion.setText(questions.get(i).getQuestion());
        btnCaseA.setText("A. "+questions.get(i).getCaseA());
        btnCaseB.setText("B. "+questions.get(i).getCaseB());
        btnCaseC.setText("C. "+questions.get(i).getCaseC());
        btnCaseD.setText("D. "+questions.get(i).getCaseD());
        btnCaseA.setEnabled(true);
        btnCaseB.setEnabled(true);
        btnCaseC.setEnabled(true);
        btnCaseD.setEnabled(true);
        dem=30;
        switch (questions.get(i).getTrueCase()){
            case 1:
                trueCase=R.id.btnA;
                break;
            case 2:
                trueCase=R.id.btnB;
                break;
            case 3:
                trueCase=R.id.btnC;
                break;
            case 4:
                trueCase=R.id.btnD;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        run=false;
        if (!checkPickAnswer){
            return;
        }
        if (v.getId()==R.id.btnA ||
                v.getId()==R.id.btnB||
                v.getId()==R.id.btnC||
                v.getId()==R.id.btnD){
            v.setBackgroundResource(R.drawable.answer_choose);
            switch (v.getId()){
                case R.id.btnA:
                    playSound(R.raw.ans_a);
                    break;
                case R.id.btnB:
                    playSound(R.raw.ans_b);
                    break;
                case R.id.btnC:
                    playSound(R.raw.ans_c);
                    break;
                case R.id.btnD:
                    playSound(R.raw.ans_d);
                    break;
            }
            if (v.getId()==trueCase){
                check=true;
                wait=0;
                coin= coin+200*(i+1);
            }else {
                check=false;
                wait=0;
            }
            checkPickAnswer=false;
        }
        else if (v.getId()==R.id.im50){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.im50;
            playSound(R.raw.sound5050);
            imim50.setEnabled(false);
            imim50.setImageResource(R.drawable.end_help50_50);
        }else if (v.getId()==R.id.impeople){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.impeople;
            imimpeople.setEnabled(false);
            playSound(R.raw.khan_gia);
            imimpeople.setImageResource(R.drawable.end_people_help);
        }else if (v.getId()==R.id.imCall){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.imCall;
            imHelp_call.setEnabled(false);
            playSound(R.raw.call);
            imHelp_call.setImageResource(R.drawable.end_call);
        }else if(v.getId()==R.id.imNext) {
            wait=0;
            changeQuestion(++i);
        }else if (v.getId()==R.id.imStop){
            checkPickAnswer=false;
            initDiaLogFinish(coin);
        }
    }
    private AsyncTask<Void,Integer,Void> asyncTask=new AsyncTask<Void, Integer, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            for (dem=30;dem>=0;dem--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(dem);
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (run) {
                tvTimedown.setText(values[0] + "");
            }
            else{
                dem++;
            }
            wait++;
            timeRunHelp++;
            showHelp(timeRunHelp,idHelp);
            //
            if (wait==4 && check){
                switch (trueCase){
                    case R.id.btnA:
                        btnCaseA.setBackgroundResource(R.drawable.answer_true);
                        playSound(R.raw.true_a);
                        animnButon(btnCaseA);
                        break;
                    case R.id.btnB:
                        btnCaseB.setBackgroundResource(R.drawable.answer_true);
                        playSound(R.raw.true_b);
                        animnButon(btnCaseB);
                        break;
                    case R.id.btnC:
                        btnCaseC.setBackgroundResource(R.drawable.answer_true);
                        playSound(R.raw.true_c);
                        animnButon(btnCaseC);
                        break;
                    case R.id.btnD:
                        btnCaseD.setBackgroundResource(R.drawable.answer_true);
                        playSound(R.raw.true_d);
                        animnButon(btnCaseD);
                        break;
                }
            }
            if (wait==4 && !check){
                switch (trueCase){
                    case R.id.btnA:
                        btnCaseA.setBackgroundResource(R.drawable.answer_false);
                        playSound(R.raw.lose_a);
                        break;
                    case R.id.btnB:
                        btnCaseB.setBackgroundResource(R.drawable.answer_false);
                        playSound(R.raw.lose_b);
                        break;
                    case R.id.btnC:
                        btnCaseC.setBackgroundResource(R.drawable.answer_false);
                        playSound(R.raw.lose_c);
                        break;
                    case R.id.btnD:
                        btnCaseD.setBackgroundResource(R.drawable.answer_false);
                        playSound(R.raw.lose_d);
                        break;
                }
            }
            if (wait==6 && check) {
                setText(++i);
            }
            if ((wait==6 && !check) || dem==0 || i==16){
                initDiaLogFinish(coin);
            }
        }
    };
    public void showHelp(int timeRunHelp,int idHelp){
        if (timeRunHelp==4 && idHelp== R.id.im50){
            if (trueCase==R.id.btnA){
                btnCaseB.setEnabled(false);
                btnCaseC.setEnabled(false);
            }else if (trueCase==R.id.btnB){
                btnCaseA.setEnabled(false);
                btnCaseD.setEnabled(false);
            }else if (trueCase==R.id.btnC){
                btnCaseA.setEnabled(false);
                btnCaseB.setEnabled(false);
            }else if (trueCase==R.id.btnD){
                btnCaseC.setEnabled(false);
                btnCaseA.setEnabled(false);
            }
            playSound(R.raw.s50);
            run=true;
            checkPickAnswer=true;
        }
        else if (idHelp== R.id.impeople){
            if (timeRunHelp==6){
                playSound(R.raw.bg_audience);
            }
            if (timeRunHelp==11){
                checkPickAnswer=true;
                initDialogAudience();
            }
        }else if (timeRunHelp==3 && idHelp==R.id.imCall){
            checkPickAnswer=true;
            initDiaLogHelpCall();
        }

    }
    public void animnButon(Button btn){
        animationButton= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_check);
        btn.startAnimation(animationButton);
    }
    private void initDiaLogFinish(int coin) {
        dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View view=layoutInflater.inflate(R.layout.dialog_finish,null);
        TextView tvFinish= (TextView) view.findViewById(R.id.tvFinish);
        tvFinish.setText("Bạn đã dành được "+ coin+" ngàn. Cảm ơn bạn đã tham gia " +
                "chương trình. Chúc bạn thành công trong cuộc sống !!!");
        playSound(R.raw.lose);
        Button btnFinish= (Button) view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
    }
    public void initDialogAudience(){
        dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.dialog_people,null);
        TextView tvShowAnswerA= (TextView) view.findViewById(R.id.audience_A);
        TextView tvShowAnswerB= (TextView) view.findViewById(R.id.audience_B);
        TextView tvShowAnswerC= (TextView) view.findViewById(R.id.audience_C);
        TextView tvShowAnswerD= (TextView) view.findViewById(R.id.audience_D);
        Button btnOk= (Button) view.findViewById(R.id.btn_ok_help_audence);
        Random random= new Random();
        int rd1True= random.nextInt(10)+50;
        int rdFalse1=random.nextInt(1)+10;
        int rdFalse2=random.nextInt(1)+10;
        int rdFalse3=100-rd1True-rdFalse1-rdFalse2;
        if (trueCase==R.id.btnA){
            tvShowAnswerA.setText(rd1True+"%");
            tvShowAnswerB.setText(rdFalse1+"%");
            tvShowAnswerC.setText(rdFalse2+"%");
            tvShowAnswerD.setText(rdFalse3+"%");
        }else if (trueCase==R.id.btnB){
            tvShowAnswerA.setText(rdFalse1+"%");
            tvShowAnswerB.setText(rd1True+"%");
            tvShowAnswerC.setText(rdFalse2+"%");
            tvShowAnswerD.setText(rdFalse3+"%");
        }else if (trueCase==R.id.btnC){
            tvShowAnswerA.setText(rdFalse1+"%");
            tvShowAnswerB.setText(rdFalse2+"%");
            tvShowAnswerC.setText(rd1True+"%");
            tvShowAnswerD.setText(rdFalse3+"%");
        }else if (trueCase==R.id.btnD){
            tvShowAnswerA.setText(rdFalse1+"%");
            tvShowAnswerB.setText(rdFalse2+"%");
            tvShowAnswerC.setText(rdFalse3+"%");
            tvShowAnswerD.setText(rd1True+"%");
        }
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                run=true;
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    public void initDiaLogHelpCall(){
        dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.dialog_call,null);
        ImageView imObama= (ImageView) view.findViewById(R.id.help_obama);
        ImageView imStever= (ImageView) view.findViewById(R.id.help_stever);
        ImageView imBillGate= (ImageView) view.findViewById(R.id.help_billgate);
        ImageView imTroll= (ImageView) view.findViewById(R.id.help_troll);
        setOnclick(imObama);
        setOnclick(imStever);
        setOnclick(imBillGate);
        setOnclick(imTroll);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    private void setOnclick(ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogAnswerCall();
            }
        });
    }
    public void showDiaLogAnswerCall(){
        dialog.dismiss();
        dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.show_answer_call,null);
        final TextView tvShowAnswer= (TextView) view.findViewById(R.id.show_answer);
        if (trueCase==R.id.btnA){
            tvShowAnswer.setText("A");
        }else if (trueCase==R.id.btnB){
            tvShowAnswer.setText("B");
        }else if (trueCase==R.id.btnC){
            tvShowAnswer.setText("C");
        }else if (trueCase==R.id.btnD){
            tvShowAnswer.setText("D");
        }
        Button btnOk= (Button) view.findViewById(R.id.btn_ok_help_call);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                run=true;
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    public void playSound(int type){
        mediaPlayer= MediaPlayer.create(this,type);
        mediaPlayer.start();
    }
}
