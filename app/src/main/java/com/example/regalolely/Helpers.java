package com.example.regalolely;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.example.regalolely.activities.Login;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Helpers {

    private static ActivityResultLauncher<ScanOptions> qrLauncher;

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

    public static void scanearQR() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escanea el código de barras");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureActivity.class);

        qrLauncher.launch(options);
    }

    public interface QRResultCallback {
        void onResult(String result);
    }

    public static void inicializarQRLauncher(Context context, String[] resultadoEscaner, QRResultCallback callback) {
        qrLauncher = ((AppCompatActivity) context).registerForActivityResult(new ScanContract(), result -> {
            if (result.getContents() != null) {
                resultadoEscaner[0] = result.getContents();
                callback.onResult(result.getContents());
            }
        });
    }
}
