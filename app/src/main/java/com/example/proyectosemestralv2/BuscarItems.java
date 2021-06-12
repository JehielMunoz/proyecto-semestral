package com.example.proyectosemestralv2;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BuscarItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button buscaEspecie, biScanerCam;
    EditText codBusca;
    Button btnvolver, btnBusquedaAvanzada;
    EditText biCodigoBusca;
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
        biScanerCam = findViewById(R.id.biScanerCam);
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
        biCodigoBusca =     (EditText)findViewById(R.id.biCodigoBusca);
        estado_spinner =    (Spinner)findViewById(R.id.biEstadoSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        dao = new daoEspecie(this); //inicializa dao con el contexto actual
        btnvolver = (Button)findViewById(R.id.biBtnVolver);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(BuscarItems.this,menuInicio.class));
                startActivity(intent);
            }
        });
        biScanerCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                IntentIntegrator intengrador = new IntentIntegrator(BuscarItems.this);
                intengrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intengrador.setPrompt("Lector - CDP");
                intengrador.setCameraId(0);
                intengrador.setBeepEnabled(true);
                intengrador.setBarcodeImageEnabled(true);
                intengrador.initiateScan();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.biBuscaEspecie:

                String cod = biCodigoBusca.getText().toString();
                Query query = mDatabase.child("data").child("especies").child(cod);
                query.addValueEventListener(new ValueEventListener(){
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot!=null) {
                            String control = String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(long.class));
                            if (!control.equals("null")){
                                String bdNumFactura =   String.valueOf(dataSnapshot.child("numero_factura").getValue(long.class));
                                String bdRutProveedor = String.valueOf(dataSnapshot.child("rut_proveedor").getValue(String.class));
                                String bdDescEspecie =  String.valueOf(dataSnapshot.child("especie").getValue(String.class));
                                String bdCodEspecie =   String.valueOf(dataSnapshot.child("codigo_correlativo").getValue(long.class));
                                String bdFRecep =       String.valueOf(dataSnapshot.child("fecha_recepcion").getValue(String.class));
                                String bdPrecioUni =    String.valueOf(dataSnapshot.child("precio_unitario").getValue(long.class));
                                String bdPrecioTot =    String.valueOf(dataSnapshot.child("precio_total").getValue(long.class));
                                String bdCentroCosto =  String.valueOf(dataSnapshot.child("centro_de_costo").getValue(String.class));
                                String bdUbiEspecie =   String.valueOf(dataSnapshot.child("ubicacion_actual").getValue(String.class));
                                String bdObsEspecie =   String.valueOf(dataSnapshot.child("observaciones").getValue(String.class));

                                numFactura.setText(bdNumFactura);
                                rutProveedor.setText(bdRutProveedor);
                                descEspecie.setText(bdDescEspecie);
                                codEspecie.setText(bdCodEspecie);
                                fechaRecepcion.setText(bdFRecep);
                                precioUni.setText(bdPrecioUni);
                                precioTot.setText(bdPrecioTot);
                                centroCosto.setText(bdCentroCosto);
                                ubiEspecie.setText(bdUbiEspecie);
                                obsEspecie.setText(bdObsEspecie);
                            }else{
                                Toast.makeText(BuscarItems.this,"Codigo no existe en la base de datos.",Toast.LENGTH_LONG).show();
                                AlertDialog.Builder alerta = new AlertDialog.Builder(BuscarItems.this);
                                alerta.setMessage("desea agregar items al sistema? ")
                                        .setCancelable(false)
                                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent= new Intent(new Intent(BuscarItems.this,addItem.class));
                                                startActivity(intent);
                                                finish();
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog titulo = alerta.create();
                                titulo.setTitle("Salida");
                                titulo.show();
                            }
                        }
                    }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});
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



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                biCodigoBusca.setText(result.getContents());

            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}