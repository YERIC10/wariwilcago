package com.example.wariwilca_go.ui.recorrido;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.wariwilca_go.ui.buscar.busquedaFragment;

public class recorridoFragment extends Fragment {

    public static recorridoFragment newInstance(String param1, String param2) {
        recorridoFragment fragment = new recorridoFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
}
