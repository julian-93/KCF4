package com.kfc.julianalmanza.kfc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import android.widget.ListView;
import android.widget.TextView;

public class Productos extends AppCompatActivity {
    private ViewPager mViewPager;

    String nombre,contraseña,correo;

    private String[] opciones = new String[] {"Menu Principal","Mi Perfil","Comidas","Cerrar_sesion"};
    private DrawerLayout drawerLayout;
    ListView lista;

    ContentValues dataBD,dataBD2;
    SQLiteDatabase dbusuarios;
    usuariosSQLiteHelper usuarios;

    SQLiteDatabase dbproductos;
    productosSQLiteHelper productos;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        /*Bundle extra =getIntent().getExtras();
        nombre=extra.getString("nombre");
        contraseña=extra.getString("contraseña");
        correo=extra.getString("correo");*/


        usuarios=new usuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbusuarios=usuarios.getWritableDatabase();

        productos=new productosSQLiteHelper(this,"ProductosBD",null,1);
        dbproductos=productos.getWritableDatabase();

        dataBD2=new ContentValues();
        dataBD2.put("producto",getString(R.string.megavariedad));
        dataBD2.put("descripcion",getString(R.string.descripcion_megavariedad));
        dataBD2.put("precio",getString(R.string.precio_megavariedad));
        dbproductos.insert("productos",null,dataBD2);
        String n=getString(R.string.megavariedad);
        String d=getString(R.string.descripcion_megavariedad);
        String p=getString(R.string.precio_megavariedad);
        dbproductos.execSQL("INSERT INTO productos VALUES(null,'" + n +
                "','" + d + "','"+p+"')");
        dataBD2.put("producto",getString(R.string.bucket7));
        dataBD2.put("descripcion",getString(R.string.descripcion_bucket7));
        dataBD2.put("precio",getString(R.string.precio_bucket7));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.bucket7)+
                "','"+getString(R.string.descripcion_bucket7)+
                "','"+getString(R.string.precio_bucket7)+"')");

        dataBD2.put("producto",getString(R.string.megaeconomico));
        dataBD2.put("descripcion",getString(R.string.descripcion_megaeconomico));
        dataBD2.put("precio",getString(R.string.precio_megaeconomico));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.megaeconomico)+
                "','"+getString(R.string.descripcion_megaeconomico)+
                "','"+getString(R.string.precio_megaeconomico)+"')");
        dataBD2.put("producto",getString(R.string.megasinigual));
        dataBD2.put("descripcion",getString(R.string.descripcion_megasinigual));
        dataBD2.put("precio",getString(R.string.precio_megasinigual));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.megasinigual)+
                "','"+getString(R.string.descripcion_megasinigual)+
                "','"+getString(R.string.precio_megasinigual)+"')");
        dataBD2.put("producto",getString(R.string.megafutbolero));
        dataBD2.put("descripcion",getString(R.string.descripcion_megafutbolero));
        dataBD2.put("precio",getString(R.string.precio_megafutbolero));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.megafutbolero)+
                "','"+getString(R.string.descripcion_megafutbolero)+
                "','"+getString(R.string.precio_megafutbolero)+"')");
        dataBD2.put("producto",getString(R.string.mega1));
        dataBD2.put("descripcion",getString(R.string.descripcion_mega1));
        dataBD2.put("precio",getString(R.string.precio_mega1));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.mega1)+
                "','"+getString(R.string.descripcion_mega1)+
                "','"+getString(R.string.precio_mega1)+"')");
        dataBD2.put("producto",getString(R.string.mega2));
        dataBD2.put("descripcion",getString(R.string.descripcion_mega2));
        dataBD2.put("precio",getString(R.string.precio_mega2));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.mega2)+
                "','"+getString(R.string.descripcion_mega2)+
                "','"+getString(R.string.precio_mega2)+"')");
        dataBD2.put("producto",getString(R.string.megahot));
        dataBD2.put("descripcion",getString(R.string.descripcion_megahot));
        dataBD2.put("precio",getString(R.string.precio_megahot));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.megahot)+
                "','"+getString(R.string.descripcion_megahot)+
                "','"+getString(R.string.precio_megahot)+"')");
        dataBD2.put("producto",getString(R.string.megapop));
        dataBD2.put("descripcion",getString(R.string.descripcion_megapop));
        dataBD2.put("precio",getString(R.string.precio_megapop));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.megapop)+
                "','"+getString(R.string.descripcion_megapop)+
                "','"+getString(R.string.precio_megapop)+"')");

        dataBD2.put("producto",getString(R.string.doublicious));
        dataBD2.put("descripcion",getString(R.string.descripcion_doublicious));
        dataBD2.put("precio",getString(R.string.precio_doublicious));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.doublicious)+
                "','"+getString(R.string.descripcion_doublicious)+
                "','"+getString(R.string.precio_doublicious)+"')");
        dataBD2.put("producto",getString(R.string.chicken_littles));
        dataBD2.put("descripcion",getString(R.string.descripcion_chicken_littles));
        dataBD2.put("precio",getString(R.string.precio_chicken));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.chicken_littles)+
                "','"+getString(R.string.descripcion_chicken_littles)+
                "','"+getString(R.string.precio_chicken)+"')");

        dataBD2.put("producto",getString(R.string.refrescos));
        dataBD2.put("descripcion",getString(R.string.descripcion_refrescos));
        dataBD2.put("precio",getString(R.string.precio_refrescos));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.refrescos)+
                "','"+getString(R.string.descripcion_refrescos)+
                "','"+getString(R.string.precio_refrescos)+"')");
        dataBD2.put("producto",getString(R.string.limonada));
        dataBD2.put("descripcion",getString(R.string.descripcion_limonada));
        dataBD2.put("precio",getString(R.string.precio_limonada));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.limonada)+
                "','"+getString(R.string.descripcion_limonada)+
                "','"+getString(R.string.precio_limonada)+"')");
        dataBD2.put("producto",getString(R.string.tea));
        dataBD2.put("descripcion",getString(R.string.descripcion_tea));
        dataBD2.put("precio",getString(R.string.precio_tea));
        dbproductos.insert("productos",null,dataBD2);
        dbproductos.execSQL("INSERT INTO productos VALUES(null, '"+getString(R.string.tea)+
                "','"+getString(R.string.descripcion_tea)+
                "','"+getString(R.string.precio_tea)+"')");






        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Pollo").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Refrescos").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Sandwiches").setTabListener(tabListener);
        actionBar.addTab (tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

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
                    case 0: Intent intent=new Intent(Productos.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1: Intent intent2=new Intent(Productos.this,Miperfil.class);
                        startActivity(intent2);
                        break;
                    case 2: //Intent intent3=new Intent(Productos.this,Productos.class);
                        //startActivity(intent3);
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

                        Intent intent4=new Intent(Productos.this,Loggin.class);
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

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Pollo_fragment();
            case 1:
                return new Refrescos_fragment();
            case 2:

                return new sandwiches_fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case (R.id.Miperfil):
                Intent intent= new Intent(this,Miperfil.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("contraseña",contraseña);
                intent.putExtra("correo",correo);
                finish();
                startActivity(intent);
            break;
            case(R.id.MainActivity):
                finish();
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
