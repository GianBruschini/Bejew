package com.example.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void Jugar(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void Exit(View view){

        this.finish();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    public void Rank(View view){
        Intent intent = new Intent(getApplicationContext(),Ranking.class);
        intent.putExtra("activity", 2);
        startActivity(intent);
    }


}
