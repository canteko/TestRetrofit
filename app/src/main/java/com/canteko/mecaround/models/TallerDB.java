package com.canteko.mecaround.models;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class TallerDB extends RealmObject {
    @Ignore
    public static AtomicLong MAX_ID = new AtomicLong();

    @PrimaryKey
    private long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private double latitude;
    private double longitude;

    public TallerDB() {
    }

    public TallerDB(long id, String nombre, String telefono, String direccion, double latitude, double longitude) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static AtomicLong getMaxId() {
        return MAX_ID;
    }

    public static void setMaxId(AtomicLong maxId) {
        MAX_ID = maxId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
