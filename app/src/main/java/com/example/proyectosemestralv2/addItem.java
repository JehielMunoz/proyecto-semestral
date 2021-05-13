package com.example.proyectosemestralv2;

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

public class addItem extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText codItem, numFactura, desItem, rutProv;
    EditText fRecepcion, precioItem, ubiItem, obsIngreso;
    Button btnGuardar, btnCancelar;
    daoEspecie dao;
    Spinner estado_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        codItem = (EditText) findViewById(R.id.codigoItem);
        numFactura = (EditText) findViewById(R.id.numFactura);
        desItem = (EditText) findViewById(R.id.descripItem);
        rutProv = (EditText) findViewById(R.id.rutProveedor);
        fRecepcion = (EditText) findViewById(R.id.fechaRecepcion);
        precioItem = (EditText) findViewById(R.id.precioItem);
        ubiItem = (EditText) findViewById(R.id.ubicacionItem);
        obsIngreso = (EditText) findViewById(R.id.observacionIngreso);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        estado_spinner = (Spinner)findViewById(R.id.estado_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        dao = new daoEspecie(this); //inicializa dao con el contexto actual

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                Especie especie = new Especie();
                String codString = codItem.getText().toString();

                int codigo_correlativo = Integer.parseInt(codItem.getText().toString());
                int numero_factura = Integer.parseInt(numFactura.getText().toString());
                String descripcion = desItem.getText().toString();
                String rut_proveedor = rutProv.getText().toString();
                String fecha_recepcion = fRecepcion.getText().toString();
                int precio_unitario = Integer.parseInt(precioItem.getText().toString());
                String estado = estado_spinner.getSelectedItem().toString();
                String ubicacion = ubiItem.getText().toString();
                String observacion = obsIngreso.getText().toString();

                especie.setCodigo_correlativo(codigo_correlativo);
                especie.setEspecie(descripcion); especie.setCantidad(1);
                especie.setEstado(estado); especie.setPrecio_unitario(precio_unitario);
                especie.setPrecio_total(9999999); especie.setFecha_recepcion(fecha_recepcion);
                especie.setNumero_factura(numero_factura); especie.setRut_proveedor(rut_proveedor);
                especie.setCentro_de_costo("TEST"); especie.setUbicacion_actual(ubicacion);
                especie.setObservaciones(observacion);
                System.out.println(especie.getCantidad()+especie.getCodigo_correlativo()+especie.getCentro_de_costo()+
                        especie.getEspecie()+especie.getEstado()+especie.getFecha_recepcion());
                //if(!especie.isNull()){
                //    Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_LONG).show();
                //}else
                if(dao.creaEspecie(especie, codString)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addItem.this,MainActivity.class);
                    startActivity(intent);
                    finish();
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