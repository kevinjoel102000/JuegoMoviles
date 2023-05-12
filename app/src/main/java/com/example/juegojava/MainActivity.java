package com.example.juegojava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView icon;
    EditText txtNombre;
    TextView score;
    Button btnPlay;

    //instalar la musica
    MediaPlayer mp;

    int numAleatorio = (int) (Math.random() * 10 );

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar2));

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setIcon(R.mipmap.ic_launcher);

       icon = findViewById(R.id.imageV_personaje);
       txtNombre = findViewById(R.id.editT_Name);
       score = findViewById(R.id.txt_highScore);
       btnPlay = findViewById(R.id.btnJugar);

        int id;

        switch (numAleatorio){
            case 0 : case 10:
                id = getResources().getIdentifier("mango", "drawable",getPackageName());
                icon.setImageResource(id);
                break;
            case 1 : case 9:
                id = getResources().getIdentifier("fresa", "drawable",getPackageName());
                icon.setImageResource(id);
                break;
            case 2 : case 8:
                id = getResources().getIdentifier("manzana", "drawable",getPackageName());
                icon.setImageResource(id);
                break;
            case 3 : case 7:
                id = getResources().getIdentifier("sandia", "drawable",getPackageName());
                icon.setImageResource(id);
                break;
            case 4 : case 5: case 6:
                id = getResources().getIdentifier("uva", "drawable",getPackageName());
                icon.setImageResource(id);
                break;

            default:
                id = getResources().getIdentifier("naranja", "drawable",getPackageName());
                icon.setImageResource(id);
                break;
        }

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);




    }
}