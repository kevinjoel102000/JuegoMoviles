package com.example.juegojava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

       //conexion a BD
       AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD",null,1);
       SQLiteDatabase BD = admin.getWritableDatabase();

       Cursor consulta = BD.rawQuery(
               "select * from puntaje where score = (select max(score) from puntaje)",null
       );

       // asigna el mejor puntaje
       if(consulta.moveToFirst()){
           String tempNombre = consulta.getString(0);
           String tempScore = consulta.getString(1);

           score.setText("Record actual: " + tempScore + " de " + tempNombre);
           BD.close();
       }

       // Icono aleatorio al arrancar
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

    public void jugar(View view){
        String nombre = txtNombre.getText().toString();

        if(!nombre.equals("")){
            mp.stop();
            mp.release();

            Intent intent = new Intent(this,MainActivity2_Nivel1.class);
            intent.putExtra("Jugador", nombre);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Ingrese el nombre",Toast.LENGTH_SHORT).show();
            txtNombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtNombre,InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onBackPressed(){

    }
}