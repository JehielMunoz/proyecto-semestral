package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Registro_proveedores extends AppCompatActivity implements View.OnClickListener{

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proveedores);

        returnButton = (Button)findViewById(R.id.retorno);

        returnButton.setOnClickListener((View.OnClickListener) this);
    }


    public void onCLick(View v){
        switch (v.getId()){
            case R.id.retorno:
                Intent intent= new Intent (Registro_proveedores.this, menu_inicio.class);
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}