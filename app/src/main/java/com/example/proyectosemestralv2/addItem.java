package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class add_item extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    public add_item(){
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent (add_item.this, menu_inicio.class));
        finish();
    }

    @Override
    public void onClick(View v) {}
}