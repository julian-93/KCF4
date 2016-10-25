package com.kfc.julianalmanza.kfc;

/**
 * Created by julian on 11/09/2016.
 */
public class usuario_datos {
        private String nombre;
        private String contraseña;
        private String correo;
        public void ingresar_usuario(String n,String contra,String cor){
            this.nombre=n;
            this.contraseña=contra;
            this.correo=cor;
        }

    public String getnombre() {
        return nombre;
    }

    public String getcontraseña() {
        return contraseña;
    }

    public String getcorreo() {
        return correo;
    }
}
