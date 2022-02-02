package com.example.practica_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_sqlite.db.DbContactos;
import com.example.practica_sqlite.entidades.Contactos;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class EditarActivity extends AppCompatActivity {


    TextInputEditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;
    Contactos contacto;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

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

        final DbContactos dbContactos = new DbContactos(this);
        contacto = dbContactos.verContastos(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getEmail());
        }

        btnGuardar.setOnClickListener(v -> {
//            if (!Objects.requireNonNull(txtNombre.getText()).toString().equals("")) {
//
//            }
            if (!txtNombre.getText().toString().equals("")
                    && !txtTelefono.getText().toString().equals("") &&
                    !txtCorreo.getText().toString().equals("")) {

                correcto = dbContactos.editarContacto(id,
                        txtNombre.getText().toString(),
                        txtTelefono.getText().toString(),
                        txtCorreo.getText().toString());

                if (correcto) {
                    Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                    verRegistro();
                } else {
                    Toast.makeText(this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}