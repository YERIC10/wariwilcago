package com.example.wariwilca_go;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.wariwilca_go.model.Solicitud;
import com.example.wariwilca_go.model.Usuario;
import com.example.wariwilca_go.ui.qr.BusquedaFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrscanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView scannerView;
    DatabaseReference dbref, dbrefUser;
    String titulo, info, fecha;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Integer puntos;
    String puntitos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        dbref= FirebaseDatabase.getInstance().getReference("Informacion");
        dbrefUser= FirebaseDatabase.getInstance().getReference("Usuario");

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                    }
                }).check();

        iniciarFirebase();
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void handleResult(Result rawResult) {

        String data=rawResult.getText().toString();
        String prueba1= data.replace('.', '-');
        String prueba2= prueba1.replace('#', '-');
        String prueba3= prueba2.replace('$', '-');
        String prueba4= prueba3.replace('[', '-');
        String prueba5= prueba4.replace(']', '-');

        dbref.child(prueba5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String descargarPhoto = snapshot.child("img").getValue().toString();
                    Glide.with(BusquedaFragment.imagen.getContext()).load(descargarPhoto).into(BusquedaFragment.imagen);
                    titulo = snapshot.child("titulo").getValue().toString();
                    info = snapshot.child("info").getValue().toString();
                    fecha = snapshot.child("fecha").getValue().toString();
                    puntos = 100;
                    //SET INFO
                    BusquedaFragment.tituloQR.setText(titulo);
                    BusquedaFragment.infoQR.setText(info);
                    BusquedaFragment.anoQR.setText(fecha);
                    //SET VISIBILITY
                    BusquedaFragment.infoB.setVisibility(View.GONE);
                    BusquedaFragment.tituloQR.setVisibility(View.VISIBLE);
                    BusquedaFragment.infoQR.setVisibility(View.VISIBLE);
                    BusquedaFragment.anoQR.setVisibility(View.VISIBLE);
                    //SET USER
                    String playerName = MainActivity.Global.playerName;
                    String playerEmail = MainActivity.Global.playerEmail;
                    String emailGuion= playerEmail.replace('.', '-');

                    dbrefUser.child(emailGuion).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                puntitos = snapshot.child("puntos").getValue().toString();
                                validarPuntos(emailGuion, prueba5);
                            }else{
                                Usuario u = new Usuario();
                                String emailGuion2= playerEmail.replace('.', '-');
                                u.setuID(emailGuion2);
                                u.setEmail(playerEmail);
                                u.setNombre(playerName);
                                u.setPuntos(puntos.toString());
                                databaseReference.child("Usuario").child(u.getuID()).setValue(u);
                                databaseReference.child("Usuario").child(u.getuID()).child("QR").child(prueba5).setValue("YES");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }else{
                    BusquedaFragment.tituloQR.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onBackPressed();
    }

    private void validarPuntos(String em, String datoQR){
        dbrefUser.child(em).child("QR").child(datoQR).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                }else{
                    String playerEmail = MainActivity.Global.playerEmail;
                    Integer puntosFinales = Integer.parseInt(puntitos) + 100;
                    Usuario u = new Usuario();
                    String emailGuion2= playerEmail.replace('.', '-');
                    u.setuID(emailGuion2);
                    u.setPuntos(puntosFinales.toString());

                    HashMap hashMap = new HashMap();
                    hashMap.put("puntos",puntosFinales);
                    databaseReference.child("Usuario").child(em).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(qrscanner.this, "SI", Toast.LENGTH_LONG).show();
                        }
                    });

                    databaseReference.child("Usuario").child(u.getuID()).child("QR").child(datoQR).setValue("YES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}