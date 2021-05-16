package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BuscarUsuario extends AppCompatActivity {

    Button btnGuardarCambios, btnCancelarBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);
        btnCancelarBusqueda= findViewById(R.id.btnCancelarBusqueda);



        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscarUsuario.this,menuInicio.class);
                startActivity(intent);

            }
        });

            btnCancelarBusqueda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BuscarUsuario.this,menuInicio.class);
                    startActivity(intent);
                }
            });

    }
}