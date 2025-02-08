package com.example.regalolely;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FraseDao {
    @Insert
    void insert(Frase frase);

    @Query("SELECT * FROM frases ORDER BY RANDOM() LIMIT 1")
    Frase getRandomFrase();

    @Query("SELECT * FROM frases")
    List<Frase> getAllFrases();

    @Query("DELETE FROM frases")
    void deleteAllFrases();
}