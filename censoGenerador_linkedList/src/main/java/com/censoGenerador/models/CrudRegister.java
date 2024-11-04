package com.censoGenerador.models;

import java.time.LocalDateTime;

public class CrudRegister {
    private Integer id;
    private String operacion;
    private String detalle;
    //private LocalDateTime hora;

    public CrudRegister() {
        this.id = 0;
        this.operacion = "NINGUNA";
        this.detalle = "";
        //this.hora = LocalDateTime.now();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperacion() {
        return this.operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /*public LocalDateTime getHora() {
        return this.hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }*/
}
