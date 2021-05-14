package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuInicio extends AppCompatActivity implements View.OnClickListener {

    Button ingresoItems, buscarItemsBtn, registroProvedorBtn, buscarProvedor, exportarDatos,generarActas,
                    solicitudAdmin, cerrarSesion, registroUsuarios,atrasPrueba  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        ingresoItems =findViewById(R.id.ingresoItemsBtn);
        registroProvedorBtn =findViewById(R.id.registroProvedorBtn);
        registroUsuarios =findViewById(R.id.registroUsuariosBtn);
        buscarProvedor =findViewById(R.id.buscarProvedorBtn);
        buscarItemsBtn =findViewById(R.id.buscarItemsBtn);
        atrasPrueba =findViewById(R.id.atrasPrueba);


        buscarProvedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBP= new Intent (menuInicio.this, busquedaProveedor.class);
                startActivity(intentBP);
            }
        });



        buscarItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




//responde bien
        //esta es una clase anonima
        ingresoItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (menuInicio.this, addItem.class);
                startActivity(intent);
            }//OK
        });

//responde bien
        registroProvedorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (menuInicio.this, registroProveedores.class);
                startActivity(intent);
            }
        });

//responde bien
        registroUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (menuInicio.this, registroUsuarios.class);
                startActivity(intent);
            }
        });

//**************************************************************************************************


    }

    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()){
            //case R.id.buscarItemsBtn:
                //Intent inentBI= new Intent (menu_inicio.this,)
            case R.id.registroUsuariosBtn:
                Intent intentRU= new Intent (menuInicio.this, registroUsuarios.class);
                startActivity(intentRU);
            case R.id.buscarProvedorBtn:
                Intent intentBP= new Intent (menuInicio.this, busquedaProveedor.class);
                startActivity(intentBP);
        }
    }*/


    }
    @Override
    public void onBackPressed(){
    }

}