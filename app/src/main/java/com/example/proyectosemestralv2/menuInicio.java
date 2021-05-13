package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuInicio extends AppCompatActivity implements View.OnClickListener {

    Button ingresoItemsBtn, buscarItemsBtn, registroProvedorBtn, buscarProvedorBtn, exportarDatosBtn,generarActasBtn,
                    solicitudAdminBtn, cerrarSesionBtn, registroUsuariosBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        ingresoItemsBtn = (Button)findViewById(R.id.ingresoItemsBtn);
        registroProvedorBtn = (Button)findViewById(R.id.registroProvedorBtn);
        registroUsuariosBtn = (Button)findViewById(R.id.registroUsuariosBtn);
        buscarProvedorBtn = (Button)findViewById(R.id.buscarProvedorBtn);
        buscarItemsBtn = (Button)findViewById(R.id.buscarItemsBtn);

        ingresoItemsBtn.setOnClickListener(this);
        registroProvedorBtn.setOnClickListener(this);
        registroUsuariosBtn.setOnClickListener(this);
        buscarProvedorBtn.setOnClickListener(this);
        buscarItemsBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.buscarItemsBtn:
                //Intent inentBI= new Intent (menu_inicio.this,)
            case R.id.ingresoItemsBtn:
                Intent intentII= new Intent (menuInicio.this, addItem.class);
                startActivity(intentII);
            case R.id.registroProvedorBtn:
                Intent intentRP= new Intent (menuInicio.this, registroProveedores.class);
                startActivity(intentRP);
            case R.id.registroUsuariosBtn:
                Intent intentRU= new Intent (menuInicio.this, registroUsuarios.class);
                startActivity(intentRU);
            case R.id.buscarProvedorBtn:
                Intent intentBP= new Intent (menuInicio.this, busquedaProveedor.class);
                startActivity(intentBP);
        }
    }

    @Override
    public void onBackPressed(){

    }
}