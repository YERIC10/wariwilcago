package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_SupDer extends AppCompatActivity {
    ImageView img_supder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_sup_der);

        img_supder = findViewById(R.id.imgsupder);


    }
}