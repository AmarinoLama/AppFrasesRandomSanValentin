package com.example.regalolely;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class Activity2 extends AppCompatActivity {

    private AppDatabase db;
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
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "frases-database")
                .allowMainThreadQueries() // No se recomienda en producción
                .build();
        fraseDao = db.fraseDao();

        btnInsertar = findViewById(R.id.btnInsertar);
        btnVolver = findViewById(R.id.btnVolver);
        btnBorrarTodo = findViewById(R.id.btnBorrarTodo);
        etFrase = findViewById(R.id.etFrase);
        cbBorrartodo = findViewById(R.id.cbBorrartodo);

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
}