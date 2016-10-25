package com.kfc.julianalmanza.kfc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class visualizacion_productos extends AppCompatActivity {
    TextView titulo,descripcion,precio;
    ImageView imagen,estrella;
    String imag,usuario,usuario2;
    String nombre,descrip,prec;
    Button volver;
    Integer estado;

    ContentValues dataBD,dataBD2;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;

    SQLiteDatabase dbproductos;
    productosSQLiteHelper productos;

    SQLiteDatabase dbfavoritos;
    favoritosSQLiteHelper favoritos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion_productos);

        Bundle extra =getIntent().getExtras();

        nombre=extra.getString("nombre");
        //descrip=extra.getString("descripcion");
        imag=extra.getString("imagen");

        productos=new productosSQLiteHelper(this,"ProductosBD",null,1);
        dbproductos=productos.getWritableDatabase();
        usuarios=new usuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();
        favoritos=new favoritosSQLiteHelper(this,"FavoritosBD",null,1);
        dbfavoritos=favoritos.getWritableDatabase();

        Cursor c3 = dbusuarios.rawQuery("select * from usuarios where activo='" + "SI" + "'", null);

        if (c3.moveToFirst()) {
            usuario2 = c3.getString(1);

        }

        Cursor c = dbproductos.rawQuery("select * from productos where producto='"+nombre+"'",null);

        if (c.moveToFirst()) {
            descrip=c.getString(2);
            prec=c.getString(3);


        }

        titulo=(TextView) findViewById(R.id.titulo);
        precio=(TextView) findViewById(R.id.precio);
        descripcion=(TextView) findViewById(R.id.descripcion);
        imagen=(ImageView) findViewById(R.id.imagen);
        estrella=(ImageView) findViewById(R.id.favorito_estrella);

        titulo.setText(nombre);
        descripcion.setText(descrip);
        String s="Precio: "+prec;
        precio.setText(s);
        int a=Integer.parseInt(imag);
        imagen.setImageDrawable(getResources().getDrawable(a));
        volver=(Button) findViewById(R.id.volver);

        Cursor c2=dbfavoritos.rawQuery("select * from favoritos where producto ='"+nombre+"'",null);
        //Cursor c4=dbfavoritos.rawQuery("select * from favoritos where usuario ='"+usuario2+"'",null);

        if(c2.moveToFirst()){
            do {

                    if (c2.getString(3).equals(usuario2)) {
                        estado = 1;
                        estrella.setImageDrawable(getResources().getDrawable(R.drawable.estrella_roja));
                    }else {
                        estado = 0;
                        estrella.setImageDrawable(getResources().getDrawable(R.drawable.estrella_blanca));
                    }
            }while(c2.moveToNext());
        }
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        estrella.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(estado==0){
                    Cursor c = dbusuarios.rawQuery("select * from usuarios where activo='"+"SI"+"'",null);


                    if(c.moveToFirst()) {
                        usuario=c.getString(1);
                    }

                    estado=1;
                    estrella.setImageDrawable(getResources().getDrawable(R.drawable.estrella_roja));
                    dataBD=new ContentValues();
                    dataBD.put("producto",nombre);
                    dataBD.put("descripcion",descrip);
                    dataBD.put("usuario",usuario);
                    dbfavoritos.insert("usuarios",null,dataBD);
                    dbfavoritos.execSQL("INSERT INTO favoritos VALUES(null, '"+nombre+
                            "','"+descrip+"','"+usuario+"')");
                }else{
                    estado=0;
                    estrella.setImageDrawable(getResources().getDrawable(R.drawable.estrella_blanca));
                    dbfavoritos.delete("favoritos","producto='"+nombre+"'",null);
                }
            }
        });

    }
}
