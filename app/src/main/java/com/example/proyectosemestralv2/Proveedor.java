package com.example.proyectosemestralv2;

public class Proveedor {

    int telefono;
    String razon_social, rut, email, Proveedor;
    String estado;
    public Proveedor(){}
    public Proveedor(int telefono, String razon_social, String rut,
                     String email, String estado) {
        this.telefono = telefono;
        this.razon_social = razon_social;
        this.rut = rut;
        this.email = email;
        this.estado = estado;
    }

    public boolean isNull(){
        if (telefono == 0 || rut.equals("") || razon_social.equals("") || email.equals("")){
            return false;
        }
        return true;
    }


    public String getProveedor() { return Proveedor; }

    public void setProveedor(String proveedor) { Proveedor = proveedor; }

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

    public String isEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}
