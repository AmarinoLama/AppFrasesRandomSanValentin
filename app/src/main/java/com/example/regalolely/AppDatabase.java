package com.example.regalolely;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Frase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FraseDao fraseDao();
}
