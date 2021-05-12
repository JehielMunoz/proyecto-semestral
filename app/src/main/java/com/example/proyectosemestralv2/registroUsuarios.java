package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registroUsuarios extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent (registroUsuarios.this, menu_inicio.class));
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}