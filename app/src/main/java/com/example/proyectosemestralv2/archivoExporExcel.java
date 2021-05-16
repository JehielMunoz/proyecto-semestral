package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class archivoExporExcel extends AppCompatActivity {
    Button guardarCambios, btnAtra, guardarExcelbtn, guardarPDFbtn;

    public archivoExporExcel(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo_expor_excel);

        guardarCambios= findViewById(R.id.guardarCambiosbtn);
        btnAtra = findViewById(R.id.btnAtras);

        btnAtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(archivoExporExcel.this,menuInicio.class));
                startActivity(intent);
            }
        });

    }
/*
    @Override
    public void onBackPressed(){

    }

*/
}