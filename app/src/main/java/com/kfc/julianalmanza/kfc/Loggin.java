package com.kfc.julianalmanza.kfc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Loggin extends AppCompatActivity {

    EditText usuario_ingresar,contraseña_ingresar;
    Button ingresar,registrar;
    String valor;
    ArrayList<usuario_datos> usuarios1 =new ArrayList<>();

    ContentValues dataBD;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        usuario_ingresar=(EditText) findViewById(R.id.usuario_ingresar);
        contraseña_ingresar=(EditText) findViewById(R.id.contraseña_ingresar);
        ingresar=(Button) findViewById(R.id.ingresar);
        registrar=(Button) findViewById(R.id.registrar);

        usuarios=new usuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();

        /*SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(prefs.getString("registrar","").equals("si")){
            String nomb=prefs.getString("nombre","");
            String contr=prefs.getString("contraseña","");
            String corr=prefs.getString("correo","");
            editor.putString("nombre","");
            editor.putString("contraseña","");
            editor.putString("correo","");
            usuario_datos us= new usuario_datos();
            us.ingresar_usuario(nomb,contr,corr);
            usuarios1.add(us);
        }
        editor.putString("registrar","");
        editor.commit();*/



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent().setClass(Loggin.this,Registrarse.class);
                startActivity(i);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu=usuario_ingresar.getText().toString();
                String contras=contraseña_ingresar.getText().toString();
                int comprobar=0;
                int puntero=0;
                /*for(int y=0;y<usuarios1.size();y++){
                    if(usu.equals(usuarios1.get(y).getnombre())){
                        if(contras.equals(usuarios1.get(y).getcontraseña())){
                            puntero=y;
                            comprobar=1;
                        }
                    }
                }
                if(comprobar==1) {

                    Intent i = new Intent().setClass(Loggin.this, MainActivity.class);
                    i.putExtra("nombre", usuarios1.get(puntero).getnombre());
                    i.putExtra("contraseña", usuarios1.get(puntero).getcontraseña());
                    i.putExtra("correo", usuarios1.get(puntero).getcorreo());
                    startActivity(i);
                }*/

                Cursor c=dbusuarios.rawQuery("select * from usuarios where nombre='"+usuario_ingresar.getText().toString()+"'",null);

                if(c.moveToFirst()){
                    if(c.getString(2).equals(contraseña_ingresar.getText().toString())){
                        dataBD=new ContentValues();
                        dataBD.put("activo","SI");
                        dbusuarios.execSQL("UPDATE usuarios SET activo='"+"SI"+"'"+"WHERE nombre ='"+usuario_ingresar.getText().toString()+"'");
                        Intent i = new Intent().setClass(Loggin.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            String nomb=data.getExtras().getString("nombre");
            String contr=data.getExtras().getString("contraseña");
            String corr=data.getExtras().getString("correo");
            usuario_datos us= new usuario_datos();
            us.ingresar_usuario(nomb,contr,corr);
            usuarios1.add(us);
            usuario_ingresar.setText(nomb);
            contraseña_ingresar.setText(contr);
        }else{

        }
    }

}

