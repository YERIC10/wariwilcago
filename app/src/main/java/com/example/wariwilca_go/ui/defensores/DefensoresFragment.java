package com.example.wariwilca_go.ui.defensores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wariwilca_go.MainActivity;
import com.example.wariwilca_go.R;
import com.example.wariwilca_go.databinding.ActivityMainBinding;
import com.example.wariwilca_go.model.Solicitud;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DefensoresFragment extends Fragment implements View.OnClickListener {

    Button btnSolicitudDefensores;
    TextView edtDni, edtEdad, edtUbi;
    String dtDni, dtedad, dtUbi;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_defensores, container, false);
        iniciarFirebase();
        return rootView;
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edtDni =  getView().findViewById(R.id.defDNI);
        edtEdad =  getView().findViewById(R.id.defEdad);
        edtUbi =  getView().findViewById(R.id.defResidencia);
        btnSolicitudDefensores =  getView().findViewById(R.id.btnSoliDef);
        btnSolicitudDefensores.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        dtDni = edtDni.getText().toString();
        dtedad = edtEdad.getText().toString();
        dtUbi = edtUbi.getText().toString();

        switch (view.getId()) {
            case R.id.btnSoliDef:

                if ("".equals(dtDni)) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("ERROR").setContentText("Por favor ingrese su número de DNI").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
                }else if("".equals(dtedad)){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("ERROR").setContentText("Por favor ingrese su edad").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
                }else if("".equals(dtUbi)){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("ERROR").setContentText("Por favor ingrese su ubicación").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
                }else{
                    EnviarDatos();
                }
                break;
            default:
                break;
        }
    }

    private void EnviarDatos() {

        String playerName = MainActivity.Global.playerName;
        String playerEmail = MainActivity.Global.playerEmail;


        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("¿Estás seguro?").setContentText("Su solicitud se enviará en un momento.").setCancelText("NO").setCancelButtonBackgroundColor(R.color.Brow300).setConfirmText("SI").setConfirmButtonBackgroundColor(R.color.Brow300).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            // s.setsID(UUID.randomUUID().toString());
            Solicitud s = new Solicitud();
            String emailGuion= playerEmail.replace('.', '-');
            String idSolicitud = emailGuion+dtDni;
            s.setsID(idSolicitud);
            s.setDNI(dtDni);
            s.setEdad(dtedad);
            s.setNombre(playerName);
            s.setEmail(playerEmail);
            s.setUbicacion(dtUbi);
            databaseReference.child("Solicitud").child(s.getsID()).setValue(s);
            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡BUEN TRABAJO!").setContentText("Su solicitud se envió correctamente.").setConfirmText("OK").setConfirmButtonBackgroundColor(R.color.Brow300).show();
            LimpiarDatos();
            sDialog.cancel();
        }
        }).show();
    }

    private void LimpiarDatos() {
        edtDni.setText("");
        edtEdad.setText("");
        edtUbi.setText("");
    }
}