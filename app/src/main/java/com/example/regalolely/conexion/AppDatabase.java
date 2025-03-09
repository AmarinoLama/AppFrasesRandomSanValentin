package com.example.regalolely.conexion;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

// Si se cambia algo de las tablas o la bbdd en general se debe cambiar la versión
@Database(entities = {Frase.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FraseDao fraseDao();
}
