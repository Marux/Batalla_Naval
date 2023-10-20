package com.example.batallanaval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void iniciarJuego(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void instruccionJuego(View view){
        Intent intent = new Intent(this, tutorial.class);
        startActivity(intent);
    }

    public void barcosJuegos(View view){
        Intent intent = new Intent(this, tipoDeBarco.class);
        startActivity(intent);
    }

    public void cerrarAplicacion(View view) {
        //finish(); // Cierra la actividad actual y, por lo tanto, la aplicación
        finishAffinity();
    }

}