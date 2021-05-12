package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class busquedaProveedor extends AppCompatActivity implements View.OnClickListener{

    Button retornoBtn, buscarBtn, guardarCambiosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_proveedor);

        buscarBtn = (Button)findViewById(R.id.buscarButton);
        retornoBtn = (Button)findViewById(R.id.retornoButton);
        guardarCambiosBtn = (Button)findViewById(R.id.guardarCambiosButton);

        buscarBtn.setOnClickListener(this);
        retornoBtn.setOnClickListener(this);
        guardarCambiosBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buscarButton:
                break;
            case R.id.retornoButton:
                Intent intentRB = new Intent(busquedaProveedor.this, menuInicio.class);
                startActivity(intentRB);
            case R.id.guardarCambiosButton:
                break;
        }
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent (busquedaProveedor.this, menu_inicio.class));
        finish();
    }
}