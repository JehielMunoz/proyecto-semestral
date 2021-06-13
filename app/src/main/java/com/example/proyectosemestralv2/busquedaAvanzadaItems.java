package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class busquedaAvanzadaItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnBusquedaAvan, btnRetorno;
    Spinner estadoSpinner;
    EditText nroFactura, rutProveedor, fechaRecepcion, centroCostos, ubicacionEspecie;

    public ArrayList<String> resultados = new ArrayList<>();

    CharSequence text = "Sin coincidencias";
    daoEspecie dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    public Especie auxEspecie = new Especie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada_items);

        btnBusquedaAvan = (Button)findViewById(R.id.baBtnBusqueda);
        btnRetorno = (Button)findViewById(R.id.baBtnCancelar);

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

        btnBusquedaAvan.setOnClickListener(this);
        btnRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(busquedaAvanzadaItems.this, BuscarItems.class));
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baBtnBusqueda:
                ArrayList<String> busquedas = new ArrayList<>();

                String numeroFactura = nroFactura.getText().toString();
                String rutProv = rutProveedor.getText().toString();
                String fecRecepcion = fechaRecepcion.getText().toString();
                String centCostos = centroCostos.getText().toString();
                String ubEspecie = ubicacionEspecie.getText().toString();
                String estado = estadoSpinner.getSelectedItem().toString();

                busquedas.add(numeroFactura); busquedas.add(estado);
                busquedas.add(rutProv); busquedas.add(fecRecepcion);
                busquedas.add(centCostos); busquedas.add(ubEspecie);


                for (int i = 0; i < busquedas.size(); i++) {
                    String auxBusquedas = busquedas.get(i);
                    if (auxBusquedas != ""){
                        Query queryBA = mDatabase.child("data").child("especies").child(auxBusquedas)/*.limitToFirst(5)*/;
                        queryBA.addValueEventListener(new ValueEventListener() {
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot != null) {
                                    String result = String.valueOf(dataSnapshot.child("especie").getValue(String.class));
                                    resultados.add(result);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) { } } );

                    }
                }

                if (resultados.size() == 0){
                    Toast.makeText(this, "No se encontraron coincidencias en sistema", Toast.LENGTH_SHORT).show();
                    resultados.clear();
                } else {
                    Toast.makeText(this, "Coincidencias obtenidas ("+ resultados.size()+")", Toast.LENGTH_SHORT).show();
                    // Intent intentLI = new Intent(busquedaAvanzadaItems.this, Lista_items.class);
                    // startActivity(intentLI);
                    resultados.clear();
                }
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

    public void onBackPressed(){

    }

}