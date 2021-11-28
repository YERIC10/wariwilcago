package com.example.wariwilca_go.ui.recorrido;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.wariwilca_go.R;


public class recorridoFragment extends Fragment {

    Button btn_centro, btn_SupDer, btn_SupIzq, btn_InfDer, btn_InfIzq, btn_Entrada;
    ImageView ing_Fondo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_recorrido, container, false);

        ing_Fondo = vista.findViewById(R.id.imgFondo);

        btn_centro = vista.findViewById(R.id.btnCentro);
        btn_centro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "CENTRO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoCentroFragment.class);
                startActivity(i);
            }
        });

        btn_Entrada = vista.findViewById(R.id.btnEntrada);
        btn_Entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ENTRADA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoEntradaFragment.class);
                startActivity(i);
            }
        });

        btn_SupIzq = vista.findViewById(R.id.btnSupIzq);
        btn_SupIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "SUPERIOR IZQUIERDO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoSupIzqFragment.class);
                startActivity(i);
            }
        });

        btn_SupDer = vista.findViewById(R.id.btnSupDer);
        btn_SupDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "SUPERIOR DERECHO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoSupDerFragment.class);
                startActivity(i);
            }
        });

        btn_InfDer = vista.findViewById(R.id.btnInfDer);
        btn_InfDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "INFERIOR DERECHO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoInfDerFragment.class);
                startActivity(i);
            }
        });

        btn_InfIzq = vista.findViewById(R.id.btnInfIzq);
        btn_InfIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "INFERIOR IZQUIERDO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), recorridoInfIzqFragment.class);
                startActivity(i);
            }
        });
        return vista;
    }
}
