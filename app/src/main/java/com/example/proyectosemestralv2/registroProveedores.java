package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registroProveedores extends AppCompatActivity implements View.OnClickListener {

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proveedores);

        returnButton = (Button)findViewById(R.id.retornoBtn);

        returnButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.retornoBtn:
                Intent intent= new Intent (registroProveedores.this, menuInicio.class);
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){

    }
}