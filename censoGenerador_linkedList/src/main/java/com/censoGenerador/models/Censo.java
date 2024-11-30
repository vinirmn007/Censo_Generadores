package com.censoGenerador.models;

import com.censoGenerador.estructures.list.LinkedList;

public class Censo {
    private LinkedList<Familia> familiasConGenerador;

    public Censo() {
        this.familiasConGenerador = new LinkedList<>();
    }

    //GETTERS Y SETTERS
    public LinkedList<Familia> getFamiliasConGenerador() {
        return this.familiasConGenerador;
    }
}
