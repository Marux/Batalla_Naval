package com.example.batallanaval;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;

public class tipoDeBarco extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int[] imageIds = {R.drawable.acorazado, R.drawable.destructor, R.drawable.fragata, R.drawable.portaaviones, R.drawable.submarino};
    private String[] titles = {"Barco Acorazado", "Barco Destructor", "Barco Fragata", "Portaaviones", "Submarino"};
    private String[] descriptions = {
            "Un barco de guerra blindado y pesado.",
            "Un barco de guerra rápido y ofensivo.",
            "Un barco de guerra versátil.",
            "Un barco de guerra con aviones de combate.",
            "Un vehículo submarino de combate."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_de_barco);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ImageAdapter(this, imageIds, titles, descriptions));
    }

    public void menuPrincipal(View view){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finishAffinity();
    }
}