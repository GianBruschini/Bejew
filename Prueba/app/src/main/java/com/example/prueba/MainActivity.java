package com.example.prueba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private int[] vector = new int[]{R.drawable.blue, R.drawable.green, R.drawable.yellow, R.drawable.red, R.drawable.purple, R.drawable.orange};
    int matriz[][] = new int[8][8];
    int cont;
    int auxMatriz;
    int dondeEstoy;
    int dondeEstoy2;
    int auxMatriz2;
    int EnX;
    int EnY;
    int click1;
    int click2;
    int ub;
    int Clicks;
    int color;
    int random;
    int auxH;
    int auxH2;
    int auxV;
    int[]vectorRefresh=new int[64];
    int auxV2;
    private int estado = 1;
    private int best1,best2,best3,best4,best5,best6,best7,best8,best9,best10;
    Random dado = new Random(System.currentTimeMillis());
    GridLayout grilla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grilla = findViewById(R.id.grid);


        ///----------------  Matriz para probar el refresh funciona ------------------------------------///

        /*int i = 0;
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                ub = x * 8 + y;
                ImageView prueba = (ImageView) grilla.getChildAt(ub);// .setVisibility(View.INVISIBLE);
                prueba.setImageResource(vector[i]);
                matriz[x][y] = i + 1;
                i++;
                if(i==6){
                    i=0;
                }
            }

        }
        */



        ///----------------  Matriz para probar el refresh funciona ------------------------------------///





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














        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        //WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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

    public void setEnableFalse(){
        GridLayout G=findViewById(R.id.grid);
        ImageView prueba;
        int posicion;
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                posicion = fila * 8 + columna;
                prueba = (ImageView) G.getChildAt(posicion);
                prueba.setEnabled(false);
            }
        }
    }

    public void setEnableTrue(){
        GridLayout G=findViewById(R.id.grid);
        ImageView prueba;
        int posicion;
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                posicion = fila * 8 + columna;
                prueba = (ImageView) G.getChildAt(posicion);
                prueba.setEnabled(true);
            }
        }
    }


    public void Refrescate(View view){
        Random rgen = new Random();
        Button r = findViewById(R.id.Refresh);

        int i=0;
        int k = 0;





        //me guardo cada elemento de la matriz en mi array
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                vectorRefresh[i]=matriz[fila][columna];
                i++;
            }
        }

        for (int j=0; j<vectorRefresh.length; j++) {
            System.out.println(vectorRefresh[j]);
        }



        //shuffleo el array
        for (int j=0; j<vectorRefresh.length; j++) {
            int randomPosition = rgen.nextInt(vectorRefresh.length);
            int temp = vectorRefresh[j];
            vectorRefresh[j] = vectorRefresh[randomPosition];
            vectorRefresh[randomPosition] = temp;
        }

        //actualizo la matriz con los elementos de mi array shuffleado
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                matriz[fila][columna] = vectorRefresh[k];
                k++;

            }
        }


        imagenes();
        if(!verificarRefresh()){
            Button ra = findViewById(R.id.Refresh);
            ra.setEnabled(true);
        }else{
            Button ra = findViewById(R.id.Refresh);
            ra.setEnabled(false);
        }
        if(controlDeJuego()){
            handler();
        }


    }

    public void imagenes2 (){
        int k = 0;
        int ubicaion;
        ImageView imagen;
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                ubicaion = fila * 8 + columna;
                imagen = (ImageView) grilla.getChildAt(ubicaion);
                imagen.setImageResource(vectorRefresh[k]);
                k++;
            }
        }

    }

    public boolean verificarRefresh(){
        return verSiHayJuego();
    }



    public void Terminar(View view){
        onBackPressed2();
    }

    public void onBackPressed2(){
        if(Locale.getDefault().getLanguage().contentEquals("en")){

            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("Do you want to finish the match?");
            builder.setTitle("Finish");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPref = getSharedPreferences("PREFS",Context.MODE_PRIVATE);
                    best1 = sharedPref.getInt("best1",0);
                    best2 = sharedPref.getInt("best2",0);
                    best3 = sharedPref.getInt("best3",0);
                    best4 = sharedPref.getInt("best4",0);
                    best5 = sharedPref.getInt("best5",0);
                    best6 = sharedPref.getInt("best6",0);
                    best7 = sharedPref.getInt("best7",0);
                    best8 = sharedPref.getInt("best8",0);
                    best9 = sharedPref.getInt("best9",0);
                    best10 = sharedPref.getInt("best10",0);

                    if((cont > best1) ||  (cont>best2) || (cont>best3) || ( cont > best4) || (cont > best5) || (cont>best6) || (cont>best7) || (cont>best8) || (cont>best9) || (cont>best10)){
                        deseaFigurar();
                    }else{
                        finish();
                    }
                    //veria si cont puede entrar al ranking, si entra llamo al metodo Terminar.
                    //else Finish(), es decir si no entra al ranking que termine.

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

        }else{
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("Desea terminar la partida?");
            builder.setTitle("Terminar");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPref = getSharedPreferences("PREFS",Context.MODE_PRIVATE);
                    best1 = sharedPref.getInt("best1",0);
                    best2 = sharedPref.getInt("best2",0);
                    best3 = sharedPref.getInt("best3",0);
                    best4 = sharedPref.getInt("best4",0);
                    best5 = sharedPref.getInt("best5",0);
                    best6 = sharedPref.getInt("best6",0);
                    best7 = sharedPref.getInt("best7",0);
                    best8 = sharedPref.getInt("best8",0);
                    best9 = sharedPref.getInt("best9",0);
                    best10 = sharedPref.getInt("best10",0);

                    if((cont > best1) ||  (cont>best2) || (cont>best3) || ( cont > best4) || (cont > best5) || (cont>best6) || (cont>best7) || (cont>best8) || (cont>best9) || (cont>best10)){
                        deseaFigurar();
                    }else{
                        finish();
                    }
                    //veria si cont puede entrar al ranking, si entra llamo al metodo Terminar.
                    //else Finish(), es decir si no entra al ranking que termine.

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

    public void deseaFigurar(){

        if(Locale.getDefault().getLanguage().contentEquals("en")){
            final AlertDialog.Builder builder= new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog, null);
            //builder.setMessage("Congratulations! you are in the top 10. Do you want to appear?");
            builder.setView(dialogView)

                    .setPositiveButton("Figure" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            verificar(dialogView);
                            finish();
                        }
                    })
                    .setNegativeButton("Not appear", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this,Ranking.class);
                            intent.putExtra("activity", 3);
                            startActivity(intent);

                        }
                    });

            builder.show();

        }else{
            final AlertDialog.Builder builder= new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog, null);
            //builder.setMessage("Felicitaciones! Usted esta en el top 10. ¿Desea figurar?");


            builder.setView(dialogView)


                    .setPositiveButton("Figurar" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            verificar(dialogView);
                            finish();
                        }
                    })
                    .setNegativeButton("No figurar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this,Ranking.class);
                            intent.putExtra("activity", 3);
                            startActivity(intent);

                        }
                    });

            builder.show();

        }


    }

    public void verificar(View dialogView){
        SharedPreferences sharedPref = getSharedPreferences("PREFS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        EditText b = dialogView.findViewById(R.id.userName);
        String name = b.getText().toString(); //Obtengo el nombre que se inserto en el cuadro
        editor.putInt("lastScore",cont);
        editor.putString("lastNombre",name);
        editor.commit();
        Intent intent = new Intent(this,Ranking.class);
        intent.putExtra("activity", 1);
        startActivity(intent);


    }


    public void onBackPressed(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        if(Locale.getDefault().getLanguage().contentEquals("en")){
            builder.setMessage("Do you want to return to the main menu?");
            builder.setTitle("Return");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
        }else{
            builder.setMessage("Desea volver al menu principal?");
            builder.setTitle("Volver");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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




    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }

    public void handler(){

        Button r = findViewById(R.id.finish);
        r.setEnabled(false);
        cascadaEnCurso = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(estado == 1) {
                    if(controlDeJuego()){
                        MainActivity.this.barrido();
                        MainActivity.this.marcarImagenes();
                        estado = estado + 1;
                        handler.postDelayed(this, 700);

                    } else{
                        MainActivity.this.cascadaEnCurso=false;
                        Button r = findViewById(R.id.finish);
                        r.setEnabled(true);
                    }

                }else{
                    estado=1;
                    MainActivity.this.cascada();
                    MainActivity.this.imagenes();
                    MainActivity.this.desmarcarImagen();
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
        ImageView imagen = null;

        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna ++){
                if(matriz[fila][columna] == 0) {
                    int random = dado.nextInt(vector.length)+1;
                   /* ub = fila * 8 + columna;
                    ImageView prueba = (ImageView) grilla.getChildAt(ub);// .setVisibility(View.INVISIBLE);
                    prueba.setImageResource(vector[random] );*/
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
                        // ACA IRIA PREGUNTANDO DE A 3 SI HAY JUEGO, TENGO QUE PONER MATH ABS POR SI ESA POSICION YA ESTABA EN NEGATIVO, ESTO ME SOLUCIONARIA EL PROBLEMA DE FORMAR LA "T"


                    }

                }
            }
        }

        for ( int fila = 0; fila < 6; fila++){
            for( int columna = 0; columna < 8  ; columna ++){

                if(matriz[fila][columna] > 0 && matriz[fila+1][columna] > 0 && matriz[fila+1][columna] > 0) {
                    if( matriz[fila][columna]-matriz[fila+1][columna]== 0 && matriz[fila][columna]-matriz[fila+2][columna]== 0)  {
                        control = true;
                        // ACA IRIA PREGUNTANDO DE A 3 SI HAY JUEGO, TENGO QUE PONER MATH ABS POR SI ESA POSICION YA ESTABA EN NEGATIVO, ESTO ME SOLUCIONARIA EL PROBLEMA DE FORMAR LA "T"


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

                        // ACA IRIA PREGUNTANDO DE A 3 SI HAY JUEGO, TENGO QUE PONER MATH ABS POR SI ESA POSICION YA ESTABA EN NEGATIVO, ESTO ME SOLUCIONARIA EL PROBLEMA DE FORMAR LA "T"

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
        Button r = findViewById(R.id.Refresh);
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                int ubicacion = x * 8 + y;

                final int posX = x;
                final int posY = y;

                grilla.getChildAt(ubicacion).setOnClickListener(new View.OnClickListener() {  //a cada imagen seteada en la matriz le asigno un onClickListener, es decir que cuando se haga click va a responder a cierto "evento/logica"
                    @Override
                    public void onClick(View v) {
                        MainActivity.this.onGemaClick(v,posX, posY);

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
        Button r = findViewById(R.id.finish);
        r.setEnabled(true);
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

