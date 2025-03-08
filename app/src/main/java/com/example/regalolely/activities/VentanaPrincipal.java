package com.example.regalolely.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.example.regalolely.MenuHandler;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

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
    protected void onDestroy() {
        super.onDestroy();
        // Detén el sonido y libera recursos cuando la actividad se destruya
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void inicializar() {
        textView = findViewById(R.id.frase);
        btnMostrar = findViewById(R.id.btnMostrar);
        toolbar = findViewById(R.id.toolbar);
    }
}
