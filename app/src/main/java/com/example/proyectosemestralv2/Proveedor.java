package com.example.proyectosemestralv2;

public class Proveedor {

    int id, telefono;
    String razon_social, rut_proveedor, email;
    boolean estado;

    public Proveedor(int id, int telefono, String razon_social, String rut_proveedor, String email, boolean estado) {
        this.id = id;
        this.telefono = telefono;
        this.razon_social = razon_social;
        this.rut_proveedor = rut_proveedor;
        this.email = email;
        this.estado = estado;
    }

    public boolean isNull(){
        if (id == 0 || telefono == 0 || rut_proveedor.equals("") || razon_social.equals("") || email.equals("")){
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRazonSocial() {
        return razon_social;
    }

    public void setRazonSocial(String razonSocial) {
        this.razon_social = razonSocial;
    }

    public String getRut() {
        return rut_proveedor;
    }

    public void setRut(String rut) {
        this.rut_proveedor = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
