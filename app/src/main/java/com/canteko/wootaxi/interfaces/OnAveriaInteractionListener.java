package com.canteko.wootaxi.interfaces;

import com.canteko.wootaxi.models.AveriaDB;

public interface OnAveriaInteractionListener {
    void onAveriaClick(AveriaDB averiaDB);
    void onAveriaEdit(AveriaDB averiaDB);
    void onAveriaEliminar(AveriaDB mItem);
}
