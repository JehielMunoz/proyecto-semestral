package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registroUsuarios extends AppCompatActivity implements View.OnClickListener {


    Button retornoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        retornoBtn = (Button)findViewById(R.id.retornoUsuarios);

        retornoBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retornoUsuarios:
                Intent intentRU = new Intent(registroUsuarios.this, menuInicio.class);
                startActivity(intentRU);
        }
    }
}