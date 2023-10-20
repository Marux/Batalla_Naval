package com.example.batallanaval;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView counterTextView;

    private static final int TAMANO_TABLERO = 10;
    private static final char CASILLA_AGUA = ' ';
    private static final char CASILLA_GOLPEADA = 'X';

    private GridLayout gridLayout;
    private Button[][] casillas;

    private int[][] barcos;
    private int barcosRestantes;

    private char[][] tablero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);

        gridLayout = findViewById(R.id.gridLayout);
        casillas = new Button[TAMANO_TABLERO][TAMANO_TABLERO];
        barcos = new int[TAMANO_TABLERO][TAMANO_TABLERO];
        barcosRestantes = 14; // Suma de las posiciones de los barcos
        tablero = new char[TAMANO_TABLERO][TAMANO_TABLERO];
        inicializarTablero();
        colocarBarcos();
    }

    private void inicializarTablero() {
        int buttonSize = 100; // Tamaño en píxeles (ajusta este valor según tus necesidades)

        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                Button button = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = buttonSize;
                params.height = buttonSize;
                button.setLayoutParams(params);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCasillaClick((Button) v);
                    }
                });
                gridLayout.addView(button);
                casillas[i][j] = button;
            }
        }
    }


    private void colocarBarcos() {
        Random random = new Random();

        // Define los barcos y sus longitudes
        int[] barcosLongitudes = { 5, 4, 3, 2 };

        for (int longitud : barcosLongitudes) {
            boolean colocado = false;

            while (!colocado) {
                int fila = random.nextInt(TAMANO_TABLERO);
                int columna = random.nextInt(TAMANO_TABLERO);
                int orientacion = random.nextInt(2); // 0 para horizontal, 1 para vertical

                if (puedeColocarse(fila, columna, longitud, orientacion)) {
                    colocado = true;

                    for (int i = 0; i < longitud; i++) {
                        if (orientacion == 0) {
                            barcos[fila][columna + i] = 1;
                        } else {
                            barcos[fila + i][columna] = 1;
                        }
                    }
                }
            }
        }
    }
    private boolean puedeColocarse(int fila, int columna, int longitud, int orientacion) {
        if (orientacion == 0) { // Horizontal
            if (columna + longitud > TAMANO_TABLERO) {
                return false; // El barco se sale del tablero
            }
            for (int i = 0; i < longitud; i++) {
                if (barcos[fila][columna + i] == 1) {
                    return false; // Hay un barco en la ruta
                }
            }
        } else { // Vertical
            if (fila + longitud > TAMANO_TABLERO) {
                return false; // El barco se sale del tablero
            }
            for (int i = 0; i < longitud; i++) {
                if (barcos[fila + i][columna] == 1) {
                    return false; // Hay un barco en la ruta
                }
            }
        }
        return true; // El barco puede colocarse en esta posición
    }

    private void onCasillaClick(Button casilla) {
        int fila = -1;
        int columna = -1;
        // Encuentra la fila y columna de la casilla en el GridLayout
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                if (casillas[i][j] == casilla) {
                    fila = i;
                    columna = j;
                    break;
                }
            }
        }

        // Verifica si la casilla ya ha sido disparada
        if (tablero[fila][columna] == CASILLA_GOLPEADA || tablero[fila][columna] == CASILLA_AGUA) {
            mostrarMensaje("Ya has disparado a esta casilla. Intenta de nuevo.",500);
            return;
        }

        // Marca la casilla como golpeada
        if (barcos[fila][columna] == 1) {
            tablero[fila][columna] = CASILLA_GOLPEADA;
            casilla.setBackgroundResource(R.drawable.button_hit); // Cambia el fondo de la casilla a golpeada
            barcosRestantes--;
            mostrarMensaje("¡Has golpeado un barco!",500);

            // Verifica si se han hundido todos los barcos
            if (barcosRestantes == 0) {
                mostrarMensaje("¡Ganaste! Has hundido todos los barcos.",5000);
                // Puedes reiniciar el juego o realizar alguna otra acción aquí
            }
        } else {
            tablero[fila][columna] = CASILLA_AGUA;
            casilla.setBackgroundResource(R.drawable.button_water); // Cambia el fondo de la casilla a agua
            mostrarMensaje("¡Agua!", 500);
            Intent intent = new Intent(this, TabletoDos.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        counter++;
        updateCounterTextView();
    }

    private void mostrarMensaje(String mensaje, int duracionEnMilisegundos) {
        final Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel(); // Esto ocultará el Toast después del tiempo especificado
            }
        }, duracionEnMilisegundos);
    }

    private void updateCounterTextView() {
        counterTextView.setText(String.valueOf(counter));
    }

    public void cerrarAplicacion(View view) {
        finishAffinity(); // Cierra la actividad actual y, por lo tanto, la aplicación
    }





}
