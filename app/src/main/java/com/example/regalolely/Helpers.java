package com.example.regalolely;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.regalolely.activities.Login;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Helpers {

    public static String getUserNameEncrypted(Context context) {
        String userName;
        try {
            MasterKey mk = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(context, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            userName = encryptedSp.getString(Login.ENCRYPTED_PREFERENCES, "noSeEncontro");

        } catch (GeneralSecurityException | IOException e) {
            return "noSeEncontro";
        }
        return userName;
    }
}
