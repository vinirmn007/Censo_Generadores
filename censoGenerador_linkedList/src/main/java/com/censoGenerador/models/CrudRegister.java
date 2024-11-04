package com.censoGenerador.models;

public class CrudRegister {
    private Integer id;
    private String operacion;
    private String detalle;
    private String hora;

    public CrudRegister() {
        this.id = 0;
        this.operacion = "NINGUNA";
        this.detalle = "";
        this.hora = getHora();
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

    public String getHora() {
        return this.hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
