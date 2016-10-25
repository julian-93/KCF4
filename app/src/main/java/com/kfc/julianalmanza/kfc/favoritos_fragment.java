package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class favoritos_fragment extends Fragment {

    ListView list;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;
    String usuario;
    ArrayList<parametro_favorito> favor =new ArrayList<parametro_favorito>();

    parametro_favorito favorito;


    SQLiteDatabase dbfavoritos;
    favoritosSQLiteHelper favoritos;
    public favoritos_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.favoritos_fragment, container, false);


        adapter adaptador = new adapter(getContext(), favor);
        list = (ListView) view.findViewById(R.id.lts);
        View header;
        header = (View) View.inflate(getContext(), R.layout.header_productos, null);
        TextView titulo = (TextView) header.findViewById(R.id.pr);
        titulo.setText(view.getResources().getString(R.string.disfrutafavorito));
        list.addHeaderView(header);
        list.setAdapter(adaptador);

        favoritos = new favoritosSQLiteHelper(getContext(), "FavoritosBD", null, 1);
        dbfavoritos = favoritos.getWritableDatabase();

        usuarios = new usuariosSQLiteHelper(getContext(), "UsuariosBD", null, 1);
        dbusuarios = usuarios.getWritableDatabase();
        Cursor c = dbusuarios.rawQuery("select * from usuarios where activo='" + "SI" + "'", null);

        if (c.moveToFirst()) {
            usuario = c.getString(1);

        }
        Cursor c2 = dbfavoritos.rawQuery("select * from favoritos where usuario='" + usuario + "'", null);
        if (c2.moveToFirst()) {
            favorito = new parametro_favorito(c2.getString(1), c2.getString(2));
            //favorito = new parametro_favorito("HOLA", "BEBE");
            favor.add(favorito);
            while(c2.moveToNext()){
                favorito = new parametro_favorito(c2.getString(1), c2.getString(2));
                favor.add(favorito);
            }
            }

            return view;
        }

        public class adapter extends ArrayAdapter<parametro_favorito> {
            ArrayList<parametro_favorito> parametro_favorito;

            public adapter(Context context, ArrayList<parametro_favorito> datos) {
                super(context, R.layout.favorito, datos);
                this.parametro_favorito = datos;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View lista = inflater.inflate(R.layout.favorito, null);
                //ImageView imagen=(ImageView) lista.findViewById(R.id.imagen);
                TextView nombre = (TextView) lista.findViewById(R.id.nombre);
                TextView descripcion = (TextView) lista.findViewById(R.id.descripcion);
                //imagen.setImageDrawable(getResources().getDrawable(parametro_favorito.get(position).getImagen()));
                nombre.setText(parametro_favorito.get(position).getNombre());
                descripcion.setText(parametro_favorito.get(position).getDescripcion());
                //descripcion.setText("");

                return (lista);

            }
        }


    }
