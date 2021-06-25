package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class archivoExporExcel extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button btnAtras, export, export_ubicacion;
    Spinner ubi_spinner;
    daoExportExcel daoExport;
    daoEspecie daoEspecie;
    private DatabaseReference mDatabase;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    String ubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo_export_excel);

        export = findViewById(R.id.btnExportGralExcel);
        export_ubicacion = findViewById(R.id.btnExportUbiExcel);
        btnAtras = findViewById(R.id.btnAtras);

        ubi_spinner =   findViewById(R.id.exUbicacionExport);
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        daoExport = new daoExportExcel();
        daoEspecie = new daoEspecie(this);

        //UBICACIONES//
        //ArrayList<String> ubicaciones = daoExport.getUbi(mDatabase);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ubicaciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubi_spinner.setAdapter(adapter);
        ubi_spinner.setOnItemSelectedListener(this);


        /*
        ArrayAdapter<String> adapter = new ArrayAdapter(archivoExporExcel.this,  android.R.layout.simple_spinner_item, ubicaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubi_spinner.setAdapter(adapter);


        ubi_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ubicacion = ubicaciones.get(position);
                ubi_spinner.setSelection((adapter).getPosition(ubicaciones.get(position)));
                Toast.makeText(archivoExporExcel.this, ubi_spinner.getSelectedItem() + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //ubi_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> spinnerArrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, ubicaciones);
        spinner.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///String selection = ubicaciones.get(position);
                spinner.setSelection((spinnerArrayAdapter).getPosition(ubicaciones.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        //UBICACIONES//

        //spinner.setOnItemSelectedListener(this);
        export.setOnClickListener(this);
        export_ubicacion.setOnClickListener(this);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(archivoExporExcel.this, menuInicio.class));
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExportGralExcel:
                try {
                    daoExport.export(archivoExporExcel.this, mDatabase, "");
                    Toast.makeText(archivoExporExcel.this, "Excel creado", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnExportUbiExcel:
                try {
                    ubicacion = ubi_spinner.getSelectedItem().toString();
                    if(!ubicacion.equals("Seleccione ubicación")) {
                        daoExport.export(archivoExporExcel.this, mDatabase, ubicacion);
                        Toast.makeText(archivoExporExcel.this, "Excel creado", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}