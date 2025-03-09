package com.example.regalolely.editPreferences;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.regalolely.Helpers;
import com.example.regalolely.menu.MenuHandler;
import com.example.regalolely.R;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.List;

public class EditPreferences extends AppCompatActivity {

    private Toolbar tb;
    private Button btn_escaner;
    private TextView txt_salidaEscaner;

    private String[] resultadoEscaner = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializar();

        Helpers.inicializarQRLauncher(this, resultadoEscaner, result -> {
            resultadoEscaner[0] = result;
            System.out.println("Salida escaner: " + resultadoEscaner[0]);
            txt_salidaEscaner.setText(resultadoEscaner[0]);
        });

        btn_escaner.setOnClickListener(v -> {
            Helpers.scanearQR();
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences_container, new PreferenceFragment())
                .commit();
    }

    private void inicializar() {
        btn_escaner = findViewById(R.id.btn_escaner);
        txt_salidaEscaner = findViewById(R.id.txt_salidaEscaner);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        addMenuProvider(new MenuHandler(this));
    }
}