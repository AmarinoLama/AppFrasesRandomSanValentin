package com.example.regalolely.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.regalolely.conexion.AppDatabase;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.R;
import com.example.regalolely.conexion.model.Frase;

public class Activity2 extends AppCompatActivity {

    private FraseDao fraseDao;

    private Button btnVolver, btnInsertar, btnBorrarTodo;
    private EditText etFrase;
    private CheckBox cbBorrartodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar la base de datos
        Conexion.runBBDD(this);
        fraseDao = Conexion.getFraseDao();

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, MainActivity.class);
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
        btnInsertar = findViewById(R.id.btnInsertar);
        btnVolver = findViewById(R.id.btnVolver);
        btnBorrarTodo = findViewById(R.id.btnBorrarTodo);
        etFrase = findViewById(R.id.etFrase);
        cbBorrartodo = findViewById(R.id.cbBorrartodo);
    }
}