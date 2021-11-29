package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_InfDer extends AppCompatActivity {
    ImageView imginfdere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_inf_der);

        imginfdere = findViewById(R.id.img_infder);
    }
}