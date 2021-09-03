package com.canteko.wootaxi.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.canteko.wootaxi.R;
import com.canteko.wootaxi.interfaces.OnNuevaAveriaListener;

public class NuevaAveriaDialogo extends DialogFragment {

    private AlertDialog.Builder builder;
    private OnNuevaAveriaListener mListener;
    private View view;
    private EditText editTextTitulo, editTextDescripcion, editTextModelo;
    private Context context;

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
        builder.setView(this.view);
        builder.setTitle(getString(R.string.averia_dialog_title))
                .setPositiveButton(getString(R.string.averia_dialog_save), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = editTextTitulo.getText().toString();
                        String descripcion = editTextDescripcion.getText().toString();
                        String modeloCoche = editTextModelo.getText().toString();

                        if(!titulo.isEmpty() && !descripcion.isEmpty() && !modeloCoche.isEmpty()) {
                            Toast.makeText(context, "Guardando avería...", Toast.LENGTH_LONG).show();
                            mListener.onAveriaGuardarListener(titulo, descripcion, modeloCoche);
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