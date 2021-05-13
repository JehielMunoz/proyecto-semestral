package com.example.proyectosemestralv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Especie {

    int Id, Codigo_correlativo, Cantidad, Precio_unitario, Precio_total;
    int Numero_factura;
    String Especie, Estado, Rut_proveedor, Centro_de_costo, Ubicacion_actual;
    String Observaciones;
    String Fecha_recepcion;

    public Especie(){}
    public Especie(
            int id, int codigo_correlativo, String especie,
            int cantidad, String estado, int precio_unitario,
            int precio_total, String fecha_recepcion, int numero_factura, String rut_proveedor,
            String centro_de_costo, String ubicacion_actual, String observaciones){
        Id = id;
        Codigo_correlativo = codigo_correlativo;
        Especie = especie;
        Cantidad = cantidad;
        Estado = estado;
        Precio_unitario = precio_unitario;
        Precio_total = precio_total;
        Fecha_recepcion = fecha_recepcion;
        Numero_factura = numero_factura;
        Rut_proveedor = rut_proveedor;
        Centro_de_costo = centro_de_costo;
        Ubicacion_actual = ubicacion_actual;
        Observaciones = observaciones;
    }
    public boolean isNull(){
        if(
            Id == 0 ||
            Codigo_correlativo == 0 ||
            Especie.equals("") ||
            Cantidad == 0 ||
            Precio_unitario == 0 ||
            Precio_total == 0 ||
            Fecha_recepcion.equals("") ||
            Numero_factura == 0 ||
            Rut_proveedor.equals("") ||
            Centro_de_costo.equals("") ||
            Ubicacion_actual.equals("") ||
            Observaciones.equals(""))
        {
            return false;
        }
        return true;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCodigo_correlativo() {
        return Codigo_correlativo;
    }

    public void setCodigo_correlativo(int codigo_correlativo) {
        Codigo_correlativo = codigo_correlativo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getPrecio_unitario() {
        return Precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        Precio_unitario = precio_unitario;
    }

    public int getPrecio_total() {
        return Precio_total;
    }

    public void setPrecio_total(int precio_total) {
        Precio_total = precio_total;
    }

    public int getNumero_factura() {
        return Numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        Numero_factura = numero_factura;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getRut_proveedor() {
        return Rut_proveedor;
    }

    public void setRut_proveedor(String rut_proveedor) {
        Rut_proveedor = rut_proveedor;
    }

    public String getCentro_de_costo() {
        return Centro_de_costo;
    }

    public void setCentro_de_costo(String centro_de_costo) {
        Centro_de_costo = centro_de_costo;
    }

    public String getUbicacion_actual() {
        return Ubicacion_actual;
    }

    public void setUbicacion_actual(String ubicacion_actual) {
        Ubicacion_actual = ubicacion_actual;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getFecha_recepcion() {
        return Fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        Fecha_recepcion = fecha_recepcion;
    }

}