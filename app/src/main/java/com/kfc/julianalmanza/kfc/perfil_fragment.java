package com.kfc.julianalmanza.kfc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link perfil_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link perfil_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perfil_fragment extends Fragment {

    TextView datos;
    String nombre,contraseña,correo;

    ContentValues dataBD;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view=(ViewGroup) inflater.inflate(R.layout.perfil_fragment, container, false);
        datos=(TextView) view.findViewById(R.id.datos);

        usuarios=new usuariosSQLiteHelper(getContext(),"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();
        Cursor c = dbusuarios.rawQuery("select * from usuarios where activo='"+"SI"+"'",null);

        if(c.moveToFirst()) {
            nombre=c.getString(1);
            contraseña=c.getString(2);
            correo=c.getString(3);
        }
        else{

            nombre="";
        }

        String texto=getString(R.string.user)+"\n"+nombre+"\n"+getString(R.string.contraseña)+"\n"+contraseña+"\n"+getString(R.string.correo)+"\n"+correo;
        datos.setText(texto);

        // Inflate the layout for this fragment
        return view;
    }


}
