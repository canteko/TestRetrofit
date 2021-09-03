package com.canteko.wootaxi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.canteko.wootaxi.R;
import com.canteko.wootaxi.databinding.AveriaItemBinding;
import com.canteko.wootaxi.interfaces.OnAveriaInteractionListener;
import com.canteko.wootaxi.models.AveriaDB;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AveriaDB}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAveriaRecyclerViewAdapter extends RecyclerView.Adapter<MyAveriaRecyclerViewAdapter.ViewHolder> {

    private final OrderedRealmCollection<AveriaDB> mValues;
    private OnAveriaInteractionListener mListener;
    private Context ctx;
    private RealmChangeListener listener;

    public MyAveriaRecyclerViewAdapter(Context context, OrderedRealmCollection<AveriaDB> items, OnAveriaInteractionListener listener) {
        this.ctx = context;
        this.mValues = items;
        this.mListener = listener;

        this.listener = (results) -> {
            notifyDataSetChanged();
        };

        if(items != null) {
            addListener(items);
        }
    }

    private void addListener(OrderedRealmCollection<AveriaDB> items) {
        if(items instanceof RealmResults) {
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listener);
        } else if(items instanceof RealmList) {
            RealmList<AveriaDB> list = (RealmList<AveriaDB>) items;
            list.addChangeListener(listener);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(AveriaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewModeloCoche.setText(holder.mItem.getModeloCoche());
        holder.textViewPresupuestos.setText("Presupuestos: " + holder.mItem.getNumeroPresupuestos());

        if(holder.mItem.getUrlFoto() != null && !holder.mItem.getUrlFoto().isEmpty()) {
            Glide.with(this.ctx)
                    .load(holder.mItem.getUrlFoto())
                    .into(holder.fotoAveriaDB);
        }

        holder.itemView.setOnClickListener(v -> {
            if(null != mListener) {
                mListener.onAveriaClick(holder.mItem);
            }
        });

        holder.imageViewEditarAveria.setOnClickListener(v -> {
            if(null != mListener) {
                mListener.onAveriaEdit(holder.mItem);
            }
        });

        holder.imageViewEliminarAveria.setOnClickListener(v -> {
            if(null != mListener) {
                mListener.onAveriaEliminar(holder.mItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewTitulo;
        public final TextView textViewModeloCoche;
        public final TextView textViewPresupuestos;
        public final ImageView fotoAveriaDB;
        public final ImageView imageViewEditarAveria;
        public final ImageView imageViewEliminarAveria;
        public AveriaDB mItem;

        public ViewHolder(AveriaItemBinding binding) {
            super(binding.getRoot());
            textViewTitulo = binding.getRoot().findViewById(R.id.textViewAveriaTitulo);
            textViewModeloCoche = binding.getRoot().findViewById(R.id.textViewModeloCocheAveria);
            textViewPresupuestos = binding.getRoot().findViewById(R.id.textViewPresupuestos);
            fotoAveriaDB = binding.getRoot().findViewById(R.id.imageViewPhoto);
            imageViewEditarAveria = binding.getRoot().findViewById(R.id.imageViewEditarAveria);
            imageViewEliminarAveria = binding.getRoot().findViewById(R.id.imageViewEliminarAveria);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }
}