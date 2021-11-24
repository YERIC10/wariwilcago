package com.example.wariwilca_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_inicio);

        Timer tiempo;
        TimerTask carga = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Inicio.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        tiempo = new Timer();
        tiempo.schedule(carga, 5000);
    }
}