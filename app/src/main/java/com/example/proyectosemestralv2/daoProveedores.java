package com.example.proyectosemestralv2;

import android.content.Context;

import java.util.ArrayList;

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

