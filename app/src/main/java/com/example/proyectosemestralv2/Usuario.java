package com.example.proyectosemestralv2;

public class Usuario {

    String nombre, correo, contraseña;
    int id, telefono;

    public Usuario(String nombre, String correo, String contraseña, int telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public boolean isNull(){
        if( this.id == 0 || this.nombre.equals("") || this.correo.equals("") || this.contraseña.equals("") || this.telefono==0 ){
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
