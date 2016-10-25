package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julian on 24/10/2016.
 */
public class productosSQLiteHelper extends SQLiteOpenHelper {

    String sqlcreate2 = "CREATE TABLE productos (" +
            "idproducto         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "producto            TEXT," +
            "descripcion         TEXT," +
            "precio              TEXT)" ;




    public productosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlcreate2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS productos");
        db.execSQL(sqlcreate2);

    }
}
