package com.example.practica_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.practica_sqlite.adaptadores.ListaContactosAdapter;
import com.example.practica_sqlite.db.DbContactos;
import com.example.practica_sqlite.db.DbHelper;
import com.example.practica_sqlite.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaContactos = findViewById(R.id.listaContactos);

        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(this);
        listaArrayContactos = new ArrayList<>();
        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContastos());
        listaContactos.setAdapter(adapter);

//        btnCrear.setOnClickListener(v -> {
//            DbHelper dbHelper = new DbHelper(this);
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            if (db != null) {
//                Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}