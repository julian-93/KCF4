package com.kfc.julianalmanza.kfc;

/**
 * Created by julian on 24/10/2016.
 */
public class parametros_lista_BD {

    private int imagen;
    private String nombre,descripcion,precio;

    public parametros_lista_BD(int imagen, String nombre, String descripcion,String precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio=precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
