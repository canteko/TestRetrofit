package com.canteko.mecaround.models;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class AveriaDB extends RealmObject {

    @Ignore
    public static String AVERIADB_ID = "id";
    @Ignore
    public static String AVERIADB_TITULO = "titulo";
    @Ignore
    public static String AVERIADB_DESCRIPCION = "descripcion";
    @Ignore
    public static String AVERIADB_MODELO = "modelo";

    @Ignore
    public static AtomicLong MAX_ID = new AtomicLong();

    @PrimaryKey
    private long id;
    private String titulo;
    private String modeloCoche;
    private String urlFoto;
    private String descripcion;
    private int numeroPresupuestos;

    public AveriaDB() {
        this.id = MAX_ID.incrementAndGet();
    }

    public AveriaDB(String titulo, String modeloCoche, String urlFoto, int numeroPresupuestos, String descripcion) {
        this.id = MAX_ID.incrementAndGet();
        this.titulo = titulo;
        this.modeloCoche = modeloCoche;
        this.urlFoto = urlFoto;
        this.numeroPresupuestos = numeroPresupuestos;
        this.descripcion = descripcion;
    }

    public static AtomicLong getMaxId() {
        return MAX_ID;
    }

    public static void setMaxId(AtomicLong maxId) {
        MAX_ID = maxId;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getModeloCoche() {
        return modeloCoche;
    }

    public void setModeloCoche(String modeloCoche) {
        this.modeloCoche = modeloCoche;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getNumeroPresupuestos() {
        return numeroPresupuestos;
    }

    public void setNumeroPresupuestos(int numeroPresupuestos) {
        this.numeroPresupuestos = numeroPresupuestos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AveriaDB averiaDB = (AveriaDB) o;
        return numeroPresupuestos == averiaDB.numeroPresupuestos && Objects.equals(titulo, averiaDB.titulo) && Objects.equals(modeloCoche, averiaDB.modeloCoche) && Objects.equals(urlFoto, averiaDB.urlFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, modeloCoche, urlFoto, numeroPresupuestos);
    }

    @Override
    public String toString() {
        return "Averia{" +
                "titulo='" + titulo + '\'' +
                ", modeloCoche='" + modeloCoche + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", numeroPresupuestos=" + numeroPresupuestos +
                '}';
    }
}
