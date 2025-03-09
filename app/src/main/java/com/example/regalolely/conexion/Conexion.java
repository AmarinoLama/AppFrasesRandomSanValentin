package com.example.regalolely.conexion;

import android.content.Context;

import androidx.room.Room;

import com.example.regalolely.conexion.dao.FraseDao;

public class Conexion {

    private static AppDatabase db;
    private static FraseDao fraseDao;

    public static void runBBDD(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "frases-database")
                    .allowMainThreadQueries() // No recomendado en producción
                    .fallbackToDestructiveMigration()
                    .build();
            fraseDao = db.fraseDao();
        }
    }

    public static FraseDao getFraseDao() {
        return fraseDao;
    }
}
