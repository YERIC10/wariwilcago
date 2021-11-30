package com.example.wariwilca_go.ui.qr;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wariwilca_go.R;
import com.example.wariwilca_go.qrscanner;


public class BusquedaFragment extends Fragment implements View.OnClickListener{

    Button botonQR;
    public static TextView tituloQR,infoQR, anoQR, infoB;
    public static ImageView imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        botonQR =  getView().findViewById(R.id.btnQR);
        tituloQR =  getView().findViewById(R.id.tituloQR);
        infoQR =  getView().findViewById(R.id.infoQR);
        anoQR =  getView().findViewById(R.id.anoQR);
        infoB =  getView().findViewById(R.id.infoBusqueda);
        imagen=  getView().findViewById(R.id.imgQR);
        botonQR.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnQR:
                startActivity(new Intent(getActivity(), qrscanner.class));
                break;
            default:
                break;
        }
    }
}