package com.example.proyectosemestralv2;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class menuInicio extends AppCompatActivity {


    CardView ingresoItemsBtn, buscarItemsBtn, registroProvedorBtn, buscarProvedorBtn,
            exportarDatosBtn, generarActasBtn, solicitudAdminBtn, cerrarSesionBtn,
            registroUsuariosBtn, buscarUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        ingresoItemsBtn =      findViewById(R.id.IngresoItemsBtn);
        registroProvedorBtn =   findViewById(R.id.RegistroProvedorBtn);
        registroUsuariosBtn =  findViewById(R.id.RegistroUsuariosBtn);
        buscarProvedorBtn =     findViewById(R.id.BuscarProvedorBtn);
        buscarItemsBtn =       findViewById(R.id.BuscarItemsBtn);
        cerrarSesionBtn=        findViewById(R.id.CerrarSesionBtn);
        exportarDatosBtn=      findViewById(R.id.ExportarDatosBtn);
        buscarUserBtn=      findViewById(R.id.BuscarUsuarioBtn);
        generarActasBtn = findViewById(R.id.GenerarActasBtn);
        //este boton tienen que mostrar el items cargado

        ingresoItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, addItem.class));
                startActivity(intent);
                finish();
            }
        });
        registroProvedorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, registroProveedores.class));
                startActivity(intent);
                finish();
            }
        });
        registroUsuariosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, registroUsuarios.class));
                startActivity(intent);
                finish();
            }
        });
        buscarProvedorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,busquedaProveedor.class));
                startActivity(intent);
                finish();
            }
        });
        buscarItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, BuscarItems.class));
                startActivity(intent);
                finish();
            }
        });

        cerrarSesionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,MainActivity.class));
                startActivity(intent);
                finish();
            }
        });


        exportarDatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,archivoExporExcel.class));
                startActivity(intent);
                finish();
            }
        });

        buscarUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,BuscarUsuario.class));
                startActivity(intent);
                finish();
            }
        });
        generarActasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,BuscarUsuario.class));
                startActivity(intent);
                finish();
            }
        });


    }

}
