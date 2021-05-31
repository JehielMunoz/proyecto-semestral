package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class archivoExporExcel extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button guardarCambios, btnAtras, export, export_ubicacion;
    Spinner spinner;
    daoExportExcel daoExport;
    daoEspecie daoEspecie;
    private DatabaseReference mDatabase;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo_export_excel);

        export = findViewById(R.id.btnExportGralExcel);
        export_ubicacion = findViewById(R.id.btnExportUbiExcel);
        guardarCambios = findViewById(R.id.guardarCambiosbtn);
        btnAtras = findViewById(R.id.btnAtras);
        Spinner spinner = (Spinner) findViewById(R.id.exUbicacionExport);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        daoExport = new daoExportExcel();
        daoEspecie = new daoEspecie(this);

        //UBICACIONES//
        ArrayList<String> ubicaciones = daoExport.getUbi(mDatabase);

        ArrayAdapter<CharSequence> spinnerArrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, ubicaciones);
        spinner.setAdapter(spinnerArrayAdapter);
        //spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //UBICACIONES//

        spinner.setOnItemSelectedListener(this);
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
                    Toast.makeText(archivoExporExcel.this, "excel creado", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.exUbicacionExport:
                String ubicacion = spinner.getSelectedItem().toString();
                try {
                    daoExport.export(archivoExporExcel.this, mDatabase, ubicacion);
                    Toast.makeText(archivoExporExcel.this, "excel creado", Toast.LENGTH_LONG).show();
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