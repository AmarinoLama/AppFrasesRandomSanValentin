package com.example.regalolely;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "frases")
public class Frase {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String frase;

    // Constructor
    public Frase(String frase) {
        this.frase = frase;
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
}
