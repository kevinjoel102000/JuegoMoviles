package com.example.juegojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity2_Nivel1 extends AppCompatActivity {
    Toolbar myToolBar;
    TextView txt_Nombre, txt_Score;
    ImageView iv_AUno, iv_ADos, iv_Vidas;
    EditText et_Respuesta;
    MediaPlayer mp, mpGreat, mpBad;

    int score, numAleatorio_1,numAleatorio_2, resultado, vidas=3;

    String nomJugador, string_Score, string_Vidas;

    String numero[] = {"cero","uno","dos","tres","cuatro","cinco","seis","siete",
                        "ocho", "nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_nivel1);

        Toast.makeText(this, "Nivel 1: Sumas Basicas", Toast.LENGTH_LONG).show();
        txt_Nombre = findViewById(R.id.txt_Nombre);
        txt_Score = findViewById(R.id.txt_Score);
        iv_AUno = findViewById(R.id.imageView_NumeroUno);
        iv_ADos = findViewById(R.id.imageView_NumeroDos);
        iv_Vidas = findViewById(R.id.imageView_Manzanas);
        et_Respuesta = findViewById(R.id.et_Resultado);

        nomJugador = getIntent().getStringExtra("Jugador");
        txt_Nombre.setText("Jugador: " + nomJugador);

        setSupportActionBar(findViewById(R.id.toolbar_Nivl1));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        mpGreat = MediaPlayer.create(this, R.raw.wonderful);
        mpBad = MediaPlayer.create(this, R.raw.bad);

        numAleatorio();



    }

    public void comparar(View view){
        String respuesta = et_Respuesta.getText().toString();
        if(!respuesta.equals("")){
            int respuestaJugador = Integer.parseInt(respuesta);
            if (respuestaJugador  == resultado){
                mpGreat.start();
                score ++ ;
                txt_Score.setText("Score: " + score);
            }else{
                mpBad.start();
                vidas -- ;
                switch (vidas){
                    case 3:
                        iv_Vidas.setImageResource(R.drawable.dosvidas);
                        Toast.makeText(this,"Tienes 3 Manzanas", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        iv_Vidas.setImageResource(R.drawable.dosvidas);
                        Toast.makeText(this,"Quedan 2 Manzanas", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        iv_Vidas.setImageResource(R.drawable.unavida);
                        Toast.makeText(this,"Queda 1 Manzana", Toast.LENGTH_SHORT).show();
                        break;

                    case 0 :
                        Toast.makeText(this,"Quedan 0 Manzanas, perdiste.", Toast.LENGTH_SHORT).show();
                        mp.stop();
                        mp.release();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
            // BASE DE DATOS -> hacer registros
            et_Respuesta.setText("");
            numAleatorio();
        }else{
            Toast.makeText(this,"Digite el resultado.",Toast.LENGTH_LONG).show();
        }
    }

    private void numAleatorio() {
        if(score <= 9){
            numAleatorio_1 = (int) (Math.random() *10);
            numAleatorio_2 = (int) (Math.random() *10);

            resultado  = numAleatorio_1 + numAleatorio_2;

            if(resultado <= 10){
                // Eliminar el FOR como prueba de mejora
                for(int i =0; i < numero.length; i++){
                    int id = getResources().getIdentifier(numero[i], "drawable",getPackageName());
                    if(numAleatorio_1 == i){
                        iv_AUno.setImageResource(id);
                    }
                    if(numAleatorio_2 == i){
                        iv_ADos.setImageResource(id);
                    }
                }
            }else{
                numAleatorio();
            }

        }else {
            Intent intent = new Intent(this,MainActivity2_Nivel2.class);

            string_Score = String.valueOf(score);
            string_Vidas = String.valueOf(vidas);

            intent.putExtra("Jugador: ", nomJugador);
            intent.putExtra("Score: ", string_Score);
            intent.putExtra("Vidas: ", string_Vidas);

            mp.stop();
            mp.release();
            startActivity(intent);
            finish();
        }
    }


}