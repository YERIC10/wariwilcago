package com.example.wariwilca_go.ui.visita;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.location.GnssAntennaInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wariwilca_go.MainActivity;
import com.example.wariwilca_go.R;
import com.example.wariwilca_go.model.Solicitud;
import com.example.wariwilca_go.util.JavaMailAPI;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VisitaFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    Button btbnFecha,btnHora, btnVisita;
    TextView txtFecha, txtHora;
    Spinner ctdPersonas;
    private int dia, mes, ano, hora, minuto;
    ArrayList listado = new ArrayList();
    String cantidadPersonas;

    public static VisitaFragment newInstance(String param1, String param2) {
        VisitaFragment fragment = new VisitaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CANTIDAD DE PERSONAS
        listado.add("1");
        listado.add("2");
        listado.add("3");
        listado.add("4");
        listado.add("5");
        listado.add("6");

        //OBTENER ID
        btnHora = getView().findViewById(R.id.btnHora);
        btbnFecha = getView().findViewById(R.id.btnFecha);
        btnVisita = getView().findViewById(R.id.btnSoliVisita);
        txtFecha = getView().findViewById(R.id.visitFecha);
        txtHora = getView().findViewById(R.id.visitHora);
        ctdPersonas = getView().findViewById(R.id.ctdPersonas2);

        //CARGAR SPINNER
        ArrayAdapter adaptadorCantidad = new ArrayAdapter(getActivity(), R.layout.spinner_items, listado);
        ctdPersonas.setAdapter(adaptadorCantidad);

        //EVENTO DE CLICK
        btnHora.setOnClickListener(this::onClick);
        btbnFecha.setOnClickListener(this::onClick);
        btnVisita.setOnClickListener(this::onClick);

        //EVENTO DE SELECCION DE ITEM
        ctdPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                cantidadPersonas = ctdPersonas.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                showDatePickerDialog();
                break;
            case R.id.btnHora:
                showTimePickerDialog();
                break;
            case R.id.btnSoliVisita:
                if ("".equals(txtFecha) || txtFecha.getVisibility() != View.VISIBLE) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("ERROR").setContentText("Por favor seleccione una fecha.").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
                }else if("".equals(txtHora) || txtHora.getVisibility() != View.VISIBLE){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("ERROR").setContentText("Por favor seleccione una hora.").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
                }else{
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("¿Estás seguro?").setContentText("Su solicitud se enviará en un momento.").setCancelText("NO").setCancelButtonBackgroundColor(R.color.Brow300).setConfirmText("SI").setConfirmButtonBackgroundColor(R.color.Brow300).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
                    public void onClick(SweetAlertDialog sDialog) {
                        enviarGmail();
                        sDialog.cancel();
                    }
                    }).show();
                }
                break;
            default:
                break;
        }
    }

    public void showTimePickerDialog(){
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
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        txtFecha.setVisibility(View.VISIBLE);
    }

    private void enviarGmail() {
        //OBTENER DATOS
        String mail = MainActivity.Global.playerEmail;
        String subject = "Solicitud de Visita";
        String texto = txtFecha.getText().toString() + " a las " + txtHora.getText().toString() + " para " + cantidadPersonas + " personas";
        String message = "Buen día, me gustaría solicitar una visita a Wariwilca el " + texto;
        //ENVIAR EMAIL
        JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), mail, subject, message);
        javaMailAPI.execute();
        LimpiarDatosVisita();
    }

    private void LimpiarDatosVisita() {
        txtHora.setText("");
        txtFecha.setText("");
    }

}