package com.example.wariwilca_go.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wariwilca_go.MainActivity;
import com.example.wariwilca_go.R;
import com.example.wariwilca_go.databinding.FragmentHomeBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

public class HomeFragment extends Fragment implements View.OnClickListener {

    YouTubePlayerView video;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    Button btnaccedercuenta;
    private com.example.wariwilca_go.databinding.FragmentHomeBinding binding1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        btnaccedercuenta = binding.btnHomeAcceso;
        video = binding.videoFraghome;
        getLifecycle().addObserver(video);
        reproducirVideo();

        btnaccedercuenta.setOnClickListener(this);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void reproducirVideo() {

        video.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializeYouTubePlayer) {
                initializeYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoURL = "igt22WpLuF0";
                        initializeYouTubePlayer.loadVideo(videoURL, 0);
                    }
                });
            }
        }, true);
    }

    public void mostrarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        builder.setIcon(R.drawable.ic_home_bienvenida);
        builder.setTitle("!Te Damos la Bienvenida¡");
        builder.setMessage("Accede a tu cuenta para guardar tus puntuaciones")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Acceso", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "ACCEDIENDO", Toast.LENGTH_SHORT).show();
            }
        })
        .setCancelable(false)
        .show();
    }

    @Override
    public void onClick(View v) {
        mostrarDialog();
    }
}