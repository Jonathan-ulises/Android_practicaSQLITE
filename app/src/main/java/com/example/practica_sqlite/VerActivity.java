package com.example.practica_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practica_sqlite.db.DbContactos;
import com.example.practica_sqlite.entidades.Contactos;
import com.google.android.material.textfield.TextInputEditText;

public class VerActivity extends AppCompatActivity {


    TextInputEditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar, btnEditar, btnEliminar;
    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        DbContactos dbContactos = new DbContactos(this);
        contacto = dbContactos.verContastos(id);

        if (contacto != null) {
            txtNombre.setEnabled(false);
            txtTelefono.setEnabled(false);
            txtCorreo.setEnabled(false);
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getTelefono());
            btnGuardar.setVisibility(View.INVISIBLE);
        }

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditarActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea eliminar este contacto?")
                    .setPositiveButton("SI", (dialog, which) -> {
                        if (dbContactos.eliminarContacto(id)) {
                            lista();
                        }
                    }).
                    setNegativeButton("NO", ((dialog, which) -> {

                    })).show();
        });
    }

    private void lista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}