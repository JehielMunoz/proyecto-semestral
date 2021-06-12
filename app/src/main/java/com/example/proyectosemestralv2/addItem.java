package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//import com.google.firebase.database.FirebaseDatabase;

public class addItem extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText codigoEspecie, numFactura, desItem, rutProv, precioTotal, codigoProd;
    EditText fRecepcion, precioItem, ubiItem, obsIngreso, recurso,ingcodigoprod,codigoBarra;
    Button btnGuardar, btnCancelar,ingScanerCam;
    daoEspecie dao;

    Spinner estado_spinner;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();


        codigoEspecie = (EditText) findViewById(R.id.ingCodigoEspecie);
        numFactura = (EditText) findViewById(R.id.ingNumFactura);
        desItem = (EditText) findViewById(R.id.ingDescripItem);
        rutProv = (EditText) findViewById(R.id.ingRutProveedor);
        fRecepcion = (EditText) findViewById(R.id.ingFechaRecepcion);
        precioItem = (EditText) findViewById(R.id.ingPrecioItem);
        precioTotal = (EditText) findViewById(R.id.ingPrecioItemIVA);
        ubiItem = (EditText) findViewById(R.id.ingUbicacionItem);
        obsIngreso = (EditText) findViewById(R.id.ingObservacionIngreso);
        recurso = (EditText) findViewById(R.id.ingRecurso);
        codigoBarra = (EditText) findViewById(R.id.ingCodigoBarra);
        btnGuardar = (Button) findViewById(R.id.ingBtnGuardar);
        btnCancelar = (Button) findViewById(R.id.ingBtnCancelar);
        estado_spinner = (Spinner) findViewById(R.id.ingEstadoSpinner);
        ingScanerCam= findViewById(R.id.ingScanerCam);



        ingScanerCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                IntentIntegrator intengrador = new IntentIntegrator(addItem.this);
                intengrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intengrador.setPrompt("Lector - BARRA Y QR - Escanea para agregar producto ");
                intengrador.setCameraId(0);
                intengrador.setBeepEnabled(true);
                intengrador.setBarcodeImageEnabled(true);
                intengrador.initiateScan();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        dao = new daoEspecie(this); //inicializa dao con el contexto actual

        btnGuardar.setOnClickListener(this);


        Query query = mDatabase.child("data").child("especies");
        query.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    String cont = String.valueOf(dataSnapshot.getChildrenCount() + 2);
                    codigoEspecie.setText(cont);
                } else {
                    System.out.println("Error en datasnapshot.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addItem.this, menuInicio.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ingBtnGuardar:
                Especie especie = new Especie();

                String codString = codigoEspecie.getText().toString();

                int codigo_correlativo = Integer.parseInt(codigoEspecie.getText().toString());
                int numero_factura = Integer.parseInt(numFactura.getText().toString());
                String descripcion = desItem.getText().toString();
                String rut_proveedor = rutProv.getText().toString();
                String fecha_recepcion = fRecepcion.getText().toString();
                int precio_unitario = Integer.parseInt(precioItem.getText().toString());
                int precio_total = Integer.parseInt(precioTotal.getText().toString());
                String estado = estado_spinner.getSelectedItem().toString();
                String centro_de_costo = recurso.getText().toString();
                String ubicacion = ubiItem.getText().toString();
                String observacion = obsIngreso.getText().toString();
                int codigo_barra = Integer.parseInt(codigoBarra.getText().toString());

                especie.setCodigo_correlativo(codigo_correlativo);
                especie.setEspecie(descripcion);
                especie.setCantidad(1);
                especie.setEstado(estado);
                especie.setPrecio_unitario(precio_unitario);
                especie.setPrecio_total(precio_total);//especie.setPrecio_total(9999999);
                especie.setFecha_recepcion(fecha_recepcion);
                especie.setNumero_factura(numero_factura);
                especie.setRut_proveedor(rut_proveedor);
                especie.setCentro_de_costo(centro_de_costo);//especie.setCentro_de_costo("TEST");
                especie.setUbicacion_actual(ubicacion);
                especie.setObservaciones(observacion);
                especie.setCodigo_barra(codigo_barra);
                //if(!especie.isNull()){
                //    Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_LONG).show();
                //}else
                if (dao.creaEspecie(especie, codString)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Codigo ya existe", Toast.LENGTH_LONG).show();
                }
                break;


        }
    }

    public void onBackPressed() {
        startActivity(new Intent(addItem.this, menuInicio.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    public void validarbtn(View v) {
        if (validar()) {
            Toast.makeText(this, "Valido correctamente ", Toast.LENGTH_LONG).show();
        }
    }


    public boolean validar() {
        boolean retorno = true;

        String c1 = numFactura.getText().toString();
        String c2 = rutProv.getText().toString();
        String c3 = desItem.getText().toString();
        String c4 = codigoEspecie.getText().toString();
        String c5 = codigoProd.getText().toString();
        String c6 = fRecepcion.getText().toString();
        String c7 = recurso.getText().toString();
        String c8 = precioItem.getText().toString();
        String c9 = precioTotal.getText().toString();
        String c10 = ubiItem.getText().toString();
        String c11 = obsIngreso.getText().toString();
        if (c1.isEmpty()) {
            numFactura.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c2.isEmpty()) {
            rutProv.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c3.isEmpty()) {
            desItem.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c4.isEmpty()) {
            codigoEspecie.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c5.isEmpty()) {
            codigoProd.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c6.isEmpty()) {
            fRecepcion.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c7.isEmpty()) {
            recurso.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c8.isEmpty()) {
            precioItem.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c9.isEmpty()) {
            precioTotal.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c10.isEmpty()) {
            ubiItem.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        if (c11.isEmpty()) {
            obsIngreso.setError("este campo no puede quedar vacio");
            retorno = false;
        }
        return retorno;
    }





    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                codigoProd.setText(result.getContents());


            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}



