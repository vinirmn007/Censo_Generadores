package com.censoGenerador.models;

import com.censoGenerador.list.ListArray;

public class Censo {
    private ListArray<Familia> familias;
    private ListArray<Familia> familiasConGenerador;

    public Censo() {
        this.familias = new ListArray<>();
        this.familiasConGenerador = new ListArray<>();
    }

    //GETTERS Y SETTERS

    public ListArray<Familia> getFamilias() {
        return this.familias;
    }

    public void setFamilias(ListArray<Familia> familias) {
        this.familias = familias;
    }

    public ListArray<Familia> getFamiliasConGenerador() {
        return this.familiasConGenerador;
    }

    public void determinarFamiliasConGenerador() {
        this.familiasConGenerador.reset();

        if (this.familias.isEmpty()) {
            return; 
        } else {
            Familia familiaArr[] = this.familias.toArray();

            for (Familia familia : familiaArr) {
                if (familia.getGeneradorId() != 0) {
                    this.familiasConGenerador.add(familia);
                }
            }
        }
    }
}
