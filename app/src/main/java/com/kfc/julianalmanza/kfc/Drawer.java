package com.kfc.julianalmanza.kfc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Drawer extends AppCompatActivity {

    private String[] opciones = new String[] {"Menu Principal","Mi Perfil","Comidas","Cerrar_sesion"};
    private DrawerLayout drawerLayout;
    ListView lista;
    Fragment fragment;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawerLayout=(DrawerLayout) findViewById(R.id.contenedorPrincipal);
        lista=(ListView) findViewById(R.id.menuizquierda);
        lista.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1,opciones));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                        case 0: fragment = new Pollo_fragment();
                            break;
                        case 1: fragment = new Refrescos_fragment();
                            break;
                        case 2: fragment = new sandwiches_fragment();
                            break;
                        case 3:
                            Intent intent=new Intent(view.getContext(),MainActivity.class);

                        break;
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contenedorFrame,fragment).commit();
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
