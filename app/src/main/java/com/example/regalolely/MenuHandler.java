package com.example.regalolely;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;

import com.example.regalolely.activities.ListadoFrases;
import com.example.regalolely.activities.VentanaCrud;

public class MenuHandler implements MenuProvider {

    private final Context context;

    public MenuHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_principal, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.op_inicioSesion) {
            Toast.makeText(context, "Opción 1", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.op_editarFrases) {
            Intent intent = new Intent(context, VentanaCrud.class);
            context.startActivity(intent);
            return true;
        }
        if (id == R.id.op_listadoFrases) {
            Intent intent = new Intent(context, ListadoFrases.class);
            context.startActivity(intent);
            return true;
        }
        if (id == R.id.op_controlarMusica) {
            Toast.makeText(context, "Opción 3", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.op_botonPanico) {
            Toast.makeText(context, "Opción 4", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}