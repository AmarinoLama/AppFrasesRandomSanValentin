package com.example.regalolely.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.regalolely.conexion.model.Frase;

public class FraseViewModel extends ViewModel {

    private final MutableLiveData<Frase> frase;

    public FraseViewModel() {
        frase = new MutableLiveData<>();
    }

    public MutableLiveData<Frase> getFrase() {
        return frase;
    }

    public void setFrase(Frase newFrase) {
        frase.setValue(newFrase);
    }
}