package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class busquedaProveedor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText razonSocial, telefonoProveedor, emailProveedor, rutBusca;
    TextView rutProveedor;
    Button retornoBtn, buscarBtn, guardarCambiosBtn;
    Spinner estado_spinner;
    daoProveedores dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_proveedor);

        rutBusca =          (EditText)findViewById(R.id.bpRutBusca);
        razonSocial =       (EditText)findViewById(R.id.bpRazonSocial);
        rutProveedor =      (TextView) findViewById(R.id.bpRutProveedor);
        telefonoProveedor = (EditText)findViewById(R.id.bpTelefonoProveedor);
        emailProveedor =    (EditText)findViewById(R.id.bpEmailProveedor);

        buscarBtn =         (Button)findViewById(R.id.bpBuscarButton);
        retornoBtn =        (Button)findViewById(R.id.bpRetornoButton);
        guardarCambiosBtn = (Button)findViewById(R.id.bpGuardarCambiosButton);

        estado_spinner =    (Spinner)findViewById(R.id.bpEstadoSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estadoProveedor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);
        dao = new daoProveedores();
        buscarBtn.setOnClickListener(this);
        retornoBtn.setOnClickListener(this);
        guardarCambiosBtn.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bpBuscarButton:
                String rutProv = rutBusca.getText().toString();
                if(!rutProv.equals("")) {
                    Query query = mDatabase.child("proveedores").child(rutProv);
                    query.addValueEventListener(new ValueEventListener(){
                        public void onDataChange(DataSnapshot dataSnapshot){
                            if(dataSnapshot!=null) {
                                String controlRut = String.valueOf(dataSnapshot.child("rut").getValue(String.class));
                                if (!controlRut.equals("null")) {
                                    String bdrazonSocial = String.valueOf(dataSnapshot.child("razonSocial").getValue(String.class));
                                    String bdRutProveedor_BP = String.valueOf(dataSnapshot.child("rut").getValue(String.class));
                                    String bdTelefonoProv = String.valueOf(dataSnapshot.child("telefono").getValue(long.class));
                                    String bdEmailProv = String.valueOf(dataSnapshot.child("email").getValue(String.class));
                                    String bdEstado = String.valueOf(dataSnapshot.child("estado").getValue(String.class));
                                    razonSocial.setText(bdrazonSocial);
                                    rutProveedor.setText(bdRutProveedor_BP);
                                    telefonoProveedor.setText(bdTelefonoProv);
                                    emailProveedor.setText(bdEmailProv);
                                    if(bdEstado.equals("Activo")){estado_spinner.setSelection(0);}
                                    else{estado_spinner.setSelection(1);}
                                } else {
                                    Toast.makeText(busquedaProveedor.this, "Rut/Razón social no encontrada.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});
                    break;
                }else{
                    Toast.makeText(busquedaProveedor.this, "Debe ingresar un rut.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bpRetornoButton:
                Intent intentRB = new Intent(busquedaProveedor.this, menuInicio.class);
                startActivity(intentRB);
                finish();
                break;
            case R.id.bpGuardarCambiosButton:
                Proveedor proveedor = new Proveedor();
                // int telefono, String razon_social, String rut,
                //    String email, String estado

                Integer dbTelefono;
                String dbRazonSocial =  String.valueOf(razonSocial.getText());
                String dbRut =          String.valueOf(rutProveedor.getText());
                String telefonoCtrl =   String.valueOf(telefonoProveedor.getText());

                if(telefonoCtrl.equals("")){ dbTelefono = 0;}
                else{   dbTelefono =    Integer.parseInt(String.valueOf(telefonoProveedor.getText()));}
                String dbEmail =        String.valueOf(emailProveedor.getText());
                String dbEstado =       String.valueOf(estado_spinner.getSelectedItem());

                if(!dbRazonSocial.equals("") || !dbRut.equals("") || dbTelefono != 0 || !dbEmail.equals("")) {
                    proveedor.setRazonSocial(dbRazonSocial);
                    proveedor.setEmail(dbEmail);
                    proveedor.setTelefono(dbTelefono);
                    proveedor.setRut(dbRut);
                    proveedor.setEstado(dbEstado);
                    int creaProveedor = dao.update(proveedor, dbRut, mDatabase);
                    if (creaProveedor == 1) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Proveedor ya está registrado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent (busquedaProveedor.this, menuInicio.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}