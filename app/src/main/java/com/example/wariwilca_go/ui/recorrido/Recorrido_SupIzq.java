package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_SupIzq extends AppCompatActivity {
    ImageView img_supizq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_sup_izq);

        img_supizq = findViewById(R.id.imgsupder);
    }
}