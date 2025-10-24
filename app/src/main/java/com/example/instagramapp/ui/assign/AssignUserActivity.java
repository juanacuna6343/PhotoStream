package com.example.instagramapp.ui.assign;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramapp.R;

public class AssignUserActivity extends AppCompatActivity {
    private static final String PREFS = "instagram_prefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_user);

        EditText editAccessToken = findViewById(R.id.editAccessToken);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String token = editAccessToken.getText().toString().trim();
            if (!token.isEmpty()) {
                SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
                prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply();
                finish();
            } else {
                editAccessToken.setError("Ingrese el Access Token");
            }
        });
    }
}