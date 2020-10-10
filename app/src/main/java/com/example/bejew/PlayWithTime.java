package com.example.bejew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayWithTime extends AppCompatActivity {
    private int[] vector = new int[]{R.drawable.blue, R.drawable.green, R.drawable.yellow, R.drawable.red, R.drawable.purple, R.drawable.orange};
    int matriz[][] = new int[8][8];
    GridLayout grilla;
    int cont;
    int auxMatriz;
    int dondeEstoy;
    int dondeEstoy2;
    int auxMatriz2;
    int EnX;
    int EnY;
    int click1;
    int click2;
    int Clicks;
    int auxH;
    int auxH2;
    int auxV;
    int auxV2;
    private int estado = 1;
    Random dado = new Random(System.currentTimeMillis());
    TextView timer;
    public static int SPLASH_TIME_OUT= 1000;
    TextView timein;
    int signal;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_time2);



        llenarTableroConGemas();


            timer =  findViewById(R.id.timer);
            new CountDownTimer(120000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("Seconds remaining:" + "  " + millisUntilFinished / 1000 );
                }

                public void onFinish() {
                    timer.setText("done!");

                    AvisoDeQueAcaboElTiempoYverificoRanking();
                }
            }.start();






    }



    public void AvisoDeQueAcaboElTiempoYverificoRanking() {

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("¡Time has ended!");
        builder.setTitle("End");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int bestTime;
                SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES",Context.MODE_PRIVATE);
                bestTime = sharedPreferences.getInt("bestTime",0);
                if ( cont > bestTime ){
                    AvisoDeQueEntroAlRanking();
                }else{
                    AvisoDeQueNoEntroAlRanking();
                }

            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private void AvisoDeQueNoEntroAlRanking() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("Insufficient points. ¡Keep trying!");
        builder.setTitle("Ranking");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                   Intent intent = new Intent(PlayWithTime.this, Inicio.class);
                   startActivity(intent);
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private void AvisoDeQueEntroAlRanking() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setMessage("Congrats! you can appear in the ranking. Do you want to appear?");
        builder.setTitle("Ranking");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("bestTime", cont);
                editor.commit();
                Intent intent = new Intent ( PlayWithTime.this , RankingWithTime.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent (PlayWithTime.this, RankingWithTime.class);
                startActivity(i);
                finish();

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();

    }


    public void llenarTableroConGemas(){

        int color;
        int random;
        int ub;

        grilla = findViewById(R.id.grid);

        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                color = 6;
                random = (int) (Math.random() * color);
                ub = x * 8 + y;
                ImageView prueba = (ImageView) grilla.getChildAt(ub);// .setVisibility(View.INVISIBLE);
                prueba.setImageResource(vector[random]);
                matriz[x][y] = random+1;

            }

        }






        if(!verificarRefresh()){
            Button r = findViewById(R.id.Refresh);
            r.setEnabled(true);
        }

        handler();
        if(!verificarRefresh()){
            Button r = findViewById(R.id.Refresh);
            r.setEnabled(true);
        }else{
            Button r = findViewById(R.id.Refresh);
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        instanciarSetOnClick();


    }

    public void back (View v){
        onBackPressed();
    }

    public void onBackPressed(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);


        builder.setMessage("Do you want to return to the main menu?");
        builder.setTitle("Return");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(PlayWithTime.this , Inicio.class);
                startActivity(intent);
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



    public boolean verSiHayJuego() {

        boolean hayJuego = false;
        for (int fila = 0; fila <= 7; fila++) {
            for (int columna = 0;  columna <= 7; columna++) {
                if(columna<7) {
                    auxH = matriz[fila][columna];
                    auxH2 = matriz[fila][columna + 1];
                    matriz[fila][columna] = auxH2;
                    matriz[fila][columna + 1] = auxH;
                    if (controlDeJuego()) {
                        auxH = matriz[fila][columna];
                        auxH2 = matriz[fila][columna + 1];
                        matriz[fila][columna] = auxH2;
                        matriz[fila][columna + 1] = auxH;
                        hayJuego = true;

                    }
                    if(!controlDeJuego()){
                        auxH = matriz[fila][columna];
                        auxH2 = matriz[fila][columna + 1];
                        matriz[fila][columna] = auxH2;
                        matriz[fila][columna + 1] = auxH;
                    }
                }

                if(fila<7) {
                    auxV = matriz[fila][columna];
                    auxV2 = matriz[fila + 1][columna];
                    matriz[fila][columna] = auxV2;
                    matriz[fila + 1][columna] = auxV;
                    if (controlDeJuego()) {
                        auxV = matriz[fila][columna];
                        auxV2 = matriz[fila + 1][columna];
                        matriz[fila][columna] = auxV2;
                        matriz[fila + 1][columna] = auxV;
                        hayJuego = true;
                    }
                    if(!controlDeJuego()){
                        auxV = matriz[fila][columna];
                        auxV2 = matriz[fila + 1][columna];
                        matriz[fila][columna] = auxV2;
                        matriz[fila + 1][columna] = auxV;

                    }
                }
            }
        }

        return hayJuego;
    }






    public boolean verificarRefresh(){
        return verSiHayJuego();
    }






    public void handler(){


        cascadaEnCurso = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(estado == 1) {
                    if(controlDeJuego()){
                        PlayWithTime.this.barrido();
                        PlayWithTime.this.marcarImagenes();
                        estado = estado + 1;
                        handler.postDelayed(this, 700);

                    } else{
                        PlayWithTime.this.cascadaEnCurso=false;


                    }

                }else{
                    estado=1;
                    PlayWithTime.this.cascada();
                    PlayWithTime.this.imagenes();
                    PlayWithTime.this.desmarcarImagen();
                    handler.postDelayed(this, 700);

                }



            }
        }, 1);



    }

    public void marcarImagenes(){

        int Donde;
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                Donde = fila * 8 + columna;
                ImageView prueba = (ImageView) grilla.getChildAt(Donde);

                if(matriz[fila][columna] < 0 ){
                    prueba.setBackgroundResource(R.color.colorAccent);
                }

                prueba.setImageResource(vector[Math.abs(matriz[fila][columna])-1]);
            }
        }
    }



    public void desmarcarImagen(){
        int Donde=0;
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                Donde = fila * 8 + columna;
                ImageView prueba = (ImageView) grilla.getChildAt(Donde);
                prueba.setBackgroundResource(R.drawable.borde);



            }
        }
    }


    public void cargarMatrizAux(int  [][] matrizAux ){
        for(int fila = 0; fila < 8; fila ++){
            for(int columna = 0; columna < 8; columna ++){
                matrizAux[fila][columna] = 0;
            }
        }
    }



    public void actualizar(){

        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                if(matriz[fila][columna] == 0) {
                    int random = dado.nextInt(vector.length)+1;
                    matriz[fila][columna] = random;
                }


            }
        }
    }



    public void cascada(){
        int matrizAux[][] = new int[8][8];
        cargarMatrizAux(matrizAux);
        int filaAux = 7;
        int columnaAux = 0;
        for(int columna = 0; columna < 8; columna ++){
            for(int fila = 7; fila >=  0; fila --){
                if(matriz[fila][columna] > 0 ){
                    matrizAux[filaAux][columnaAux]=matriz[fila][columna];
                    filaAux--;
                }
            }
            filaAux = 7;
            columnaAux++;
        }

        matriz=matrizAux;
        actualizar();


    }

    public boolean controlDeJuego(){
        boolean control = false;
        for ( int fila = 0; fila < 8; fila++){
            for( int columna = 0; columna < 6  ; columna ++){

                if(matriz[fila][columna] > 0 && matriz[fila][columna+1] > 0 && matriz[fila][columna+2] > 0) {
                    if( matriz[fila][columna]-matriz[fila][columna+1]== 0 && matriz[fila][columna]-matriz[fila][columna+2]== 0)  {
                        control = true;



                    }

                }
            }
        }

        for ( int fila = 0; fila < 6; fila++){
            for( int columna = 0; columna < 8  ; columna ++){

                if(matriz[fila][columna] > 0 && matriz[fila+1][columna] > 0 && matriz[fila+1][columna] > 0) {
                    if( matriz[fila][columna]-matriz[fila+1][columna]== 0 && matriz[fila][columna]-matriz[fila+2][columna]== 0)  {
                        control = true;



                    }

                }
            }
        }

        return control;
    }


    public void barrido() {


        if(controlDeJuego()){
            for ( int fila = 0; fila < 8; fila++){
                for( int columna = 0; columna < 6  ; columna ++){

                    if((Math.abs(matriz[fila][columna])  == (Math.abs(matriz[fila][columna +1])) && (Math.abs(matriz[fila][columna]) == (Math.abs(matriz[fila][columna+2]))))) {


                        if (matriz[fila][columna] > 0) {   // a cada elemento que encuentro lo multiplico por -1 asi cuando el proceso "imagenes" detecte que hay un valor negativo, lo pueda borrar
                            matriz[fila][columna] = matriz[fila][columna] * -1;




                        }
                        if (matriz[fila][columna + 1] > 0) {
                            matriz[fila][columna + 1] = matriz[fila][columna + 1] * -1;



                        }
                        if (matriz[fila][columna + 2] > 0) {
                            matriz[fila][columna + 2] = matriz[fila][columna + 2] * -1;



                        }

                    }
                }
            }

            for ( int fila2 = 0; fila2 < 6; fila2++){
                for( int columna = 0; columna < 8  ; columna ++){

                    if((Math.abs(matriz[fila2][columna])  == (Math.abs(matriz[fila2+1][columna ])) && (Math.abs(matriz[fila2][columna]) == (Math.abs(matriz[fila2+2][columna]))))) {

                        // ACA IRIA PREGUNTANDO DE A 3 SI HAY JUEGO, TENGO QUE PONER MATH ABS POR SI ESA POSICION YA ESTABA EN NEGATIVO, ESTO ME SOLUCIONARIA EL PROBLEMA DE FORMAR LA "T"

                        if (matriz[fila2][columna] > 0 ){   // a cada elemento que encuentro lo multiplico por -1 asi cuando el proceso "imagenes" detecte que hay un valor negativo, lo pueda borrar
                            matriz[fila2][columna]= matriz[fila2][columna] * -1;



                        }
                        if (matriz[fila2+1][columna] > 0 ){
                            matriz[fila2+1][columna ]=  matriz[fila2+1][columna] *-1;



                        }
                        if (matriz[fila2+2][columna ] > 0 ){
                            matriz[fila2+2][columna ]= matriz[fila2+2][columna]*-1;



                        }


                    }
                }
            }
            sumarPuntos();




        }



    }

    public void sumarPuntos(){

        TextView t = (TextView) findViewById(R.id.puntos);

        for ( int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {

                if(matriz[fila][columna] < 0 ){
                    cont = cont +1 ;
                    t.setText(Integer.toString(cont));

                }
            }
        }


    }







    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void imagenes (){

        int ubicaion;
        ImageView imagen;
        for ( int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {


                ubicaion = fila * 8 + columna;
                imagen = (ImageView) grilla.getChildAt(ubicaion);
                imagen.setImageResource(vector[matriz[fila][columna]-1]);
            }
        }

    }




    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void instanciarSetOnClick() {
        Log.d("click","ckiclllllll");
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                int ubicacion = x * 8 + y;

                final int posX = x;
                final int posY = y;

                grilla.getChildAt(ubicacion).setOnClickListener(new View.OnClickListener() {  //a cada imagen seteada en la matriz le asigno un onClickListener, es decir que cuando se haga click va a responder a cierto "evento/logica"
                    @Override
                    public void onClick(View v) {
                        PlayWithTime.this.onGemaClick(v,posX, posY);

                    }
                });

            }
        }
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    boolean cascadaEnCurso = false;

    public void onGemaClick(View view, int x, int y) {
        int auxPos;
        int restaC;
        int restaF;

        if (!cascadaEnCurso){
            Clicks = Clicks + 1;




            if (Clicks == 1) {
                click1 = 1;     // ASIGNO PARA LUEGO COMPARAR
                auxMatriz = matriz[x][y]; // ME GUARDO EL VALOR DE LA POS (X,Y) EN MI MATRIZ DEL PRIMER CLICK
                dondeEstoy = x * 8 + y; // ME GUARDO LA UBICACION DE DONDE HICE EL PRIMER CLICK
                EnX = x;   // ME GUARDO EN UNA VARIABLE MI X
                EnY = y;   // ME GUARDO EN OTRA VARIABLE MY
                View aux = view;


                aux.setSelected(true);

            }
            if (Clicks == 2) {
                click2 = 2;   // ASIGNO PARA LUEGO COMPARAR
                auxMatriz2 = matriz[x][y]; // ME GUARDO EL VALOR DE LA POS (X,Y) DE MI MATRIZ DEL PRIMER CLICK
                dondeEstoy2 = x * 8 + y; // ME GUARDO LA UBICACION PARA SABER EN DONDE HICE EL CLICK
                auxPos = matriz[x][y];
                View aux2 = (View)grilla.getChildAt(dondeEstoy);
                aux2.setSelected(false);

                restaC = EnY - y;
                restaF = EnX - x;

                if((Math.abs(restaC) == 1 && Math.abs(restaF) == 0) ||  ((Math.abs(restaC)  == 0) && Math.abs(restaF)== 1)){

                    matriz[x][y] = matriz[EnX][EnY];  // HAGO EL INTERCAMBIO EN LA MATRIZ
                    matriz[EnX][EnY] = auxPos; // HAGO EL INTERCAMBIO  EN LA MATRIZ
                    if (click1 != click2) {   //HAGO LA COMPARACION PARA SABER SI NO SE HIZO UN TERCER CLICK


                        Boolean hubojuego = controlDeJuego();


                        if(!hubojuego){
                            auxPos = matriz[x][y];
                            matriz[x][y] = matriz[EnX][EnY];  // HAGO EL INTERCAMBIO EN LA MATRIZ
                            matriz[EnX][EnY] = auxPos;
                            imagenes();// HAGO EL INTERCAMBIO  EN LA MATRIZ
                        }else{
                            handler();


                        }




                        //ACTUALIZO
                    }

                }





                if(Clicks == 2){
                    Clicks = 0;
                }
            }


        }
    }
















}


