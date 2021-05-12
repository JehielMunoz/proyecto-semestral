package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //comentario de prueba v2
    Button login_btn, register_btn;
//se declara las variabless de los botones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//se le asigna la variable a un boton y lo busca por la id de la pantalla
        login_btn = (Button) findViewById(R.id.Login_btn);
        register_btn = (Button) findViewById(R.id.Register_btn);

//aqui lo hace un escucha al presionar el boton al llamado de onclick
        login_btn.setOnClickListener((View.OnClickListener) this);
        register_btn.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            //la actividad de los botones de login y register de la pantalla de inicio
            case R.id.Login_btn:
                startActivity(new Intent(MainActivity.this, menu_inicio.class));
            case R.id.Register_btn:
                startActivity(new Intent(MainActivity.this, registroUsuarios.class));
            }
        }
    @Override
    public void onBackPressed(){
        finish();
    }
}
