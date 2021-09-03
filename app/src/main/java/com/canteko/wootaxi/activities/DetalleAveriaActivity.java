package com.canteko.wootaxi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.canteko.wootaxi.R;
import com.canteko.wootaxi.databinding.ActivityDetalleAveriaBinding;
import com.canteko.wootaxi.models.AveriaDB;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.realm.Realm;

public class DetalleAveriaActivity extends AppCompatActivity {

    private ActivityDetalleAveriaBinding binding;
    private TextView textViewInfo;
    private long idAveria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalleAveriaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        Bundle extras = getIntent().getExtras();

        textViewInfo = findViewById(R.id.textoAveria);
        idAveria = extras.getLong(AveriaDB.AVERIADB_ID);

        Realm realm = Realm.getDefaultInstance();

        AveriaDB averia = realm.where(AveriaDB.class).equalTo(AveriaDB.AVERIADB_ID, idAveria).findFirst();

        toolBarLayout.setTitle(averia.getTitulo());
        textViewInfo.setText(averia.getDescripcion());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}