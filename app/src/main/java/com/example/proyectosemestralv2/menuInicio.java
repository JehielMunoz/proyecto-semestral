package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class menuInicio extends AppCompatActivity implements View.OnClickListener {

    Button  ingresoItemsBtn,
            buscarItemsBtn,
            registroProvedorBtn,
            buscarProvedorBtn,
            exportarDatosBtn,
            generarActasBtn,
            solicitudAdminBtn,
            cerrarSesionBtn,
            registroUsuariosBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        ingresoItemsBtn =       (Button)findViewById(R.id.IngresoItemsBtn);
        registroProvedorBtn =   (Button)findViewById(R.id.RegistroProvedorBtn);
        registroUsuariosBtn =   (Button)findViewById(R.id.RegistroUsuariosBtn);
        buscarProvedorBtn =     (Button)findViewById(R.id.BuscarProvedorBtn);
        buscarItemsBtn =        (Button)findViewById(R.id.BuscarItemsBtn);
        cerrarSesionBtn= (Button)findViewById(R.id.CerrarSesionBtn);
        exportarDatosBtn= (Button)findViewById(R.id.ExportarDatosBtn);

        ingresoItemsBtn.setOnClickListener(this);
        registroProvedorBtn.setOnClickListener(this);
        registroUsuariosBtn.setOnClickListener(this);
        buscarProvedorBtn.setOnClickListener(this);
        buscarItemsBtn.setOnClickListener(this);

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

    }

    public void onClick(View v){
        switch (v.getId()){
            //case R.id.BuscarItemsBtn:
                //Intent inentBI= new Intent (menu_inicio.this, buscar)
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
//
  //  public void onBackPressed(){
    //}
}