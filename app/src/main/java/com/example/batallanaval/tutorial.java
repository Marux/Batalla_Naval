package com.example.batallanaval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }

    public void menuPrincipal(View view){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finishAffinity();
    }

}