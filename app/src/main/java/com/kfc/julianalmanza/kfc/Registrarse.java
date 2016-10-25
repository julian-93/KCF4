package com.kfc.julianalmanza.kfc;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registrarse extends AppCompatActivity {
    EditText usuario_registro,contraseña_registro,confirmar_contraseña,correo;
    Button aceptar,cancelar;

    ContentValues dataBD;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        //ext=getApplicationContext();
        usuario_registro=(EditText) findViewById(R.id.usuario_registrar);
        contraseña_registro=(EditText) findViewById(R.id.contraseña_registrar);
        confirmar_contraseña=(EditText) findViewById(R.id.confirmar_contraseña);
        correo=(EditText) findViewById(R.id.correo);
        aceptar=(Button) findViewById(R.id.aceptar_registro);
        cancelar=(Button) findViewById(R.id.cancelar_registro);

        usuarios=new usuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();

        aceptar.setOnClickListener(new View.OnClickListener() {
            //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //Intent i=new Intent().setClass(Registrarse.this,Loggin.class);
                int comprobar = 1;
                String vacio = getText(R.string.vacio).toString();
                String contra = getText(R.string.diferentecontra).toString();
                String usuario = usuario_registro.getText().toString();
                usuario_registro.setError(null);
                contraseña_registro.setError(null);
                confirmar_contraseña.setError(null);
                correo.setError(null);
                if (usuario.isEmpty()) {
                    comprobar = 0;
                    usuario_registro.setError(vacio);
                }
                if (contraseña_registro.getText().toString().isEmpty()) {
                    comprobar = 0;
                    contraseña_registro.setError(vacio);
                }
                if (confirmar_contraseña.getText().toString().isEmpty()) {
                    comprobar = 0;
                    confirmar_contraseña.setError(vacio);
                }
                if (correo.getText().toString().isEmpty()) {
                    comprobar = 0;
                    correo.setError(vacio);
                }
                if (contraseña_registro.getText().toString().equals(confirmar_contraseña.getText().toString())) {
                }else{
                    comprobar = 0;
                    contraseña_registro.setError(contra);
                    confirmar_contraseña.setError(contra);
                }

                if (comprobar == 1) {

                    /*SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("nombre",usuario_registro.getText().toString());
                    editor.putString("contraseña", contraseña_registro.getText().toString());
                    editor.putString("correo", correo.getText().toString());
                    editor.commit();
                    Intent i = getIntent();
                    i.putExtra("nombre", usuario_registro.getText().toString());
                    i.putExtra("contraseña", contraseña_registro.getText().toString());
                    i.putExtra("correo", correo.getText().toString());
                    //startActivity(i);
                    //setResult(RESULT_OK, i);*/
                    String nombre,contraseña,corr;
                    nombre=usuario_registro.getText().toString();
                    contraseña=contraseña_registro.getText().toString();
                    corr=correo.getText().toString();
                    dataBD=new ContentValues();
                    dataBD.put("nombre",nombre);
                    dataBD.put("contraseña",contraseña);
                    dataBD.put("correo",corr);
                    dataBD.put("activo","NO");
                    dbusuarios.insert("usuarios",null,dataBD);
                    dbusuarios.execSQL("INSERT INTO usuarios VALUES(null, '"+nombre+
                            "','"+contraseña+"','"+corr+"','"+"NO"+"')");
                    //Intent i = getIntent();
                    //setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent().setClass(Registrarse.this,Loggin.class);
                Intent i=getIntent();
                //startActivity(i);
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
