package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_InfIzq extends AppCompatActivity {

    ImageView Img_fondo_Inf_izq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_inf_izq);

        Img_fondo_Inf_izq = findViewById(R.id.img_infdizq);
    }
}