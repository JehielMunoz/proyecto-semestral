package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registroProveedores extends AppCompatActivity  {

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proveedores);


//*******************************
        returnButton= findViewById(R.id.retorno);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (registroProveedores.this, menuInicio.class);
                startActivity(intent);
            }
        });
//*******************************

    }



 /*   @Override
    public void onBackPressed(){
    }
*/
}