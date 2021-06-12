package com.example.proyectosemestralv2;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        mDatabase = FirebaseDatabase.getInstance(urlDb).getReference();
    }

    public void leeProveedor(DatabaseReference mDatabase, String rut){
        ValueEventListener proveedorListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Proveedor proveedor = dataSnapshot.getValue(Proveedor.class);
                String msg = proveedor.getProveedor().toString();
                System.out.println();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        //Especie especie = mDatabase.child("data").child(codString);
    }
    public int exist(String rut, DatabaseReference mDatabase) {
        final int[] existCtrl = {0};
        Query query = mDatabase.child("proveedores").child(rut);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    existCtrl[0] = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});
        System.out.println(existCtrl);
        return existCtrl[0];
    }
    public int update(Proveedor proveedor, String rut, DatabaseReference mDatabase){
        int createControl = 0;
        if(createControl == 0){mDatabase.child("proveedores").child(rut).setValue(proveedor); return 1;}
        else {return 0;}
    }
    public int creaProveedor(Proveedor proveedor, String rut, DatabaseReference mDatabase) {
        int createControl = 0;
        createControl = exist(rut,mDatabase);
        if(createControl == 0){mDatabase.child("proveedores").child(rut).setValue(proveedor); return 1;}
        else {return 0;}
    }
}