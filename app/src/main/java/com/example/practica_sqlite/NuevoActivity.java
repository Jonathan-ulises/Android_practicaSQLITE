package com.example.practica_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.practica_sqlite.db.DbContactos;
import com.google.android.material.textfield.TextInputEditText;

public class NuevoActivity extends AppCompatActivity {

    TextInputEditText txtNombre, txtTelefono, txtEmail;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            DbContactos dbContactos = new DbContactos(this);
            long id = dbContactos.insertaContacto(txtNombre.getText().toString(),
                    txtTelefono.getText().toString(), txtEmail.getText().toString());

            if (id > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                limpiar();
            } else {
                Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}