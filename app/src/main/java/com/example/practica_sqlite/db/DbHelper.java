package com.example.practica_sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "agenda.db";
    public static final String TABLE_CONTACTOS = "t_contactos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        db.execSQL("ALTER TABLE " + TABLE_CONTACTOS + " RENAME COLUMN nombre TO name");
//        db.execSQL("ALTER TABLE " + TABLE_CONTACTOS + " ADD COLUMN cp TEXT");
        // Si la tabla ya contenia informacion, en lugar de crear una tabla
        // hacer un ALTER TABLE sobre la tabla modificada
        onCreate(db);
    }


}
