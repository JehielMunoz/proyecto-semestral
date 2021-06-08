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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class busquedaAvanzadaItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnBusquedaAvan, btnRetorno;
    Spinner estadoSpinner;
    EditText nroFactura, rutProveedor, fechaRecepcion, centroCostos, ubicacionEspecie;

    public List<String> resultados = new ArrayList<>();

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



                for (int i = 0; i > busquedas.size(); i++){
                    Query queryBA = mDatabase.child("data").child("especies").child(busquedas.get(i));
                    queryBA.addValueEventListener(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot != null){
                                String result =  String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(String.class));
                                resultados.add(result);
                            } else {
                                Toast.makeText(busquedaAvanzadaItems.this,"No hay registros en el sistema",Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError){}});
                }

                Set<String> hashset = new HashSet<String>(resultados);
                resultados.clear();
                resultados.addAll(hashset);
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

    public List<String> getResultados() {
        return resultados;
    }

    public void setResultados(List<String> resultados) {
        this.resultados = resultados;
    }
}