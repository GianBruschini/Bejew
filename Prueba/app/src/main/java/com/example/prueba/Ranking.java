package com.example.prueba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Ranking extends AppCompatActivity {
    int lastScore;
    String lastNombre;
    int best1, best2 , best3,best4,best5,best6,best7,best8,best9,best10;
    String nombre1,nombre2,nombre3,nombre4,nombre5,nombre6,nombre7,nombre8,nombre9,nombre10;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int activityNumber = intent.getIntExtra("activity", 0);

        if(activityNumber == 1){
            SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            lastScore = preferences.getInt("lastScore" , 0);
            lastNombre = preferences.getString("lastNombre",null);
            best1 = preferences.getInt("best1", 0);
            nombre1 = preferences.getString("nombre1" , null);
            best2 = preferences.getInt("best2", 0);
            nombre2 = preferences.getString("nombre2" , null);
            best3 = preferences.getInt("best3", 0);
            nombre3 = preferences.getString("nombre3" , null);
            best4 = preferences.getInt("best4", 0);
            nombre4 = preferences.getString("nombre4" , null);
            best5 = preferences.getInt("best5", 0);
            nombre5 = preferences.getString("nombre5" , null);
            best6 = preferences.getInt("best6", 0);
            nombre6 = preferences.getString("nombre6" , null);
            best7 = preferences.getInt("best7", 0);
            nombre7 = preferences.getString("nombre7" , null);
            best8 = preferences.getInt("best8", 0);
            nombre8 = preferences.getString("nombre8" , null);
            best9 = preferences.getInt("best9", 0);
            nombre9 = preferences.getString("nombre9" , null);
            best10 = preferences.getInt("best10", 0);
            nombre10 = preferences.getString("nombre10" , null);


            if(lastScore > best10){
                best10 = lastScore;
                nombre10 = lastNombre;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best10" , best10);
                editor.putString("nombre10", nombre10);
                editor.commit();
            }
            if(lastScore > best9){
                int temp = best9;
                String tempn = nombre9;
                best9 = lastScore;
                nombre9 = lastNombre;
                best10 = temp;
                nombre10 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best10" , best10);
                editor.putString("nombre10" , nombre10);
                editor.putInt("best9" , best9);
                editor.putString("nombre9" , nombre9);
                editor.commit();
            }
            if(lastScore > best8){
                int temp = best8;
                String tempn = nombre8;
                best8 = lastScore;
                nombre8 = lastNombre;
                best9 = temp;
                nombre9 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best9" , best9);
                editor.putString("nombre9" , nombre9);
                editor.putInt("best8" , best8);
                editor.putString("nombre8" , nombre8);
                editor.commit();
            }
            if(lastScore > best7){
                int temp = best7;
                String tempn = nombre7;
                best7 = lastScore;
                nombre7 = lastNombre;
                best8 = temp;
                nombre8 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best8" , best8);
                editor.putString("nombre8" , nombre8);
                editor.putInt("best7" , best7);
                editor.putString("nombre7" , nombre7);
                editor.commit();
            }
            if(lastScore > best6){
                int temp = best6;
                String tempn = nombre6;
                best6 = lastScore;
                nombre6 = lastNombre;
                best7 = temp;
                nombre7 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best7" , best7);
                editor.putString("nombre7" , nombre7);
                editor.putInt("best6" , best6);
                editor.putString("nombre6" , nombre6);
                editor.commit();
            }
            if(lastScore > best5){
                int temp = best5;
                String tempn = nombre5;
                best5 = lastScore;
                nombre5 = lastNombre;
                best6 = temp;
                nombre6 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best6" , best6);
                editor.putString("nombre6" , nombre6);
                editor.putInt("best5" , best4);
                editor.putString("nombre5" , nombre5);
                editor.commit();
            }
            if(lastScore > best4){
                int temp = best4;
                String tempn = nombre4;
                best4 = lastScore;
                nombre4 = lastNombre;
                best5 = temp;
                nombre5 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best5" , best5);
                editor.putString("nombre5" , nombre5);
                editor.putInt("best4" , best4);
                editor.putString("nombre4" , nombre4);
                editor.commit();
            }
            if(lastScore > best3){
                int temp = best3;
                String tempn = nombre3;
                best3 = lastScore;
                nombre3 = lastNombre;
                best4 = temp;
                nombre4 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best4" , best4);
                editor.putString("nombre4" , nombre4);
                editor.putInt("best3" , best3);
                editor.putString("nombre3" , nombre3);
                editor.commit();

            }

            if(lastScore > best2){
                int temp = best2;
                String tempn = nombre2;
                best2 = lastScore;
                nombre2 = lastNombre;
                best3 = temp;
                nombre3 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3" , best3);
                editor.putString("nombre3" , nombre3);
                editor.putInt("best2" , best2);
                editor.putString("nombre2" , nombre2);
                editor.commit();
            }


            if(lastScore > best1){
                int temp = best1;
                String tempn = nombre1;
                best1 = lastScore;
                nombre1 = lastNombre;
                best2 = temp;
                nombre2 = tempn;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best2" , best2);
                editor.putString("nombre2", nombre2);
                editor.putInt("best1" , best1);
                editor.putString("nombre1", nombre1);
                editor.commit();
            }


            TextView score1 = findViewById(R.id.score1);
            score1.setText(String.valueOf(best1));
            TextView n1 = findViewById(R.id.user1);
            n1.setText(nombre1);


            TextView score2 = findViewById(R.id.score2);
            score2.setText(String.valueOf(best2));
            TextView n2 = findViewById(R.id.user2);
            n2.setText(nombre2);


            TextView score3 = findViewById(R.id.score3);
            score3.setText(String.valueOf(best3));
            TextView n3 = findViewById(R.id.user3);
            n3.setText(nombre3);


            TextView score4 = findViewById(R.id.score4);
            score4.setText(String.valueOf(best4));
            TextView n4 = findViewById(R.id.user4);
            n4.setText(nombre4);


            TextView score5 = findViewById(R.id.score5);
            score5.setText(String.valueOf(best5));
            TextView n5 = findViewById(R.id.user5);
            n5.setText(nombre5);

            TextView score6 = findViewById(R.id.score6);
            score6.setText(String.valueOf(best6));
            TextView n6 = findViewById(R.id.user6);
            n6.setText(nombre6);


            TextView score7 = findViewById(R.id.score7);
            score7.setText(String.valueOf(best7));
            TextView n7 = findViewById(R.id.user7);
            n7.setText(nombre7);


            TextView score8 = findViewById(R.id.score8);
            score8.setText(String.valueOf(best8));
            TextView n8 = findViewById(R.id.user8);
            n8.setText(nombre8);


            TextView score9 = findViewById(R.id.score9);
            score9.setText(String.valueOf(best9));
            TextView n9 = findViewById(R.id.user9);
            n9.setText(nombre9);



            TextView score10 = findViewById(R.id.score10);
            score10.setText(String.valueOf(best10));
            TextView n10 = findViewById(R.id.user10);
            n10.setText(nombre10);

        }else{
            SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            lastScore = preferences.getInt("lastScore" , 0);
            lastNombre = preferences.getString("lastNombre",null);
            best1 = preferences.getInt("best1", 0);
            nombre1 = preferences.getString("nombre1" , null);
            best2 = preferences.getInt("best2", 0);
            nombre2 = preferences.getString("nombre2" , null);
            best3 = preferences.getInt("best3", 0);
            nombre3 = preferences.getString("nombre3" , null);
            best4 = preferences.getInt("best4", 0);
            nombre4 = preferences.getString("nombre4" , null);
            best5 = preferences.getInt("best5", 0);
            nombre5 = preferences.getString("nombre5" , null);
            best6 = preferences.getInt("best6", 0);
            nombre6 = preferences.getString("nombre6" , null);
            best7 = preferences.getInt("best7", 0);
            nombre7 = preferences.getString("nombre7" , null);
            best8 = preferences.getInt("best8", 0);
            nombre8 = preferences.getString("nombre8" , null);
            best9 = preferences.getInt("best9", 0);
            nombre9 = preferences.getString("nombre9" , null);
            best10 = preferences.getInt("best10", 0);
            nombre10 = preferences.getString("nombre10" , null);


            TextView score1 = findViewById(R.id.score1);
            score1.setText(String.valueOf(best1));
            TextView n1 = findViewById(R.id.user1);
            n1.setText(nombre1);


            TextView score2 = findViewById(R.id.score2);
            score2.setText(String.valueOf(best2));
            TextView n2 = findViewById(R.id.user2);
            n2.setText(nombre2);


            TextView score3 = findViewById(R.id.score3);
            score3.setText(String.valueOf(best3));
            TextView n3 = findViewById(R.id.user3);
            n3.setText(nombre3);


            TextView score4 = findViewById(R.id.score4);
            score4.setText(String.valueOf(best4));
            TextView n4 = findViewById(R.id.user4);
            n4.setText(nombre4);


            TextView score5 = findViewById(R.id.score5);
            score5.setText(String.valueOf(best5));
            TextView n5 = findViewById(R.id.user5);
            n5.setText(nombre5);

            TextView score6 = findViewById(R.id.score6);
            score6.setText(String.valueOf(best6));
            TextView n6 = findViewById(R.id.user6);
            n6.setText(nombre6);


            TextView score7 = findViewById(R.id.score7);
            score7.setText(String.valueOf(best7));
            TextView n7 = findViewById(R.id.user7);
            n7.setText(nombre7);


            TextView score8 = findViewById(R.id.score8);
            score8.setText(String.valueOf(best8));
            TextView n8 = findViewById(R.id.user8);
            n8.setText(nombre8);


            TextView score9 = findViewById(R.id.score9);
            score9.setText(String.valueOf(best9));
            TextView n9 = findViewById(R.id.user9);
            n9.setText(nombre9);



            TextView score10 = findViewById(R.id.score10);
            score10.setText(String.valueOf(best10));
            TextView n10 = findViewById(R.id.user10);
            n10.setText(nombre10);



        }

      /*SharedPreferences preferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = preferences.edit();
        editor2.clear();
        editor2.commit();
      */









    }


    public void onBackPressed2(){
        android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(this);
        if(Locale.getDefault().getLanguage().contentEquals("en")){
            builder.setMessage("Do you want to return to the main menu?");
            builder.setTitle("Return");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Ranking.this,Inicio.class);
                    startActivity(intent);
                    finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            android.support.v7.app.AlertDialog dialog=builder.create();
            dialog.show();
        }else{
            builder.setMessage("Desea volver al menu principal?");
            builder.setTitle("Volver");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Ranking.this,Inicio.class);
                    startActivity(intent);
                    finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed2();
        return false;
    }


    public void onBackPressed(){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
        finish();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.themenu, menu);

        return super.onCreateOptionsMenu(menu);
    }




    /*tv_score.setText("LAST SCORE: " + String.valueOf(lastScore) + "\n" +
               "BEST1: " + String.valueOf(best1) + "\n" +
               "BEST2: " + String.valueOf(best2) + "\n" +
               "BEST3: " + String.valueOf(best3));
       */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = nombre1 + " : " + best1 + "\n" + nombre2 + " : " + best2 + "\n" + nombre3 + " : " + best3 + "\n" + nombre4 + " : " + best4 + "\n" + nombre5 + " : " + best5 + "\n" + nombre6 + " : " + best6 + "\n" + nombre7 + " : " + best7 + "\n" + nombre8 + " : " + best8 + "\n" + nombre9 + " : " + best9 + "\n" + nombre10 + " : " + best10 ;

                String shareSubject = "your subject";

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(sharingIntent, "Share"));
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}
