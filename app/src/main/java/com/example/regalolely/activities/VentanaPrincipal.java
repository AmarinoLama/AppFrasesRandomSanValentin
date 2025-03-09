package com.example.regalolely.activities;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.regalolely.menu.MenuHandler;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class VentanaPrincipal extends AppCompatActivity {

    private FraseDao fraseDao;
    private TextView textView;
    private MediaPlayer mediaPlayer;
    private Toolbar toolbar;
    private Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_principal);

        inicializar();

        // Configurar el menú principal
        setSupportActionBar(toolbar);
        addMenuProvider(new MenuHandler(this));

        // Inicializar la base de datos
        Conexion.runBBDD(this);
        fraseDao = Conexion.getFraseDao();

        // Inicializa el MediaPlayer con el archivo de sonido y lo reproduce
        mediaPlayer = MediaPlayer.create(this, R.raw.musica);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        btnMostrar.setOnClickListener((view) -> {
            Frase frase = fraseDao.getRandomFrase();
            if (frase == null) {
                Toast.makeText(VentanaPrincipal.this, "No hay frases", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(frase.getFrase());
            textView.setText(sb.toString());
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausa el sonido cuando la actividad se pausa
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    private void inicializar() {
        textView = findViewById(R.id.frase);
        btnMostrar = findViewById(R.id.btnMostrar);
        toolbar = findViewById(R.id.toolbar);
    }
}
