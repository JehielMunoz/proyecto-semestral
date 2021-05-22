package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class registroProveedores extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {

    Button returnButton, guardarProveedor;
    EditText razonSocialProv, rutProveedor, telefonoProvedor, emailProvedor;
    daoProveedores dao;

    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proveedores);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        returnButton = (Button) findViewById(R.id.rpRetorno);
        guardarProveedor = (Button) findViewById(R.id.rpGuardarProv);
        razonSocialProv = (EditText) findViewById(R.id.rpRazonSocialIng);
        rutProveedor = (EditText) findViewById(R.id.rpRutIng);
        telefonoProvedor = (EditText) findViewById(R.id.rpNroTelefonicoIng);
        emailProvedor = (EditText) findViewById(R.id.rpCorreoIng);

        dao = new daoProveedores(this);

        returnButton.setOnClickListener(this);
        guardarProveedor.setOnClickListener(this);

        Query query = mDatabase.child("proveedores");
//        query.addValueEventListener(new ValueEventListener(){
//            public void onDataChange(DataSnapshot dataSnapshot){
//                if(dataSnapshot!=null){
//                    String cont = String.valueOf(dataSnapshot.getChildrenCount()+2);
//                    rutProveedor.setText(cont);
//                }else{ System.out.println("Error en datasnapshot.");}
//            }@Override public void onCancelled(@NonNull DatabaseError databaseError){}});

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rpRetorno:
                Intent intent= new Intent (registroProveedores.this, menuInicio.class);
                startActivity(intent);
                break;
            case R.id.rpGuardarProv:
                Proveedor proveedor = new Proveedor();

                String razonSocial = razonSocialProv.getText().toString();
                String rut = rutProveedor.getText().toString();
                Integer telefono = Integer.parseInt(telefonoProvedor.getText().toString());
                String email = emailProvedor.getText().toString();

                proveedor.setRazonSocial(razonSocial);
                proveedor.setEmail(email);
                proveedor.setTelefono(telefono);
                proveedor.setRut(rut);
                proveedor.setEstado(true);

                if(dao.creaProveedor(proveedor, rut)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    // Intent intentCP = new Intent(registroProveedores.this, MainActivity.class);
                    // startActivity(intentCP);
                    // finish();
                } else {
                    Toast.makeText(this,"Proveedor ya esta registrado", Toast.LENGTH_LONG).show();
                }
                // break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed(){

    }
}