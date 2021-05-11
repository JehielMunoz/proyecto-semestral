package com.example.proyectosemestralv2;

import android.content.Context;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class daoProveedores {


    Context context;
    Proveedor proveedor;
    ArrayList<Proveedor> list;

    public daoProveedores(){

    }

    public ArrayList<Proveedor> getProveedor(){
        ArrayList<Proveedor> list = new ArrayList<>();
        list.clear();

        return list;
    }

}

