package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class infoItems extends AppCompatActivity {
         Button btnvolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_items);

        btnvolver = (Button)findViewById(R.id.btnvolver);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(new Intent(infoItems.this,menuInicio.class));
                startActivity(intent);
            }
        });





    }
    public void onBackPressed(){
        }
}