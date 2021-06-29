package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class archivo_export_acta_alta extends AppCompatActivity implements View.OnClickListener {

    Button retornoBtn, busquedaFacturaBtn, generarActaBtn;
    EditText busNomProyecto, busCentCostos, busEncarInventario,
             busRespMaterial, busUbicEspecie, busFecRecepcion;
    ListView listItemsFacturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo_export_acta_alta);

        retornoBtn = (Button) findViewById(R.id.aaBtnRetorno);
        busquedaFacturaBtn = (Button) findViewById(R.id.aaBtnBuscarFactura);
        generarActaBtn = (Button) findViewById(R.id.aaBtnGenerarActa);

        busNomProyecto = (EditText) findViewById(R.id.aaBusqNombreProyecto);
        busCentCostos = (EditText) findViewById(R.id.aaBusqCentroCostos);
        busEncarInventario = (EditText) findViewById(R.id.aaEncargadoInventario);
        busRespMaterial = (EditText) findViewById(R.id.aaResponsableMaterial);
        busUbicEspecie = (EditText) findViewById(R.id.aaUbEspecies);
        busFecRecepcion = (EditText) findViewById(R.id.aaFechaRecepcion);

        listItemsFacturas = (ListView) findViewById(R.id.aaItemsEncontrados);

        retornoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(archivo_export_acta_alta.this, menuInicio.class));
                startActivity(intent);
            }
        });
        busquedaFacturaBtn.setOnClickListener(this);
        generarActaBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    public void onBackPressed(){

    }
}