package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class addItem extends AppCompatActivity {

    Button  cancelarbtn, aceptarbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        cancelarbtn =findViewById(R.id.btnCancelar);



    }

    public addItem(){
    }





    @Override
    public void onBackPressed(){

    }

}