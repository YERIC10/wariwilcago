package com.example.wariwilca_go.ui.recorrido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.wariwilca_go.R;

public class Recorrido_Entrada extends AppCompatActivity {

    ImageButton btn_adelante, btn_atras, btn_derecha, btn_izquierda;
    ImageView img_fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido_entrada);

        img_fondo = findViewById(R.id.imgFondo);
        img_fondo.setImageResource(R.drawable.img_0);

        btn_adelante = findViewById(R.id.btn_adelante_recorrido);
        btn_adelante.setOnClickListener(new View.OnClickListener() {
            Integer c=0;
            @Override
            public void onClick(View v) {
                for(int i=0; i<=26; i++ ){
                    if (c%25==0){ img_fondo.setImageResource(R.drawable.img_1);}
                    if (c%25==1){ img_fondo.setImageResource(R.drawable.img_3);}
                    if (c%25==2){ img_fondo.setImageResource(R.drawable.img_4);}
                    if (c%25==3){ img_fondo.setImageResource(R.drawable.img_5);}
                    if (c%25==4){ img_fondo.setImageResource(R.drawable.img_6);}
                    if (c%25==5){ img_fondo.setImageResource(R.drawable.img_24);}
                    if (c%25==6){ img_fondo.setImageResource(R.drawable.img_25);}
                    if (c%25==7){ img_fondo.setImageResource(R.drawable.img_6);}
                    if (c%25==8){ img_fondo.setImageResource(R.drawable.img_18);}
                    if (c%25==9){ img_fondo.setImageResource(R.drawable.img_19);}
                }
                c++;
            }
        });

        btn_derecha = findViewById(R.id.btn_derecha_recorrido);
        btn_derecha.setOnClickListener(new View.OnClickListener() {
            Integer c=0;
                @Override
                public void onClick(View v) {
                    for(int i=0; i<=26; i++ ){
                        if(c%25==0){img_fondo.setImageResource(R.drawable.img_7);}
                        if(c%25==1){img_fondo.setImageResource(R.drawable.img_20);}
                        if(c%25==2){img_fondo.setImageResource(R.drawable.img_11);}
                        if(c%25==3){ img_fondo.setImageResource(R.drawable.img_12);}
                        if(c%25==4){ img_fondo.setImageResource(R.drawable.img_14);}
                        if(c%25==5){ img_fondo.setImageResource(R.drawable.img_21);}
                    }
                    c++;
                }
        });
        btn_izquierda = findViewById(R.id.btn_izquierda_recorrido);
        btn_izquierda.setOnClickListener(new View.OnClickListener() {
            Integer c=0;
            @Override
            public void onClick(View v) {
                for(int i=0; i<=26; i++ ){
                    if(c%25==0){img_fondo.setImageResource(R.drawable.img_9);}
                    if(c%25==1){img_fondo.setImageResource(R.drawable.img_10);}
                    if(c%25==2){ img_fondo.setImageResource(R.drawable.img_15);}
                    if(c%25==3){ img_fondo.setImageResource(R.drawable.img_16);}
                    if(c%25==4){ img_fondo.setImageResource(R.drawable.img_17);}
                    if(c%25==5){ img_fondo.setImageResource(R.drawable.img_22);}
                    if(c%25==6){ img_fondo.setImageResource(R.drawable.img_23);}
                }
                c++;
            }
        });
    }
}