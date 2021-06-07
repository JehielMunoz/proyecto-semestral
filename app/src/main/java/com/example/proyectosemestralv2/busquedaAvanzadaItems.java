package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    EditText codCorrelativo, nroFactura, rutProveedor, fechaRecepcion, precioUnitario, precioTotal,
             centroCostos, ubicacionEspecie;

    CharSequence text = "Sin coincidencias";
    daoEspecie dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    public Especie auxEspecie = new Especie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada_items);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        btnBusquedaAvan = (Button)findViewById(R.id.baBtnBusqueda);
        btnRetorno = (Button)findViewById(R.id.baBtnCancelar);

        codCorrelativo = (EditText)findViewById(R.id.baCodigoCorrelativo);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baBtnBusqueda:
                String codigoRelat = codCorrelativo.getText().toString();
                String numeroFactura = nroFactura.getText().toString();
                String rutProv = rutProveedor.getText().toString();
                String fecRecepcion = fechaRecepcion.getText().toString();
                String centCostos = centroCostos.getText().toString();
                String ubEspecie = ubicacionEspecie.getText().toString();

                ArrayList<String> itemsBusqueda = new ArrayList<>();
                ArrayList<Especie> coincidencias = new ArrayList<>();
                Query query = mDatabase.child("data").child("especies");
                String aux;
                /*
                array1 = lista de campos
                array2 = busqueda
                ArrayList<String> array3 = new ArrayList<>();
                dataSnapshot = el resultado del query
                for(int i = 0; i < dataSnapshot.getChildrenCount(); i++){
                    for(int x = 0; x < array1.size(); x++){
                        aux = String.valueOf(dataSnapshot.child(array1.get(0)).child(array2.get(0)));
                        if(aux!=null && !array3.contains(aux)){array3.add()}
                for(int z = 0; z < array3.size(); z++){
                    if(
                */
                // Campos de busqueda
                //"codigo_correlativo"  "numero_factura"    "rut_proveedor"
                // "fecha_recepcion"    "centro_de_costo"   "ubicacion_actual"

                if(codigoRelat != ""){ itemsBusqueda.add(codigoRelat);}
                if(numeroFactura != null){ itemsBusqueda.add(numeroFactura); }
                if(rutProv != null){ itemsBusqueda.add(rutProv); }
                if(fecRecepcion != null){ itemsBusqueda.add(fecRecepcion); }
                if(centCostos != null){ itemsBusqueda.add(centCostos); }
                if(ubEspecie != null){ itemsBusqueda.add(centCostos); }

                if(itemsBusqueda.size() > 1){
                    Toast.makeText(this,"Se han ingresado las busquedas ("+ itemsBusqueda.size()+")",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"No se detecta nada",Toast.LENGTH_LONG).show();
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

}