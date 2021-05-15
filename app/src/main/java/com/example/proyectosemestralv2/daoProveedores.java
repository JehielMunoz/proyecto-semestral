package com.example.proyectosemestralv2;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class daoProveedores {
    Context context;
    Proveedor proveedor;
    String urlDb = "https://proyectoi-invedu-default-rtdb.firebaseio.com/";
    private DatabaseReference mDatabase;
    public daoProveedores(){}
    public daoProveedores(Context context) {
        this.context = context;
    }

    public boolean creaProveedor(Proveedor proveedor, String rut){
        mDatabase.child("proveedores").child(rut).setValue(proveedor);
        return true;
    }

    public void buscaProveedor(String rut){

    }
}
/*
        this.id = id;
        this.telefono = telefono;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.email = email;
        this.estado = estado;
 */
