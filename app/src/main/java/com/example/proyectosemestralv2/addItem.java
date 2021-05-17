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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import com.google.firebase.database.FirebaseDatabase;

public class addItem extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText codigoEspecie, numFactura, desItem, rutProv, precioTotal;
    EditText fRecepcion, precioItem, ubiItem, obsIngreso, recurso;
    Button btnGuardar, btnCancelar, cargarCodigo;
    daoEspecie dao;
    Spinner estado_spinner;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        codigoEspecie = (EditText)findViewById(R.id.codigoEspecie);
        numFactura =    (EditText)findViewById(R.id.numFactura);
        desItem =       (EditText)findViewById(R.id.descripItem);
        rutProv =       (EditText)findViewById(R.id.rutProveedor);
        fRecepcion =    (EditText)findViewById(R.id.fechaRecepcion);
        precioItem =    (EditText)findViewById(R.id.precioItem);
        precioTotal=    (EditText)findViewById(R.id.precioItemIVA);
        ubiItem =       (EditText)findViewById(R.id.ubicacionItem);
        obsIngreso =    (EditText)findViewById(R.id.observacionIngreso);
        recurso =       (EditText)findViewById(R.id.recurso);
        btnGuardar =    (Button)findViewById(R.id.btnGuardar);
        btnCancelar =   (Button)findViewById(R.id.btnCancelar);
        estado_spinner = (Spinner)findViewById(R.id.estado_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        dao = new daoEspecie(this); //inicializa dao con el contexto actual

        btnGuardar.setOnClickListener(this);
        cargarCodigo.setOnClickListener(this);


        Query query = mDatabase.child("data").child("especies");
        query.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot!=null){
                    String cont = String.valueOf(dataSnapshot.getChildrenCount()+2);
                    codigoEspecie.setText(cont);
                }else{ System.out.println("Error en datasnapshot.");}
            }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addItem.this,menuInicio.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
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

                especie.setCodigo_correlativo(codigo_correlativo);
                especie.setEspecie(descripcion); especie.setCantidad(1);
                especie.setEstado(estado); especie.setPrecio_unitario(precio_unitario);
                especie.setPrecio_total(precio_total);//especie.setPrecio_total(9999999);
                especie.setFecha_recepcion(fecha_recepcion);
                especie.setNumero_factura(numero_factura); especie.setRut_proveedor(rut_proveedor);
                especie.setCentro_de_costo(centro_de_costo);//especie.setCentro_de_costo("TEST");
                especie.setUbicacion_actual(ubicacion);
                especie.setObservaciones(observacion);
                //if(!especie.isNull()){
                //    Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_LONG).show();
                //}else
                if(dao.creaEspecie(especie , codString)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Codigo ya existe", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void onBackPressed(){
        startActivity(new Intent (addItem.this, menuInicio.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}