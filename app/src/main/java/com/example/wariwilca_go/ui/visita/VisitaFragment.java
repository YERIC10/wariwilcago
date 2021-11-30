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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wariwilca_go.MainActivity;
import com.example.wariwilca_go.R;
import com.example.wariwilca_go.util.JavaMailAPI;

import java.util.ArrayList;
import java.util.Calendar;

public class VisitaFragment extends Fragment implements View.OnClickListener {

    Button btbnFecha,btnHora, btnVisita;
    TextView txtFecha, txtHora;
    ListView listado2;
    private int dia, mes, ano, hora, minuto;
    ArrayList listado = new ArrayList();

    public static VisitaFragment newInstance(String param1, String param2) {
        VisitaFragment fragment = new VisitaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        //CANTIDAD DE PERSONAS
        listado.add("1");
        listado.add("2");
        listado.add("3");
        listado.add("4");
        listado.add("5");
        listado.add("6");

        super.onActivityCreated(savedInstanceState);
        btnHora = getView().findViewById(R.id.btnHora);
        btbnFecha = getView().findViewById(R.id.btnFecha);
        btnVisita = getView().findViewById(R.id.btnSoliVisita);
        txtFecha = getView().findViewById(R.id.visitFecha);
        txtHora = getView().findViewById(R.id.visitHora);
        listado2 = getView().findViewById(R.id.ctdPersonas);

        ArrayAdapter adaptadorCantidad = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listado);

        listado2.setAdapter(adaptadorCantidad);
        btnHora.setOnClickListener(this::onClick);
        btbnFecha.setOnClickListener(this::onClick);
        btnVisita.setOnClickListener(this::onClick);
        seleccionaCantidad();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita, container, false);
    }

    private void seleccionaCantidad(){
        listado2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mensaje = (String) listado.get(i);
                Toast.makeText(getActivity(), mensaje,Toast.LENGTH_SHORT).show();
            }
        });
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
            case R.id.btnSoliVisita:

                if ("".equals(txtFecha)) {
                    Toast.makeText(getActivity(), "Seleccione un DIA", Toast.LENGTH_SHORT).show();
                }else if("".equals(txtHora)){
                    Toast.makeText(getActivity(), "Seleccione una FECHA", Toast.LENGTH_SHORT).show();
                }else{
                    enviarGmail();
                    LimpiarDatosVisita();
                }
                break;
            default:
                break;
        }
    }

    private void LimpiarDatosVisita() {
        txtHora.setText("");
        txtFecha.setText("");
    }

    private void enviarGmail() {
        String mail = MainActivity.Global.playerEmail;
        String subject = "Solicitud de Visita";
        String texto = txtFecha.getText().toString() + " a las " + txtHora.getText().toString();
        String message = "Buen día, me gustaría solicitar una visita a Wariwilca el " + texto;
        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), mail, subject, message);
        javaMailAPI.execute();
    }
}