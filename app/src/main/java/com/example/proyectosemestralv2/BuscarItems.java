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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button buscaEspecie;
    EditText codBusca;
    Button btnvolver, btnBusquedaAvanzada;
    EditText numFactura, rutProveedor, descEspecie, codEspecie, fechaRecepcion,
                precioUni, precioTot, centroCosto, ubiEspecie, obsEspecie;
    Spinner estado_spinner;
    daoEspecie dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_items);
        //DB
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
        //DB
        //TESTEO
        codBusca = (EditText)findViewById(R.id.biCodigoBusca);
        buscaEspecie = (Button)findViewById(R.id.biBuscaEspecie);
        btnBusquedaAvanzada = (Button)findViewById(R.id.biBtnBusqAvanzada);

        btnBusquedaAvanzada.setOnClickListener(this);
        buscaEspecie.setOnClickListener(this);

        //TESTEO
        numFactura =        (EditText)findViewById(R.id.biNumFactura);
        rutProveedor =      (EditText)findViewById(R.id.biRutProveedor);
        descEspecie =       (EditText)findViewById(R.id.biDescEspecie);
        codEspecie =        (EditText)findViewById(R.id.biCodEspecie);
        fechaRecepcion =    (EditText)findViewById(R.id.biFechaRecepcion);
        precioUni =         (EditText)findViewById(R.id.biPrecioUni);
        precioTot =         (EditText)findViewById(R.id.biPrecioTot);
        centroCosto =       (EditText)findViewById(R.id.biCentroCosto);
        ubiEspecie =        (EditText)findViewById(R.id.biUbiEspecie);
        obsEspecie =        (EditText)findViewById(R.id.biObsEspecie);

        estado_spinner =    (Spinner)findViewById(R.id.biEstadoSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);


        Query query = mDatabase.child("data").child("especies").child("74");
        query.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot!=null){
                    numFactura.setText(String.valueOf(dataSnapshot.child("numero_factura").getValue(long.class)));
                    rutProveedor.setText(dataSnapshot.child("rut_proveedor").getValue(String.class));
                    descEspecie.setText(dataSnapshot.child("especie").getValue(String.class));
                    codEspecie.setText(String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(long.class)));
                    fechaRecepcion.setText(dataSnapshot.child("fecha_recepcion").getValue(String.class));
                    precioUni.setText(String.valueOf(dataSnapshot.child("precio_unitario").getValue(long.class)));
                    precioTot.setText(String.valueOf(dataSnapshot.child("precio_total").getValue(long.class)));
                    centroCosto.setText(dataSnapshot.child("centro_de_costo").getValue(String.class));
                    ubiEspecie.setText(dataSnapshot.child("ubicacion_actual").getValue(String.class));
                    obsEspecie.setText(dataSnapshot.child("observaciones").getValue(String.class));
                }else{ System.out.println("Error en datasnapshot.");}
            }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});


        dao = new daoEspecie(this); //inicializa dao con el contexto actual
        btnvolver = (Button)findViewById(R.id.biBtnVolver);
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(BuscarItems.this,menuInicio.class));
                startActivity(intent);
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.biBuscaEspecie:
                // !!!!! NO BORRAR !!!!!!
                //String cod = codBusca.getText().toString();
                /*Query query = mDatabase.child("data").child("especies").child("74");
                query.addValueEventListener(new ValueEventListener(){
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot!=null){
                            numFactura.setText(dataSnapshot.child("numero_factura").getValue(String.class));
                            rutProveedor.setText(dataSnapshot.child("rut_proveedor").getValue(String.class));
                            descEspecie.setText(dataSnapshot.child("especie").getValue(String.class));
                            codEspecie.setText(dataSnapshot.child("codigo_correlativo").getValue(String.class));
                            fechaRecepcion.setText(dataSnapshot.child("fecha_recepcion").getValue(String.class));
                            precioUni.setText(dataSnapshot.child("precio_unitario").getValue(String.class));
                            precioTot.setText(dataSnapshot.child("precio_total").getValue(String.class));
                            centroCosto.setText(dataSnapshot.child("centro_de_costo").getValue(String.class));
                            ubiEspecie.setText(dataSnapshot.child("ubicacion_actual").getValue(String.class));
                            obsEspecie.setText(dataSnapshot.child("observaciones").getValue(String.class));
                        }else{ System.out.println("Error en datasnapshot.");}
                    }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});*/
                // !!!!! NO BORRAR !!!!!!
                break;
            case R.id.biBtnBusqAvanzada:
                // Intent intentBA = new Intent (BuscarItems.this, busquedaAvanzadaItems.class);
                startActivity(new Intent (BuscarItems.this, busquedaAvanzadaItems.class) /* intentBA */);
                break;
        }
    }

    public void onBackPressed(){
        startActivity(new Intent (BuscarItems.this, menuInicio.class));
        finish();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}