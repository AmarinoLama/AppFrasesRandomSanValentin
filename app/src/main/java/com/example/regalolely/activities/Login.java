package com.example.regalolely.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.example.regalolely.R;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Login extends AppCompatActivity {

    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String ENCRYPTED_PREFERENCES = "encryptedPreferences";

    private Button btnLogin;
    private EditText etContrasena, etUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializar();

        btnLogin.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString();
            String contrasena = etContrasena.getText().toString();
            inicioSesion(usuario, contrasena);
        });
    }

    private void inicializar() {
        btnLogin = findViewById(R.id.btnLogin);
        etContrasena = findViewById(R.id.etContrasena);
        etUsuario = findViewById(R.id.etUsuario);
    }

    private void inicioSesion(String usuario, String password) {

        addToEncriptedSharePreferences(usuario);

        if (password.equals("1234")) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("admin", usuario);
            editor.apply();
        }

        Toast.makeText(Login.this, "Bienvenido " + usuario + "!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Login.this, VentanaPrincipal.class);
        startActivity(intent);
    }

    private void addToEncriptedSharePreferences(String userName) {
        try {
            MasterKey mk = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(this, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            SharedPreferences.Editor encryptedEditor = encryptedSp.edit();

            encryptedEditor.putString(ENCRYPTED_PREFERENCES, userName);

            encryptedEditor.apply();


        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}