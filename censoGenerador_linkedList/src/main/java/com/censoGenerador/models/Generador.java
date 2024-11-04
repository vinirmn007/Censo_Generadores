package com.censoGenerador.models;

public class Generador {
    private Integer id;
    private String marca;
    private String modelo;
    private Float consumoPorHora;
    private Float energiaGenerada;
    private Float precio;
    private String uso;
    private Familia familia;

    public Generador() {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.consumoPorHora = 0.0F;
        this.energiaGenerada = 0.0F;
        this.precio = 0.0F;
        this.uso = "";
        this.familia = null;
    }

    //SETTERS Y GETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Float getConsumoPorHora() {
        return this.consumoPorHora;
    }

    public void setConsumoPorHora(Float consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }

    public Float getEnergiaGenerada() {
        return this.energiaGenerada;
    }

    public void setEnergiaGenerada(Float energiaGenerada) {
        this.energiaGenerada = energiaGenerada;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getUso() {
        return this.uso;
    }

    public void setUso(String uso) {
		this.uso = uso;
	}

    public Familia getFamilia() {
        return this.familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    @Override
    public String toString() {
        return "Generador{"+"id=" + id + '/' + "marca=" + marca + '/' + ", modelo=" + modelo + '}';
    }
}
