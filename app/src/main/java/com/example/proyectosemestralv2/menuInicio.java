package com.example.proyectosemestralv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class menuInicio extends AppCompatActivity implements View.OnClickListener {

    CardView ingresoItemsBtn, buscarItemsBtn, registroProvedorBtn, buscarProvedorBtn,
            exportarDatosBtn, generarActasBtn, solicitudAdminBtn, cerrarSesionBtn,
            registroUsuariosBtn, buscarUserBtn, infoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        ingresoItemsBtn =       (CardView)findViewById(R.id.IngresoItemsBtn);
        registroProvedorBtn =   (CardView)findViewById(R.id.RegistroProvedorBtn);
        registroUsuariosBtn =   (CardView)findViewById(R.id.RegistroUsuariosBtn);
        buscarProvedorBtn =     (CardView)findViewById(R.id.BuscarProvedorBtn);
        buscarItemsBtn =        (CardView)findViewById(R.id.BuscarEspecieBtn);
        cerrarSesionBtn=        (CardView)findViewById(R.id.CerrarSesionBtn);
        exportarDatosBtn=       (CardView)findViewById(R.id.ExportarDatosBtn);
        buscarUserBtn=          (CardView)findViewById(R.id.BuscarUsuarioBtn);

        generarActasBtn =       (CardView)findViewById(R.id.GenerarActasBtn);
        //este boton tienen que mostrar el items cargado


        ingresoItemsBtn.setOnClickListener(this);
        registroProvedorBtn.setOnClickListener(this);
        registroUsuariosBtn.setOnClickListener(this);
        buscarProvedorBtn.setOnClickListener(this);
        //buscarItemsBtn.setOnClickListener(this);


        //se va a informacion de items
        /*
        infoItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, BuscarItems.class));
                startActivity(intent);
            }
        });
        */

        buscarUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,BuscarUsuario.class));
                startActivity(intent);
            }
        });

        cerrarSesionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,MainActivity.class));
                startActivity(intent);
            }
        });
        exportarDatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this,archivoExporExcel.class));
                startActivity(intent);
            }
        });

        generarActasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, archivo_export_acta_alta.class));
                startActivity(intent);
            }
        });

        buscarItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(menuInicio.this, BuscarItems.class));
                startActivity(intent);
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            //case R.id.BuscarItemsBtn:
                //startActivity(new Intent(menuInicio.this, BuscarItemsBtn.class));
            case R.id.IngresoItemsBtn:
                startActivity(new Intent(menuInicio.this, addItem.class));
                finish(); break;
            case R.id.RegistroProvedorBtn:
                startActivity(new Intent(menuInicio.this, registroProveedores.class));
                finish(); break;
            case R.id.RegistroUsuariosBtn:
                startActivity(new Intent(menuInicio.this, registroUsuarios.class));
                finish(); break;
            case R.id.BuscarProvedorBtn:
                startActivity(new Intent(menuInicio.this, busquedaProveedor.class));
                break;
        }
    }

}