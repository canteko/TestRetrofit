package com.canteko.mecaround.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canteko.mecaround.R;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    ImageView logotipo = null;
    EditText editTextEmail = null;
    EditText editTextPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPass);

        logotipo = (ImageView) findViewById(R.id.idLogo);
        RequestOptions cropOption = new RequestOptions().fitCenter();
        Glide.with(this)
                .load("https://account.splashtrack.com/Content/V2/img/user-login-bg.png")
                .apply(cropOption)
                .into(logotipo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isLoggedIn()) {
            goToMainActivity();
        }
    }

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(email.equalsIgnoreCase("miguel@gmail.com") && password.equals("1234")) {
            // Login correcto

            // Guardamos los datos de login
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences_file_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preferences_email), email);
            editor.putBoolean(getString(R.string.preferences_is_logged_in), true);
            editor.apply();

            goToMainActivity();
        } else {
            Toast.makeText(this, "Email y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public void goToMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences_file_name), Context.MODE_PRIVATE);
        String email = sharedPref.getString(getString(R.string.preferences_email), "email");
        boolean isLoggedIn = sharedPref.getBoolean(getString(R.string.preferences_is_logged_in), false);
        return isLoggedIn;
    }
}