package com.example.bejew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Instructions extends AppCompatActivity {
    boolean siEsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        SharedPreferences sharedIns = getSharedPreferences("INSTRUCTIONS2", Context.MODE_PRIVATE);
        siEsTrue = sharedIns.getBoolean("showDialog", true);
        if ( siEsTrue){
            instrucciones();

        }else{
            Intent i = new Intent(Instructions.this, PlayWithTime.class);
            startActivity(i);

        }


    }


    public void instrucciones (){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("\n" + "You only have 120 seconds to form combinations  ");
        builder.setTitle("Instructions");
        builder.setPositiveButton("Okay! I understrand.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                Intent i = new Intent(Instructions.this, PlayWithTime.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();



            }
        });
        builder.setNegativeButton("Never show again pls!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                dialog.dismiss();
                SharedPreferences sharedIns = getSharedPreferences("INSTRUCTIONS2", 0);
                SharedPreferences.Editor InstructionsEditor = sharedIns.edit();
                InstructionsEditor.putBoolean("showDialog", false);
                InstructionsEditor.commit();
                Intent i = new Intent(Instructions.this, PlayWithTime.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();


            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();



    }
}
