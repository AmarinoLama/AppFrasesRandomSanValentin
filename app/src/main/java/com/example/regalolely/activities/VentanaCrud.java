package com.example.regalolely.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.regalolely.MenuHandler;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

public class VentanaCrud extends AppCompatActivity {

    private FraseDao fraseDao;

    private Toolbar toolbar;
    private Button btnVolver, btnInsertar, btnBorrarTodo;
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
    }

    private void inicializar() {
        toolbar = findViewById(R.id.toolbar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnVolver = findViewById(R.id.btnVolver);
        btnBorrarTodo = findViewById(R.id.btnBorrarTodo);
        etFrase = findViewById(R.id.etFrase);
        cbBorrartodo = findViewById(R.id.cbBorrartodo);
    }
}