package com.canteko.mecaround.interfaces;

import com.canteko.mecaround.models.AveriaDB;

public interface OnAveriaInteractionListener {
    void onAveriaClick(AveriaDB averiaDB);
    void onAveriaEdit(AveriaDB averiaDB);
    void onAveriaEliminar(AveriaDB mItem);
}
