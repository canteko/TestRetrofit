package com.canteko.wootaxi.interfaces;

public interface OnNuevaAveriaListener {

    void onAveriaGuardarListener(final String titulo, final String descripcion, final String modeloCoche);
    void onAveriaEditarListener(final String titulo, final String descripcion, final String modeloCoche, final long idAveria);
}
