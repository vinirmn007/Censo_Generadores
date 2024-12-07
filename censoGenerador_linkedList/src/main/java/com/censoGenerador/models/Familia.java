package com.censoGenerador.models;

public class Familia {
    private Integer id;
    private String apellido;
    private Integer nroIntegrantes;
    private Integer generadorId;

    public Familia(String apellido) {
        this.id = 0;
        this.apellido = apellido;
        this.nroIntegrantes = 0;
        this.generadorId = 0;
    }

    //SETTERS Y GETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNroIntegrantes() {
        return this.nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }

    public Integer getGeneradorId() {
        return this.generadorId;
    }

    public void setGeneradorId(Integer generadorId) {
        this.generadorId = generadorId;
    }

    @Override
    public String toString() {
        return "Familia{" + "apellido='" + apellido + '\'' + ", id=" + id + '}';
    }
}
