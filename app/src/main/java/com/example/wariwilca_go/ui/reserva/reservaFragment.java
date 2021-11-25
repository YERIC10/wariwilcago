package com.example.wariwilca_go.ui.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.wariwilca_go.ui.registrar.registrarFragment;

public class reservaFragment extends Fragment {
    public static reservaFragment newInstance(String param1, String param2) {
        reservaFragment fragment = new reservaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
}
