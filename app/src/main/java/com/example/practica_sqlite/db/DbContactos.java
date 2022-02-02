package com.example.practica_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.practica_sqlite.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper{

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaContacto(String nombre, String telefono, String correo) {
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("email", correo);

           id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public ArrayList<Contactos> mostrarContastos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setEmail(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while(cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

    public Contactos verContastos(int id) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);
        if (cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setEmail(cursorContactos.getString(3));
        }

        cursorContactos.close();

        return contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', email = '" + correo + "' WHERE id = '"+id+"'");
            correcto = true;
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
