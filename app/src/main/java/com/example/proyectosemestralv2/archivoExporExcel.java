package com.example.proyectosemestralv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class archivoExporExcel extends AppCompatActivity implements View.OnClickListener {
    Button guardarCambios, btnAtras, export, guardarPDFbtn;
    daoExportExcel daoExport;
    daoEspecie daoEspecie;
    private DatabaseReference mDatabase;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo_export_excel);

        export =        findViewById(R.id.btnExportExcel);
        guardarCambios= findViewById(R.id.guardarCambiosbtn);
        btnAtras =      findViewById(R.id.btnAtras);

        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();

        daoExport   = new daoExportExcel();
        daoEspecie  = new daoEspecie(this);

        export.setOnClickListener(this);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(archivoExporExcel.this,menuInicio.class));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExportExcel:
                try {
                    daoExport.export(mDatabase,this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this,"excel creado",Toast.LENGTH_LONG).show();
        }
    }
}