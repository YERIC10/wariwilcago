package com.example.wariwilca_go.ui.visita;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wariwilca_go.R;

import java.util.Calendar;

public class VisitaFragment extends Fragment implements View.OnClickListener {

    Button btbnFecha,btnHora;
    TextView txtFecha, txtHora;
    private int dia, mes, ano, hora, minuto;


    public static VisitaFragment newInstance(String param1, String param2) {
        VisitaFragment fragment = new VisitaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnHora = getView().findViewById(R.id.btnHora);
        btbnFecha = getView().findViewById(R.id.btnFecha);
        txtFecha = getView().findViewById(R.id.visitFecha);
        txtHora = getView().findViewById(R.id.visitHora);
        btnHora.setOnClickListener(this::onClick);
        btbnFecha.setOnClickListener(this::onClick);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFecha:
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtFecha.setText(i2+"/"+(i1+1)+"/"+i);
                        txtFecha.setVisibility(View.VISIBLE);
                    }
                },dia,mes,ano);
                datePickerDialog.show();
                break;
            case R.id.btnHora:
                final Calendar ca = Calendar.getInstance();
                hora = ca.get(Calendar.HOUR);
                minuto = ca.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtHora.setText(i+":"+i1);
                        txtHora.setVisibility(View.VISIBLE);
                    }
                },hora, minuto, false);
                timePickerDialog.show();
                break;
            case R.id.btnSoliDef:

                break;
            default:
                break;
        }
    }
}