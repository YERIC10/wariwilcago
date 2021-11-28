package com.example.wariwilca_go.ui.recorrido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.wariwilca_go.R;

public class recorridoEntradaFragment extends Fragment {

    ImageButton btn_atras, btn_adelante, btn_izquierda, btn_derecha;
    ImageView Fondo_recorrido;
    Integer contador=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_recorrido_entrada, container, false);
        Fondo_recorrido = vista.findViewById(R.id.img_entrada_fondo);
        Fondo_recorrido.setImageResource(R.drawable.img_0);

        btn_adelante = vista.findViewById(R.id.btnAdelante_entrada);
        btn_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i > 4; i++ ){
                    if(contador%5==0){
                        Fondo_recorrido.setImageResource(R.drawable.img_1);
                    }
                    if(contador%5==1){
                        Fondo_recorrido.setImageResource(R.drawable.img_3);
                    }
                    if(contador%5==2){
                        Fondo_recorrido.setImageResource(R.drawable.img_4);
                    }
                    if(contador%5==3){
                        Fondo_recorrido.setImageResource(R.drawable.img_5);
                    }
                    if(contador%5==4){
                        Fondo_recorrido.setImageResource(R.drawable.img_6);
                    }
                    contador++;
                }
            }
        });

        btn_atras = vista.findViewById(R.id.btnAtras_entrada);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i > 4; i++ ){
                    if(contador%5==0){
                        Fondo_recorrido.setImageResource(R.drawable.img_1);
                    }
                    if(contador%5==1){
                        Fondo_recorrido.setImageResource(R.drawable.img_3);
                    }
                    if(contador%5==2){
                        Fondo_recorrido.setImageResource(R.drawable.img_4);
                    }
                    if(contador%5==3){
                        Fondo_recorrido.setImageResource(R.drawable.img_5);
                    }
                    if(contador%5==4){
                        Fondo_recorrido.setImageResource(R.drawable.img_6);
                    }
                    contador++;
                }
            }
        });

        btn_derecha = vista.findViewById(R.id.btnDerecha_entrada);
        btn_derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_izquierda = vista.findViewById(R.id.btnIzquierda_entrada);
        btn_izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return vista;
    }
}
