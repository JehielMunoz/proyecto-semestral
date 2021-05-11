package com.example.proyectosemestralv2;

public class Proveedor {

    int id, telefono;
    String razonSocial, rut, email;
    boolean estado;

    public Proveedor(int id, int telefono, String razonSocial, String rut, String email, boolean estado) {
        this.id = id;
        this.telefono = telefono;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.email = email;
        this.estado = estado;
    }

    public boolean isNull(){
        if (id == 0 | telefono == 0 | razonSocial.equals("") | rut.equals("") | email.equals("")){
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
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
