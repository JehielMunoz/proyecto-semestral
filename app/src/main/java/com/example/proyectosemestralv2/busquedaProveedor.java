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
    EditText razonSocial, rutProveedor, telefonoProveedor, emailProveedor;
    Button retornoBtn, buscarBtn, guardarCambiosBtn;
    Spinner estado_spinner;

    private RecyclerView recyclerView;
    private daoProveedores dao;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;
    ArrayList<Proveedor> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_proveedor);

        razonSocial =       (EditText)findViewById(R.id.razonSocial);
        rutProveedor =      (EditText)findViewById(R.id.rutProveedor);
        telefonoProveedor = (EditText)findViewById(R.id.telefonoProveedor);
        emailProveedor =    (EditText)findViewById(R.id.emailProveedor);

        buscarBtn =         (Button)findViewById(R.id.buscarButton);
        retornoBtn =        (Button)findViewById(R.id.retornoButton);
        guardarCambiosBtn = (Button)findViewById(R.id.guardarCambiosButton);

        estado_spinner =    (Spinner)findViewById(R.id.estado_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);
        estado_spinner.setOnItemSelectedListener(this);

        buscarBtn.setOnClickListener(this);
        retornoBtn.setOnClickListener(this);
        guardarCambiosBtn.setOnClickListener(this);

        lista = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference("proveedores");
        //mDatabase.addListenerForSingleValueEvent(valueEventListener);
    }

/*    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            lista.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Proveedor proveedor = snapshot.getValue(Proveedor.class);
                    lista.add(proveedor);
                }

            }
        }
        @Override public void onCancelled(@NonNull DatabaseError databaseError) {}};

 */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buscarButton:
                String rutProv = rutProveedor.getText().toString();
                Query query = mDatabase.orderByChild("rut_proveedor").equalTo("rutProv");
                query.addValueEventListener(new ValueEventListener(){
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot!=null){
                            razonSocial.setText(dataSnapshot.child("razon_social").getValue(String.class));
                            rutProveedor.setText(dataSnapshot.child("rut_proveedor").getValue(String.class));
                            telefonoProveedor.setText(dataSnapshot.child("telefono").getValue(String.class));
                            emailProveedor.setText(dataSnapshot.child("email").getValue(String.class));
                        }else{
                            razonSocial.setText("Razon no encontrada");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){}});
                recreate();
            case R.id.retornoButton:
                Intent intentRB = new Intent(busquedaProveedor.this, menuInicio.class);
                startActivity(intentRB);
            case R.id.guardarCambiosButton:
                break;
        }
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent (busquedaProveedor.this, menu_inicio.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}