package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class busquedaAvanzadaItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnBusquedaAvan, btnRetorno;
    Spinner estadoSpinner;
    EditText codCorrelativo, nroFactura, rutProveedor, fechaRecepcion, precioUnitario, precioTotal,
             centroCostos, ubicacionEspecie;

    daoEspecie dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada_items);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        btnBusquedaAvan = (Button)findViewById(R.id.baBtnBusqueda);
        btnRetorno = (Button)findViewById(R.id.baBtnCancelar);

        codCorrelativo = (EditText)findViewById(R.id.baCodigoCorelativo);
        nroFactura = (EditText)findViewById(R.id.baNroFactura);
        rutProveedor = (EditText)findViewById(R.id.baRutProveedor);
        fechaRecepcion = (EditText)findViewById(R.id.baFechaRecepcion);

        centroCostos = (EditText)findViewById(R.id.baCentroCostos);
        ubicacionEspecie = (EditText)findViewById(R.id.baUbicacionEspecie);
        estadoSpinner = (Spinner)findViewById(R.id.baEstadoSpinner);

        ArrayAdapter<CharSequence> baAdapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        baAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(baAdapter);
        estadoSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baBtnCancelar:
                startActivity(new Intent(busquedaAvanzadaItems.this, menuInicio.class));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}