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

        returnButton = (Button) findViewById(R.id.retorno);
        guardarProveedor = (Button) findViewById(R.id.guardarProv);
        razonSocialProv = (EditText) findViewById(R.id.razonSocial);
        rutProveedor = (EditText) findViewById(R.id.rut);
        telefonoProvedor = (EditText) findViewById(R.id.telefonoProveedor);
        emailProvedor = (EditText) findViewById(R.id.emailProveedor);

        dao = new daoProveedores(this);

        returnButton.setOnClickListener(this);
        guardarProveedor.setOnClickListener(this);

        Query query = mDatabase.child("data").child("proveedores");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.retorno:
                Intent intent= new Intent (registroProveedores.this, menuInicio.class);
                startActivity(intent);
                break;
            case R.id.guardarProv:
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
                    Intent intentCP = new Intent(registroProveedores.this, MainActivity.class);
                    startActivity(intentCP);
                    finish();
                } else {
                    Toast.makeText(this,"Codigo ya existe", Toast.LENGTH_LONG).show();
                }
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