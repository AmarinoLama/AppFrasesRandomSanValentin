package com.example.regalolely;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;
import java.util.Arrays;
import java.util.List;

public class VentanaPrincipal extends AppCompatActivity {

    private FraseDao fraseDao;
    private TextView textView, txtContrasena;
    private MediaPlayer mediaPlayer;
    private Toolbar toolbar;
    private Button btnMenu, btnMostrar;

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

        cargarFrases();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtContrasena.getText().toString().equals("amarinolama")) {
                    Intent intent = new Intent(VentanaPrincipal.this, VentanaCrud.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(VentanaPrincipal.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    txtContrasena.setText("");
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frase frase = fraseDao.getRandomFrase();
                StringBuilder sb = new StringBuilder();
                sb.append(frase.getFrase());
                textView.setText(sb.toString());
            }
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
        txtContrasena = findViewById(R.id.txtContrasena);
        btnMenu = findViewById(R.id.btnMenu);
        btnMostrar = findViewById(R.id.btnMostrar);
        toolbar = findViewById(R.id.toolbar);
    }

    private void cargarFrases() {
        List<Frase> frases = Arrays.asList(
                new Frase("La vida es bella, pero más vella es esta paella"),
                new Frase("Eres la más guapa del mundo 🎃"),
                new Frase("Me da igual lo que te pongas porque siempre estás guapa, incluso cunando no llevas nada"),
                new Frase("Eres la mejor persona que he conocido en mi vida"),
                new Frase("Eres la mejor amiga que alguien podría tener"),
                new Frase("Eres la mejor novia incluso cuando te dan ataques de esquizofrenia"),
                new Frase("Serás la mejor madre de todas, porque tienes al mejor novio del mundo jejejej"),
                new Frase("Te amo hasta el infinito y más allá ♾"),
                new Frase("Por mucho que me quieras yo te querré más"),
                new Frase("Siempre estaré a tu lado, pase lo que pase"),
                new Frase("Eres la mejor persona que he conocido en mi vida"),
                new Frase("Me encantaría pasar el resto de mi vida contigo"),
                new Frase("Recuerda que los momentos difíciles solo son una pequeña parte del camino hacia la felicidad. 🌈✨"),
                new Frase("Eres más fuerte de lo que crees, y cada día das pasos más grandes hacia tu bienestar. 💪❤️"),
                new Frase("No estás sola en esto, siempre estoy aquí para apoyarte. Juntos todo es más fácil. 🤗💖"),
                new Frase("El sol siempre sale después de la tormenta, y tu luz brilla más que nunca. 🌞🌸"),
                new Frase("Respira profundamente, la calma está dentro de ti. 🧘‍♀️🌿"),
                new Frase("Tus ojos reflejan una fuerza infinita, no dejes que nada te haga dudar de ti misma. 👀✨"),
                new Frase("Hoy es un buen día para empezar de nuevo, con una sonrisa como la tuya. 😊🌟"),
                new Frase("Cada paso que das es una victoria, y con tu alegría, todo es posible. 🏅💖"),
                new Frase("Tú eres la razón de tantas sonrisas, no olvides lo increíble que eres. 😘💫"),
                new Frase("Las nubes se disipan, y tu sonrisa siempre ilumina el día. 🌤️💛")
        );

        for (Frase frase : frases) {
            fraseDao.insert(frase);
        }
    }
}
