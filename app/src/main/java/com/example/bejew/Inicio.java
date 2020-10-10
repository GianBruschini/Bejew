package com.example.bejew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class Inicio extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 580;
    TextView bejew;


    String array [] = {"play" , "ranking" ,  "information"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        CircleMenu circleMenu = findViewById(R.id.circleMenu);


        circleMenu.setMainMenu(Color.parseColor("#FFFFFF"), R.drawable.menu, R.drawable.cancel)
                .addSubMenu(Color.parseColor("#FF2196F3"), R.drawable.play)
                .addSubMenu(Color.parseColor("#FFFFC107"), R.drawable.ranking)
                .addSubMenu(Color.parseColor("#FFEE0A0A"), R.drawable.information)
                .addSubMenu(Color.parseColor("#FF673AB7"), R.drawable.timer)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        final int index1 = index;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                if ( index1 == 0 ){
                                    Intent intent = new Intent(Inicio.this, Play.class);

                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    finish();

                                }

                                if ( index1 == 1 ){
                                    finish();
                                    Intent intent = new Intent(Inicio.this, Ranking.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    finish();

                                }

                                if ( index1 == 2 ){
                                    Intent intent = new Intent(Inicio.this, Information.class);
                                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    finish();
                                }

                                if ( index1 == 3 ){
                                    Intent intent= new Intent(Inicio.this, Instructions.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    finish();
                                }



                            }
                        },SPLASH_TIME_OUT);

                        //Toast.makeText(Inicio.this, "You selected" + "" +  array[index], Toast.LENGTH_SHORT).show();


                    }
                });



    }



}
