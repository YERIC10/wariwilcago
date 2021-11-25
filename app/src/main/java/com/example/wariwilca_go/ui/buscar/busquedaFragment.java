package com.example.wariwilca_go.ui.buscar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class busquedaFragment extends Fragment {

    public static busquedaFragment newInstance(String param1, String param2) {
        busquedaFragment fragment = new busquedaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
}
