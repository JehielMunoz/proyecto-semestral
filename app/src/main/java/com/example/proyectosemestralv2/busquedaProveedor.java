package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class busquedaProveedor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText razonSocial, rutProveedor, telefonoProveedor, emailProveedor, rutBusca;
    Button retornoBtn, buscarBtn, guardarCambiosBtn;
    Spinner estado_spinner;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;
    ArrayList<Proveedor> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_proveedor);

        rutBusca =         (EditText)findViewById(R.id.bpRutBusca);
        razonSocial =       (EditText)findViewById(R.id.bpRazonSocial);
        rutProveedor =      (EditText)findViewById(R.id.bpRutProveedor);
        telefonoProveedor = (EditText)findViewById(R.id.bpTelefonoProveedor);
        emailProveedor =    (EditText)findViewById(R.id.bpEmailProveedor);

        buscarBtn =         (Button)findViewById(R.id.bpBuscarButton);
        retornoBtn =        (Button)findViewById(R.id.bpRetornoButton);
        guardarCambiosBtn = (Button)findViewById(R.id.bpGuardarCambiosButton);

        estado_spinner =    (Spinner)findViewById(R.id.bpEstadoSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        buscarBtn.setOnClickListener(this);
        retornoBtn.setOnClickListener(this);
        guardarCambiosBtn.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference("proveedores");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bpBuscarButton:
                String rutProv = rutBusca.getText().toString();
                Query query = mDatabase.child(rutProv);
                query.addValueEventListener(new ValueEventListener(){
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot!=null){
                            razonSocial.setText(dataSnapshot.child("razonSocial").getValue(String.class));
                            rutProveedor.setText(dataSnapshot.child("rut").getValue(String.class));
                            telefonoProveedor.setText(dataSnapshot.child("telefono").getValue(Long.class).toString());
                            emailProveedor.setText(dataSnapshot.child("email").getValue(String.class));
                        }else{
                            razonSocial.setText("Razon no encontrada");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){}});
                break;
            case R.id.bpRetornoButton:
                Intent intentRB = new Intent(busquedaProveedor.this, menuInicio.class);
                startActivity(intentRB);
                break;
            case R.id.bpGuardarCambiosButton:
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