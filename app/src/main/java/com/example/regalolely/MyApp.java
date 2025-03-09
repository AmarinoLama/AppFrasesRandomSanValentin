package com.example.regalolely;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

public class MyApp extends Application {
    @Override
    public void onCreate() {

        // AQUÍ VAN AJUSTES DE LA APLICACIÓN

        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Always in light mode
    }

    // Hacer que cuando te logees aparezca tu nombre encima

    // Hacer que funcione el dark mode

    // Mejorar paleta colores

    // Botón del pánico

    // Gestionar los permisos
}