package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class archivo_export_acta_alta extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button retornoBtn, busquedaFacturaBtn, generarActaBtn;
    EditText busNomProyecto, busCentCostos, busEncarInventario,
             busRespMaterial, busUbicEspecie, busFecRecepcion,
             busNroFactura;
    ListView listItemsFacturas;

    ArrayList<String> resultadosNroFactura = new ArrayList<>();
    ArrayList<String> listado = new ArrayList<>();
    ArrayList<String> listadoActa = new ArrayList<>();

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference bbdd;

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
        busNroFactura = (EditText) findViewById(R.id.aaNroFacturaBusq);

        listItemsFacturas = (ListView) findViewById(R.id.aaItemsEncontrados);

        retornoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(archivo_export_acta_alta.this, menuInicio.class));
            }
        });
        busquedaFacturaBtn.setOnClickListener(this);
        generarActaBtn.setOnClickListener(this);

        listItemsFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String auxEleccion = resultadosNroFactura.get(position);
                if(!listadoActa.contains(auxEleccion)){
                    listadoActa.add(resultadosNroFactura.get(position));
                    Toast.makeText(archivo_export_acta_alta.this, "Ítem agregado del acta", Toast.LENGTH_LONG).show();
                    Log.i("CONTADOR","Resultados: "+listadoActa.size());
                } else {
                    for (int i = 0; i < resultadosNroFactura.size(); i++) {
                        if(listadoActa.get(i).equals(resultadosNroFactura.get(position))){
                            listadoActa.remove(i);
                            break;
                        }
                    }
                    Log.i("CONTADOR","Resultados: "+listadoActa.size());
                    Toast.makeText(archivo_export_acta_alta.this, "Ítem eliminado del acta", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aaBtnBuscarFactura:
                bbdd = FirebaseDatabase.getInstance(urlDb).getReference();
                String auxNroFactura = busNroFactura.getText().toString();
                if (auxNroFactura.isEmpty() || auxNroFactura.equals("")) {
                    Toast.makeText(this, "Ingrese un número de factura válido", Toast.LENGTH_LONG).show();
                } else {
                    Query queryBA = bbdd.child("data").child("especies");
                    queryBA.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String contBA;
                            for (int z = 0; z < dataSnapshot.getChildrenCount(); z++){
                                contBA = String.valueOf(z);
                                String nroFacturaItem = String.valueOf(dataSnapshot.child(contBA).child("numero_factura").getValue());
                                if (auxNroFactura.equals(nroFacturaItem)){
                                    String auxCod = String.valueOf(dataSnapshot.child(contBA).child("codigo_correlativo").getValue());
                                    String auxNombre = String.valueOf(dataSnapshot.child(contBA).child("especie").getValue(String.class));
                                    String auxIngreso = auxCod+". "+auxNombre;
                                    if (!listado.contains(auxIngreso)){
                                        listado.add(auxIngreso);
                                        resultadosNroFactura.add(auxCod);
                                    }
                                }
                            }
                            Log.i("CONTADOR","Resultados: "+resultadosNroFactura.size());
                        }
                        @Override public void onCancelled(@NonNull DatabaseError databaseError) {}});
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_expandable_list_item_1,
                            listado);
                    listItemsFacturas.setAdapter(arrayAdapter);
                }


            }
    }

    public void onBackPressed(){}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}