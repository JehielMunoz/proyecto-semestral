package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class busquedaAvanzadaItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnBusquedaAvan, btnRetorno;
    Spinner estadoSpinner;
    EditText nroFactura, rutProveedor, fechaRecepcion, centroCostos, ubicacionEspecie;

    ArrayList<String> resultados = new ArrayList<>();

    CharSequence text = "Sin coincidencias";
    daoEspecie dao;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada_items);

        btnBusquedaAvan = (Button)findViewById(R.id.baBtnBusqueda);
        btnRetorno = (Button)findViewById(R.id.baBtnCancelar);

        nroFactura = (EditText)findViewById(R.id.baNroFactura);
        rutProveedor = (EditText)findViewById(R.id.baRutProveedor);
        fechaRecepcion = (EditText)findViewById(R.id.baFechaRecepcion);
        centroCostos = (EditText)findViewById(R.id.baCentroCostos);
        ubicacionEspecie = (EditText)findViewById(R.id.baUbicacionEspecie);
        estadoSpinner = (Spinner)findViewById(R.id.baEstadoSpinner);

        ArrayAdapter<CharSequence> baAdapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        baAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(baAdapter);
        estadoSpinner.setOnItemSelectedListener(this);

        btnBusquedaAvan.setOnClickListener(v -> {
            ArrayList<String> busquedas     = new ArrayList<>();
            //Listas auxiliares
            ArrayList<String> bdAuxListOne    = new ArrayList<>();
            ArrayList<String> bdAuxListTwo    = new ArrayList<>();
            ArrayList<String> bdAuxListThree  = new ArrayList<>();
            ArrayList<String> bdAuxListFour   = new ArrayList<>();
            ArrayList<String> bdAuxListFive  = new ArrayList<>();
            ArrayList<String> bdAuxListSix   = new ArrayList<>();


            String rutProv = rutProveedor.getText().toString();
            String fecRecepcion = fechaRecepcion.getText().toString();
            String centCostos = centroCostos.getText().toString();
            String ubEspecie = ubicacionEspecie.getText().toString();
            String numFactura = nroFactura.getText().toString();
            String dbEstado = estadoSpinner.getSelectedItem().toString();

            mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
            busquedas.add(rutProv);     busquedas.add(fecRecepcion);
            busquedas.add(centCostos);  busquedas.add(ubEspecie);
            busquedas.add(numFactura);  busquedas.add(dbEstado);

            if(busquedas.size()!=0){
                Query queryBA = mDatabase.child("data").child("especies");
                queryBA.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String contBA;
                        for(int i = 1; i < dataSnapshot.getChildrenCount() + 1; i++){
                            contBA = String.valueOf(i);
                            String codCor =     String.valueOf(dataSnapshot.child(contBA).child("codigo_correlativo").getValue(long.class));
                            String auxOne =     String.valueOf(dataSnapshot.child(contBA).child("rut_proveedor").getValue(String.class));
                            String auxTwo =     String.valueOf(dataSnapshot.child(contBA).child("fecha_recepcion").getValue(String.class));
                            String auxThree =   String.valueOf(dataSnapshot.child(contBA).child("centro_de_costo").getValue(String.class));
                            String auxFour =    String.valueOf(dataSnapshot.child(contBA).child("ubicacion_actual").getValue(String.class));
                            String auxFive =    String.valueOf(dataSnapshot.child(contBA).child("numero_factura").getValue(long.class));
                            String auxSix =     String.valueOf(dataSnapshot.child(contBA).child("estado").getValue(String.class));


                            if(busquedas.get(0)!=""){if(auxOne.equals(busquedas.get(0))){bdAuxListOne.add(codCor);}}
                            if(busquedas.get(1)!=""){if(auxTwo.equals(busquedas.get(1))){bdAuxListTwo.add(codCor);}}
                            if(busquedas.get(2)!=""){if(auxThree.equals(busquedas.get(2))){bdAuxListThree.add(codCor);}}
                            if(busquedas.get(3)!=""){if(auxFour.equals(busquedas.get(3))){bdAuxListFour.add(codCor);}}
                            if(busquedas.get(4)!=""){if(auxFive.equals(busquedas.get(4))){bdAuxListFive.add(codCor);}}
                            if(busquedas.get(5)!=""){if(auxSix.equals(busquedas.get(5))){bdAuxListSix.add(codCor);}}
                        }

                        if(bdAuxListOne.size()!=0 && bdAuxListTwo.size()!=0){bdAuxListOne.retainAll(bdAuxListTwo);bdAuxListTwo.clear();}
                        if(bdAuxListOne.size()!=0 && bdAuxListThree.size()!=0){bdAuxListOne.retainAll(bdAuxListThree);bdAuxListThree.clear();}
                        if(bdAuxListOne.size()!=0 && bdAuxListFour.size()!=0){bdAuxListOne.retainAll(bdAuxListFour);bdAuxListFour.clear();}
                        if(bdAuxListOne.size()!=0 && bdAuxListFive.size()!=0){bdAuxListOne.retainAll(bdAuxListFive);bdAuxListFive.clear();}
                        if(bdAuxListOne.size()!=0 && bdAuxListSix.size()!=0){bdAuxListOne.retainAll(bdAuxListSix);bdAuxListSix.clear();}

                        if(bdAuxListTwo.size()!=0 && bdAuxListThree.size()!=0){bdAuxListTwo.retainAll(bdAuxListThree);bdAuxListThree.clear();}
                        if(bdAuxListTwo.size()!=0 && bdAuxListFour.size()!=0){bdAuxListTwo.retainAll(bdAuxListFour);bdAuxListFour.clear();}
                        if(bdAuxListTwo.size()!=0 && bdAuxListFive.size()!=0){bdAuxListTwo.retainAll(bdAuxListFive);bdAuxListFive.clear();}
                        if(bdAuxListTwo.size()!=0 && bdAuxListSix.size()!=0){bdAuxListTwo.retainAll(bdAuxListSix);bdAuxListSix.clear();}

                        if(bdAuxListThree.size()!=0 && bdAuxListFour.size()!=0){bdAuxListThree.retainAll(bdAuxListFour);bdAuxListFour.clear();}
                        if(bdAuxListThree.size()!=0 && bdAuxListFive.size()!=0){bdAuxListThree.retainAll(bdAuxListFive);bdAuxListFive.clear();}
                        if(bdAuxListThree.size()!=0 && bdAuxListSix.size()!=0){bdAuxListThree.retainAll(bdAuxListSix);bdAuxListSix.clear();}

                        if(bdAuxListFour.size()!=0 && bdAuxListFive.size()!=0){bdAuxListFour.retainAll(bdAuxListFive);bdAuxListFive.clear();}
                        if(bdAuxListFour.size()!=0 && bdAuxListSix.size()!=0){bdAuxListFour.retainAll(bdAuxListSix);bdAuxListSix.clear();}

                        if(bdAuxListFive.size()!=0 && bdAuxListSix.size()!=0){bdAuxListFive.retainAll(bdAuxListSix);bdAuxListSix.clear();}

                        if(bdAuxListOne.size()!=0){resultados.addAll(bdAuxListOne);}
                        if(bdAuxListTwo.size()!=0){resultados.addAll(bdAuxListTwo);}
                        if(bdAuxListThree.size()!=0){resultados.addAll(bdAuxListThree);}
                        if(bdAuxListFour.size()!=0){resultados.addAll(bdAuxListFour);}
                        if(bdAuxListFive.size()!=0){resultados.addAll(bdAuxListFive);}
                        if(bdAuxListSix.size()!=0){resultados.addAll(bdAuxListSix);}

                        //}

                    }@Override public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }

            for(String log : resultados)
            {
                Log.v("Tag",log);
            }

            if (resultados.size() != 0){
                Toast.makeText(this, "Coincidencias obtenidas ("+ resultados.size()+")", Toast.LENGTH_SHORT).show();
                Intent intentLI = new Intent(busquedaAvanzadaItems.this, Lista_items.class);
                intentLI.putStringArrayListExtra("resultados", resultados);
                startActivity(intentLI);
                resultados.clear();
            }
        });

        btnRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(busquedaAvanzadaItems.this, BuscarItems.class));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) { }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onBackPressed(){

    }

}