package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julian on 25/10/2016.
 */
public class favoritosSQLiteHelper extends SQLiteOpenHelper{

    String sqlcreate3 = "CREATE TABLE favoritos (" +
            "idfavorito         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "producto            TEXT," +
            "descripcion        TEXT," +
            "usuario              TEXT)" ;




    public favoritosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlcreate3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favoritos");
        db.execSQL(sqlcreate3);

    }
}
