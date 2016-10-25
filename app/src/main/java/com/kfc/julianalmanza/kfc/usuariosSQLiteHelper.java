package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julian on 20/10/2016.
 */
public class usuariosSQLiteHelper extends SQLiteOpenHelper {

    //private String Nombre_Base_datos="AgendaBD";
    //private int Data_Version=1;

    String sqlcreate = "CREATE TABLE usuarios (" +
            "idusuario         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre            TEXT," +
            "contraseña        TEXT," +
            "correo            TEXT," +
            "activo            TEXT)" ;




    public usuariosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlcreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL(sqlcreate);

    }
}
