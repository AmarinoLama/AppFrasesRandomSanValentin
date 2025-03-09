package com.example.regalolely.conexion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "frases")
public class Frase {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String frase;

    private String autor;

    // Constructor
    public Frase(String frase, String autor) {
        this.frase = frase;
        this.autor = autor;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
