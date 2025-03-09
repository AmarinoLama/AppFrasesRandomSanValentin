package com.example.regalolely.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.regalolely.menu.MenuHandler;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

import java.util.Arrays;
import java.util.List;

public class VentanaCrud extends AppCompatActivity {

    private FraseDao fraseDao;

    private Toolbar toolbar;
    private Button btnVolver, btnInsertar, btnBorrarTodo, btn_packFrases1, btn_packFrases2;
    private EditText etFrase;
    private CheckBox cbBorrartodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_crud);

        inicializar();

        // Inicializar el menú
        setSupportActionBar(toolbar);
        addMenuProvider(new MenuHandler(this));

        // Inicializar la base de datos
        Conexion.runBBDD(this);
        fraseDao = Conexion.getFraseDao();

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(VentanaCrud.this, VentanaPrincipal.class);
            startActivity(intent);
        });

        btnInsertar.setOnClickListener(v -> {
            if (etFrase.getText().toString().isEmpty()) {
                Toast.makeText(this, "Frase vacía", Toast.LENGTH_SHORT).show();
            } else {
                Frase frase = new Frase(etFrase.getText().toString());
                fraseDao.insert(frase);
            }
            etFrase.setText("");
        });

        cbBorrartodo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnBorrarTodo.setEnabled(isChecked);
        });

        btnBorrarTodo.setOnClickListener(v -> {
            fraseDao.deleteAllFrases();
        });

        btn_packFrases1.setOnClickListener(v -> {
            cargarPackFrases1();
        });

        btn_packFrases2.setOnClickListener(v -> {
            Toast.makeText(this, "Próximamente", Toast.LENGTH_SHORT).show();
        });
    }

    private void inicializar() {
        toolbar = findViewById(R.id.toolbar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnVolver = findViewById(R.id.btnVolver);
        btnBorrarTodo = findViewById(R.id.btnBorrarTodo);
        etFrase = findViewById(R.id.etFrase);
        cbBorrartodo = findViewById(R.id.cbBorrartodo);
        btn_packFrases1 = findViewById(R.id.btn_packFrases1);
        btn_packFrases2 = findViewById(R.id.btn_packFrases2);
    }

    private void cargarPackFrases1() {
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