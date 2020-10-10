package com.example.bejew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Presentation extends AppCompatActivity {
    public static int SPLASH_TIME_OUT= 2300;
    TextView bejew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Presentation.this, Inicio.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();

            }
        },SPLASH_TIME_OUT);

        bejew= findViewById(R.id.Bejew);
        startAnimation();
    }



    public void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this , R.anim.rotate);
        bejew.startAnimation(animation);

    }
}
