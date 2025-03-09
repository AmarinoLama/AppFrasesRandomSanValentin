package com.example.regalolely.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.regalolely.Helpers;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;
import com.example.regalolely.menu.MenuHandler;
import com.example.regalolely.viewModels.FraseViewModel;

public class EditarFrase extends AppCompatActivity {

    public static final String ID_FRASE = "id_frase";

    private TextView txt_frase, txt_autor, txt_idFrase;
    private Button btn_asignarme, btn_volver;
    private EditText edt_frase;
    private Toolbar toolbar;

    private FraseDao fraseDao;

    private Frase fraseCargada;
    private FraseViewModel fraseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editar_frase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar la base de datos
        Conexion.runBBDD(this);
        fraseDao = Conexion.getFraseDao();

        inicializar();

        edt_frase.setOnEditorActionListener((v, actionId, event) -> {
            String newFrase = edt_frase.getText().toString();
            fraseCargada.setFrase(newFrase);
            fraseDao.updateFrase(fraseCargada);
            cargarVM();
            return true;
        });

        cargarVM();

        SOL();
    }

    private void inicializar() {
        txt_autor = findViewById(R.id.txt_autor);
        txt_frase = findViewById(R.id.txt_frase);
        txt_idFrase = findViewById(R.id.txt_idFrase);
        btn_asignarme = findViewById(R.id.btn_asignarme);
        btn_volver = findViewById(R.id.btn_volver);
        edt_frase = findViewById(R.id.edt_frase);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addMenuProvider(new MenuHandler(this));
    }

    private void SOL() {
        btn_asignarme.setOnClickListener(v -> {
            String user = Helpers.getUserNameEncrypted(this);
            fraseCargada.setAutor(user);
            fraseDao.updateFrase(fraseCargada);
            cargarVM();
        });

        btn_volver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListadoFrases.class);
            startActivity(intent);
        });
    }

    private void cargarVM() {
        fraseViewModel = new ViewModelProvider(this).get(FraseViewModel.class);

        fraseViewModel.getFrase().observe(this, new Observer<Frase>() {
            @Override
            public void onChanged(Frase frase) {
                if (frase != null) {
                    cargarFrase(frase);
                }
            }
        });

        SharedPreferences sp = getSharedPreferences(Login.SHARED_PREFERENCES, MODE_PRIVATE);
        int idFrase = sp.getInt(ID_FRASE, -1);
        if (idFrase != -1) {
            Frase frase = fraseDao.getFrase(idFrase);
            fraseViewModel.setFrase(frase);
        }
    }

    public void cargarFrase(Frase frase) {
        txt_idFrase.setText(String.valueOf(frase.getId()));
        txt_frase.setText(frase.getFrase());
        txt_autor.setText(frase.getAutor());
        edt_frase.setText(frase.getFrase());

        fraseCargada = frase;
    }
}