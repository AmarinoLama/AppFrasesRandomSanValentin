package com.example.regalolely.conexion;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;

@Database(entities = {Frase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FraseDao fraseDao();
}
