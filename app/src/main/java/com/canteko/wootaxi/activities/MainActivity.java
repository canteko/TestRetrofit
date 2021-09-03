package com.canteko.wootaxi.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.canteko.wootaxi.commons.Parameters;
import com.canteko.wootaxi.databinding.ActivityMainBinding;
import com.canteko.wootaxi.fragments.EditAveriaFragment;
import com.canteko.wootaxi.fragments.NuevaAveriaDialogo;
import com.canteko.wootaxi.interfaces.ApiWootaxiInterface;
import com.canteko.wootaxi.interfaces.OnAveriaInteractionListener;
import com.canteko.wootaxi.interfaces.OnNuevaAveriaListener;
import com.canteko.wootaxi.models.AveriaDB;
import com.canteko.wootaxi.requests.LoginRequest;
import com.canteko.wootaxi.requests.VerifyApiKeyRequest;
import com.canteko.wootaxi.responses.LoginResponse;
import com.canteko.wootaxi.responses.data.LoginData;

import java.net.HttpURLConnection;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnAveriaInteractionListener, OnNuevaAveriaListener {

    private ActivityMainBinding binding;
    private DialogFragment dialogNuevaAveria;
    private DialogFragment dialogEditAveria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNuevaAveria = new NuevaAveriaDialogo();
                dialogNuevaAveria.show(getSupportFragmentManager(), "NuevaAveriaDialogo");
            }
        });
    }

    @Override
    public void onAveriaClick(AveriaDB averia) {
        Intent i = new Intent(this, DetalleAveriaActivity.class);
        i.putExtra(AveriaDB.AVERIADB_ID, averia.getId());
        startActivity(i);
    }

    @Override
    public void onAveriaEdit(AveriaDB averiaDB) {
        this.dialogEditAveria = EditAveriaFragment.newInstance(averiaDB.getId(), averiaDB.getTitulo(), averiaDB.getDescripcion(), averiaDB.getModeloCoche());
        dialogEditAveria.show(getSupportFragmentManager(), "EditAveria");
    }

    @Override
    public void onAveriaGuardarListener(final String titulo, final String descripcion, final String modeloCoche) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AveriaDB nuevaAveria = new AveriaDB();
                nuevaAveria.setTitulo(titulo);
                nuevaAveria.setModeloCoche(modeloCoche);
                nuevaAveria.setNumeroPresupuestos(0);
                nuevaAveria.setUrlFoto("");
                nuevaAveria.setDescripcion(descripcion);

                realm.copyToRealm(nuevaAveria);
            }
        });
    }

    @Override
    public void onAveriaEditarListener(final String titulo, final String descripcion, final String modeloCoche, final long idAveria) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AveriaDB nuevaAveria = new AveriaDB();
                nuevaAveria.setId(idAveria);
                nuevaAveria.setTitulo(titulo);
                nuevaAveria.setModeloCoche(modeloCoche);
                nuevaAveria.setNumeroPresupuestos(0);
                nuevaAveria.setUrlFoto("");
                nuevaAveria.setDescripcion(descripcion);

                realm.copyToRealmOrUpdate(nuevaAveria);
            }
        });
    }

    @Override
    public void onAveriaEliminar(AveriaDB averiaDB) {
        mostrarDialogoEliminar(averiaDB);
    }

    private void mostrarDialogoEliminar(AveriaDB item) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar avería")
                .setMessage("¿Seguro que deseas eliminar la avería " + item.getTitulo() + "?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.where(AveriaDB.class).equalTo(AveriaDB.AVERIADB_ID, item.getId()).findFirst().deleteFromRealm();
                                Toast.makeText(getBaseContext(), "Averia Eliminada", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfValidCredentials();
    }

    private void checkIfValidCredentials() {
        String apiKey = Parameters.getApiKey(this);
        String email = Parameters.getEmail(this);

        if (apiKey.isEmpty() || email.isEmpty()) {
            goToLoginActivity();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Parameters.getApiBaseUrl(this))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiWootaxiInterface apiService = retrofit.create(ApiWootaxiInterface.class);
        Call<LoginResponse> verifyApiKeyCall = apiService.verifyApiKey(new VerifyApiKeyRequest(apiKey, email));
        verifyApiKeyCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(response.code() != HttpURLConnection.HTTP_OK || loginResponse == null || !loginResponse.isStatus()) {
                    goToLoginActivity();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                goToLoginActivity();
            }
        });
    }

    public void goToLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}