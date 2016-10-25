package com.kfc.julianalmanza.kfc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView imagen_bigbox,Imagen_bigbox_recargado,imagen_combo_economico,imagen_megapop;
    Button izquierda,derecha;
    String nombre;
    TextView usuario_main;
    LinearLayout line;
    int imag;

    ContentValues dataBD;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;

    private String[] opciones = new String[] {"Menu Principal","Mi Perfil","Comidas","Cerrar_sesion"};
    private DrawerLayout drawerLayout;
    ListView lista;

    private ActionBarDrawerToggle drawerToggle;

    private int[] imagenes={
            R.drawable.kfc_bigbox,
            R.drawable.kfc_bigbox_recargado,
            R.drawable.kfc_combo_economico,
            R.drawable.kfc_mega_pop,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        usuario_main=(TextView) findViewById(R.id.usuario_main);
        //nombre = prefs.getString("nombre", "");
        //contraseña = prefs.getString("contraseña", "");
        //correo = prefs.getString("correo", "");
        usuarios=new usuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();
        Cursor c = dbusuarios.rawQuery("select * from usuarios where activo='"+"SI"+"'",null);


        if(c.moveToFirst()) {
            nombre=c.getString(1);
        }
        else{

            nombre="";
        }

        //Bundle extra =getIntent().getExtras();
        String texto=getString(R.string.user);
        texto=texto+"\n"+nombre;
        usuario_main.setText(texto);

        izquierda=(Button) findViewById(R.id.izquierda);
        derecha=(Button) findViewById(R.id.derecha);
        line=(LinearLayout) findViewById(R.id.promo);
        imag=0;

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imag==0){
                    imag=3;
                    line.setBackground(getResources().getDrawable(imagenes[imag]));
                }else {
                    imag=imag-1;
                    line.setBackground(getResources().getDrawable(imagenes[imag]));
                }
            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imag==3){
                    imag=0;
                    line.setBackground(getResources().getDrawable(imagenes[imag]));
                }else {
                    imag=imag+1;
                    line.setBackground(getResources().getDrawable(imagenes[imag]));
                }
            }
        });

        drawerLayout=(DrawerLayout) findViewById(R.id.cp);
        lista=(ListView) findViewById(R.id.menuiz);
        lista.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1,opciones));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: //Intent intent=new Intent(MainActivity.this,MainActivity.class);
                            //startActivity(intent);
                        break;
                    case 1: Intent intent2=new Intent(MainActivity.this,Miperfil.class);
                        startActivity(intent2);
                        break;
                    case 2: Intent intent3=new Intent(MainActivity.this,Productos.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        /*SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("registrar","si");
                        editor.commit();*/

                        Cursor c = dbusuarios.rawQuery("select * from usuarios where activo='"+"SI"+"'",null);
                        if(c.moveToFirst()) {
                            nombre=c.getString(1);

                        }
                        else{

                            nombre="";
                        }
                        dataBD=new ContentValues();
                        dataBD.put("activo","NO");
                        dbusuarios.execSQL("UPDATE usuarios SET activo='"+"NO"+"'"+
                                "WHERE nombre ='"+nombre+"'");
                        Intent intent4=new Intent(MainActivity.this,Loggin.class);
                        startActivity(intent4);
                        break;
                }

                lista.setItemChecked(position,true);
                drawerLayout.closeDrawer(lista);

            }
        });
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto,R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);
        final ActionBar ab = getSupportActionBar();
        if (ab!=null){

            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }



    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case(R.id.Miperfil):

                Intent intent= new Intent(this,Miperfil.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("contraseña",contraseña);
                intent.putExtra("correo",correo);
                startActivity(intent);
                break;
            case(R.id.MainActivity):
                break;
            case(R.id.Productos):
                Intent intent2= new Intent(this,Productos.class);
                intent2.putExtra("nombre",nombre);
                intent2.putExtra("contraseña",contraseña);
                intent2.putExtra("correo",correo);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
