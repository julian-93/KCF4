package com.kfc.julianalmanza.kfc;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.kfc.julianalmanza.kfc.R.string.refrescos;


/**
 * A simple {@link Fragment} subclass.
 */
public class Refrescos_fragment extends Fragment {
    ListView list;
    private parametros_lista[]parametros;
    public Refrescos_fragment() {
        // Required empty public constructor
    }

    SQLiteDatabase dbproductos;
    productosSQLiteHelper productos;

    ArrayList<parametros_lista_BD> produc =new ArrayList<parametros_lista_BD>();
    parametros_lista_BD producto;

//just imain
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view=(ViewGroup) inflater.inflate(R.layout.fragment_refrescos, container, false);
        parametros= new parametros_lista[]{
                        new parametros_lista(R.drawable.refrescos,view.getResources().getString(R.string.refrescos),view.getResources().getString(R.string.descripcion_refrescos)),
                        new parametros_lista(R.drawable.limonada,view.getResources().getString(R.string.limonada),view.getResources().getString(R.string.descripcion_limonada)),
                        new parametros_lista(R.drawable.tea,view.getResources().getString(R.string.tea),view.getResources().getString(R.string.descripcion_tea))


                };

        adapter adaptador=new adapter(getContext(),produc);
        list=(ListView) view.findViewById(R.id.lts);
        View header;
        header = (View) View.inflate(getContext(),R.layout.header_productos,null);
        TextView titulo=(TextView) header.findViewById(R.id.pr);
        titulo.setText(view.getResources().getString(R.string.disfrutarefres));
        list.addHeaderView(header);
        list.setAdapter(adaptador);

        productos=new productosSQLiteHelper(getContext(),"ProductosBD",null,1);
        dbproductos=productos.getWritableDatabase();

        Cursor c = dbproductos.rawQuery("select * from productos where producto='"+view.getResources().getString(R.string.refrescos)+"'",null);
        if (c.moveToFirst()) {
            producto = new parametros_lista_BD(R.drawable.refrescos, c.getString(1), c.getString(2),c.getString(3));
            produc.add(producto);

        }
        c = dbproductos.rawQuery("select * from productos where producto='"+view.getResources().getString(R.string.limonada)+"'",null);
        if (c.moveToFirst()) {
            producto = new parametros_lista_BD(R.drawable.limonada, c.getString(1), c.getString(2),c.getString(3));
            produc.add(producto);

        }
        c = dbproductos.rawQuery("select * from productos where producto='"+view.getResources().getString(R.string.tea)+"'",null);
        if (c.moveToFirst()) {
            producto = new parametros_lista_BD(R.drawable.tea, c.getString(1), c.getString(2),c.getString(3));
            produc.add(producto);

        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    parametros_lista_BD objeto = ((parametros_lista_BD) parent.getAdapter().getItem(position));
                    Intent intent = new Intent(getContext(), visualizacion_productos.class);
                    String a = Integer.toString(objeto.getImagen());
                    intent.putExtra("imagen", a);
                    intent.putExtra("nombre", objeto.getNombre());
                    intent.putExtra("descripcion", objeto.getDescripcion());
                    startActivity(intent);
                }
            }
        });



        return view;


    }

    public class adapter extends ArrayAdapter<parametros_lista_BD>{
        ArrayList<parametros_lista_BD> parametros_lista_BD;
        public adapter(Context context, ArrayList<parametros_lista_BD> datos) {
            super(context, R.layout.lista_item,datos);
            this.parametros_lista_BD=datos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View lista=inflater.inflate(R.layout.lista_item,null);
            ImageView imagen=(ImageView) lista.findViewById(R.id.imagen);
            TextView nombre=(TextView) lista.findViewById(R.id.nombre);
            TextView descripcion=(TextView) lista.findViewById(R.id.descripcion);
            imagen.setImageDrawable(getResources().getDrawable(parametros_lista_BD.get(position).getImagen()));
            nombre.setText(parametros_lista_BD.get(position).getNombre());
            descripcion.setText("");

            return  (lista);

        }
    }


}




