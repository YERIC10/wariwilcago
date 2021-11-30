package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_Centro extends AppCompatActivity {
    ImageView img_centro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_centro);

        img_centro = findViewById(R.id.imgcentro);

    }
}