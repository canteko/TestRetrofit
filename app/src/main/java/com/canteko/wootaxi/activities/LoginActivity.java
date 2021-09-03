package com.canteko.wootaxi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canteko.wootaxi.R;
import com.canteko.wootaxi.commons.Parameters;
import com.canteko.wootaxi.interfaces.ApiWootaxiInterface;
import com.canteko.wootaxi.requests.LoginRequest;
import com.canteko.wootaxi.requests.VerifyApiKeyRequest;
import com.canteko.wootaxi.responses.LoginResponse;
import com.canteko.wootaxi.responses.data.LoginData;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Introduce todos los datos", Toast.LENGTH_LONG).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Parameters.getApiBaseUrl(this))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiWootaxiInterface apiService = retrofit.create(ApiWootaxiInterface.class);
        Call<LoginResponse> loginCall = apiService.login(new LoginRequest(email, password));
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(response.code() != HttpURLConnection.HTTP_OK) {
                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error, codigo devuelto por el servidor: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                LoginData data = loginResponse.getData();
                String apiKey = data.getApiKey();
                if(apiKey == null || apiKey.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No se ha podido iniciar sesión", Toast.LENGTH_LONG).show();
                    return;
                }

                Parameters.setApiKey(getApplicationContext(), apiKey);
                Parameters.setEmail(getApplicationContext(), email);

                goToMainActivity();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se ha podido lanzar la petición", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}