package com.example.proyectosemestralv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.*;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

class daoEspecie {
    Context context;
    Especie especie; //Declaramos objeto de tipo especie
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;

    public daoEspecie() {}

    public daoEspecie(Context context) {
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
    }

    public void leeEspecie(DatabaseReference mDatabase, String codString){
        ValueEventListener especieListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Especie especie = dataSnapshot.getValue(Especie.class);
                String msg = especie.getEspecie().toString();
                System.out.println();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        //Especie especie = mDatabase.child("data").child(codString);
    }
    public boolean creaEspecie(Especie especie, String codString) {
        //if (search(especie.getCodigo_correlativo()) == 0) {
        mDatabase.child("data").child(codString).setValue(especie);
        return true;
        //}
        //return false;
    }/*
    public int search(int Codigo_correlativo ){
        int count = 0;
        DataSnapshot dataSnapshot.child("data/codigo_correlativo/").getValue(dataSnapshot.class);
        for(DataSnapshot especieSnap : dataSnapshot.getChildren()){

        for(Especie especie : list){ // : significa que recorre todos los usuarios en la lista
            if(especie.getCodigo_correlativo() == Codigo_correlativo){
                count++;
            }
        }
        return count;
    }
}

     public ArrayList<User> getUsers(){
        ArrayList<User> list = new ArrayList<User>();
        list.clear(); //limpiar en caso de caché
        Cursor cursor = conn.rawQuery("SELECT * FROM users",null);

        if(cursor != null){           //si el cursos no está vacio y
            if(cursor.moveToFirst()){ // se puede posicionar en el primer elemento
                do{
                    User user = new User();
                    user.setId(cursor.getInt(0)); //recibe desde la tabla en la base de datos
                    user.setName(cursor.getString(1)); //ordenado dependiendo de la tabla
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    list.add(user);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    public boolean login(String email, String password){
        boolean success; //defecto falso
        Cursor cursor = conn.rawQuery("Select * from users",null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    if(cursor.getString(2).equals(email) && cursor.getString(3).equals(password)){
                        success = true;
                        break;
                    }
                }while(cursor.moveToNext());
                if(success=true){
                    cursor.close();
                    return success;
                }
            }
            cursor.close();
        }
        return false;
    }
    public int search(String email){
        int count = 0;
        list = getUsers();
        for(User selectedUser : list){ // : significa que recorre todos los usuarios en la lista
            if(selectedUser.getEmail().equals(email)){
                count++;
            }
        }
        return count;
    }
    public User getUser(String email, String password){
        list = getUsers();
        for(User user:list){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public User getUserById(int id){
        list = getUsers();
        for(User user:list){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

  */
}

