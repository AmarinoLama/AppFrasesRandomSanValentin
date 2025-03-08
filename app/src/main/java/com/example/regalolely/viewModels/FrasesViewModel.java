package com.example.regalolely.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.regalolely.conexion.model.Frase;

import java.util.ArrayList;
import java.util.List;

public class FrasesViewModel extends ViewModel {

    private final MutableLiveData<List<Frase>> frases;

    public FrasesViewModel() {
        frases = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<List<Frase>> getFrases() {
        return frases;
    }

    public void setFrases(List<Frase> newFrases) {
        frases.setValue(newFrases);
    }

    public void addFrase(Frase frase) {
        List<Frase> currentList = frases.getValue();
        if (currentList != null) {
            currentList.add(frase);
            frases.setValue(currentList);
        }
    }

    public void removeFrase(Frase frase) {
        List<Frase> currentList = frases.getValue();
        if (currentList != null) {
            currentList.remove(frase);
            frases.setValue(currentList);
        }
    }
}