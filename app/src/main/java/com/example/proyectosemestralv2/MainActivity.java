package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //comentario de prueba v2
//se declara las variabless de los botones
    EditText email, pass;
    Button login_btn, Register_btn;
    daoUser dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//se le asigna la variable a un boton y lo busca por la id de la pantalla
        login_btn = (Button) findViewById(R.id.login_btn);
        Register_btn = (Button) findViewById(R.id.SignButton);

//aqui lo hace un escucha al presionar el boton al llamado de onclick
        login_btn.setOnClickListener((View.OnClickListener) this);
        Register_btn.setOnClickListener((View.OnClickListener) this);

        //obteniendo datos de la pantalla

        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.contrasena_login);


    }

    /*
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            //la actividad de los botones de login y register de la pantalla de inicio
            case R.id.login_btn: Intent intentII = new Intent(MainActivity.this, menuInicio.class);
                    startActivity(intentII);
                    break;
            case R.id.Register_btn: Intent intent = new Intent( MainActivity.this,registroUsuarios.class);
            break;
            }
        }
        */


    //PRUEBA DE LOGIN VERIFICACION
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                Intent intent = new Intent(MainActivity.this, menuInicio.class);
                startActivity(intent);
                break;

    /*
    esta bueno
                String mail = email.getText().toString();
                String password = pass.getText().toString();


                if (mail.equals("") || password.equals("")) {    //Si email o contraseña estan vacios nos pedirá que los completemos
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();  //Así se muestra un mensaje en android

                }
                else if
                (Toast.makeText(this,"", Toast.LENGTH_LONG).show();

                (dao.login(mail, passw ord)) {//Intentará hacer login (retorna true or false)


                    User loggedUser = dao.getUser(mail, password);  //Vamos a llenar un nuevo objeto User con el usuario que nos retorne la funcion getUser
                    Intent intent = new Intent(MainActivity.this, Home.class);  //Si es true, nos redirige al home
                    intent.putExtra("id", loggedUser.getId());  //Agregar información para enviar a la otra pantalla
                    startActivity(intent);

                } else {

                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
                break;
           */
        case R.id.SignButton:
                Intent i = new Intent(MainActivity.this, registroUsuarios.class);
                startActivity(i);
                break;
        }
    }
    //FINAL DE PRUEBA

    @Override
    public void onBackPressed(){

    }
    }
