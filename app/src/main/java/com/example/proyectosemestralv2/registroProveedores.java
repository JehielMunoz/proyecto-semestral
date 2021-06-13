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

        returnButton = (Button) findViewById(R.id.rpRetorno);
        guardarProveedor = (Button) findViewById(R.id.rpGuardarProv);
        razonSocialProv = (EditText) findViewById(R.id.rpRazonSocialIng);
        rutProveedor = (EditText) findViewById(R.id.rpRutIng);
        telefonoProvedor = (EditText) findViewById(R.id.rpNroTelefonicoIng);
        emailProvedor = (EditText) findViewById(R.id.rpCorreoIng);

        dao = new daoProveedores(this);

        returnButton.setOnClickListener(this);
        guardarProveedor.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rpRetorno:
                Intent intent= new Intent (registroProveedores.this, menuInicio.class);
                startActivity(intent);
                break;

            case R.id.rpGuardarProv:

                Integer telefono; char valDv; String[] charsRut = new String[2];
                String razonSocial =    String.valueOf(razonSocialProv.getText());
                String rut =            String.valueOf(rutProveedor.getText());
                String telefonoCtrl =   String.valueOf(telefonoProvedor.getText());
                if(telefonoCtrl.equals("")){ telefono = 0;}
                else{   telefono =      Integer.parseInt(String.valueOf(telefonoProvedor.getText()));}
                String email =          String.valueOf(emailProvedor.getText());
                //String dbEstado =       String.valueOf();
                try{
                    if(rut.contains("-")){charsRut = rut.split("-",2);}
                    //if(!razonSocial.equals("") || !rut.equals("") || telefono != 0 || !email.equals("")){
                    if(razonSocial.equals("")){Toast.makeText(this, "Debe ingresar razon social.", Toast.LENGTH_LONG).show();}
                    else{
                        if(rut.equals("")){Toast.makeText(this, "Debe ingresar rut del proveedor.", Toast.LENGTH_LONG).show();}
                        else {
                            int valRut = Integer.parseInt(charsRut[0]);
                            if(charsRut[1].length()!=1){valDv = "x".charAt(0);}
                            else{valDv = charsRut[1].charAt(0);}
                            if (!dao.ValidarRut(valRut, valDv)){Toast.makeText(this, "Rut Invalido.", Toast.LENGTH_LONG).show();}
                            else{
                                if (telefono == 0) {
                                    Toast.makeText(this, "Debe ingresar telefono de contacto.", Toast.LENGTH_LONG).show();
                                } else {
                                    //|| !email.contains(".cl") || !email.contains(".org") || !email.contains(".net")
                                    //|| !email.contains(".COM") || !email.contains(".CL") || !email.contains(".ORG") || !email.contains(".NET"))
                                    if (email.equals("") || !(email.contains("@") &&
                                            (email.contains(".com") || email.contains(".cl") || email.contains(".org") || email.contains(".net") ||
                                             email.contains(".COM") || email.contains(".CL") || email.contains(".ORG") || email.contains(".NET")
                                            ))){
                                            Toast.makeText(this, "Debe ingresar correo de contacto valido.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Query query = mDatabase.child("proveedores");
                                        query.addValueEventListener(new ValueEventListener() {
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot != null) {
                                                    String ctrl = String.valueOf(dataSnapshot.child(rut).child("rut").getValue(String.class));
                                                    if (ctrl.equals("null") || ctrl.equals("")) {
                                                        Proveedor proveedor = new Proveedor();
                                                        proveedor.setRazonSocial(razonSocial);
                                                        proveedor.setEmail(email);
                                                        proveedor.setTelefono(telefono);
                                                        proveedor.setRut(rut);

                                                        mDatabase.child("proveedores").child(rut).setValue(proveedor);

                                                        Toast.makeText(registroProveedores.this, "Registro exitoso", Toast.LENGTH_LONG).show();

                                                    } else {
                                                        Toast.makeText(registroProveedores.this, "Proveedor ya existe.", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    System.out.println("Error en datasnapshot.");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }catch(Exception e){Toast.makeText(this, "Rut Invalido.", Toast.LENGTH_LONG).show();}
            break;
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