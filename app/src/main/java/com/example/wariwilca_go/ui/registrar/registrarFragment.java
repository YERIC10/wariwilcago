package com.example.wariwilca_go.ui.registrar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.wariwilca_go.ui.recorrido.recorridoFragment;

public class registrarFragment extends Fragment {

    public static registrarFragment newInstance(String param1, String param2) {
        registrarFragment fragment = new registrarFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
}
