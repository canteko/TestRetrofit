package com.canteko.mecaround.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.canteko.mecaround.R;
import com.canteko.mecaround.interfaces.OnNuevaAveriaListener;
import com.canteko.mecaround.models.AveriaDB;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditAveriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAveriaFragment extends DialogFragment {

    private long idAveria = 0L;
    private String titulo = "";
    private String descripcion = "";
    private String modelo = "";


    private AlertDialog.Builder builder;
    private OnNuevaAveriaListener mListener;
    private View view;
    private EditText editTextTitulo, editTextDescripcion, editTextModelo;
    private Context context;

    public EditAveriaFragment() {
        // Required empty public constructor
    }

    public static EditAveriaFragment newInstance(final long id, final String titulo, final String descripcion, final String modelo) {
        EditAveriaFragment fragment = new EditAveriaFragment();
        Bundle args = new Bundle();
        args.putLong(AveriaDB.AVERIADB_ID, id);
        args.putString(AveriaDB.AVERIADB_TITULO, titulo);
        args.putString(AveriaDB.AVERIADB_DESCRIPCION, descripcion);
        args.putString(AveriaDB.AVERIADB_MODELO, modelo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.idAveria = getArguments().getLong(AveriaDB.AVERIADB_ID);
            this.titulo = getArguments().getString(AveriaDB.AVERIADB_TITULO);
            this.descripcion = getArguments().getString(AveriaDB.AVERIADB_DESCRIPCION);
            this.modelo = getArguments().getString(AveriaDB.AVERIADB_MODELO);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        this.context = getActivity();

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder = new AlertDialog.Builder(getActivity());

        this.view = inflater.inflate(R.layout.dialog_averia, null);
        editTextTitulo = this.view.findViewById(R.id.editTextAveriaTitle);
        editTextDescripcion = this.view.findViewById(R.id.editTextDescription);
        editTextModelo = this.view.findViewById(R.id.editTextModelo);

        editTextTitulo.setText(this.titulo);
        editTextDescripcion.setText(this.descripcion);
        editTextModelo.setText(this.modelo);

        builder.setView(this.view);
        builder.setTitle("Editar averia")
                .setPositiveButton(getString(R.string.averia_dialog_save), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = editTextTitulo.getText().toString();
                        String descripcion = editTextDescripcion.getText().toString();
                        String modeloCoche = editTextModelo.getText().toString();

                        if(!titulo.isEmpty() && !descripcion.isEmpty() && !modeloCoche.isEmpty()) {
                            Toast.makeText(context, "Guardando avería...", Toast.LENGTH_LONG).show();
                            mListener.onAveriaEditarListener(titulo, descripcion, modeloCoche, idAveria);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Rellena los campos", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(getString(R.string.averia_dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNuevaAveriaListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}