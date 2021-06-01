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
    public boolean exist(String rut, DatabaseReference mDatabase) {
        final boolean[] existCtrl = {false};
        Query query = mDatabase.child("proveedores").child(rut);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()!=0){
                    existCtrl[0] = true;}
                else {
                    existCtrl[0] = false;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});
        return existCtrl[0];
    }

    public boolean creaProveedor(Proveedor proveedor, String rut, DatabaseReference mDatabase) {
        final boolean[] createControl = {false};
        Query query = mDatabase.child("proveedores").child(rut);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()!=0){
                    createControl[0] = false;}
                else {
                    mDatabase.child("proveedores").child(rut).setValue(proveedor);
                    createControl[0] = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});
        return createControl[0];
    }
}