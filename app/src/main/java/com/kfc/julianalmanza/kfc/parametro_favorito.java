package com.kfc.julianalmanza.kfc;

/**
 * Created by julian on 25/10/2016.
 */
public class parametro_favorito  {
    private String nombre,descripcion;

    public parametro_favorito(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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
