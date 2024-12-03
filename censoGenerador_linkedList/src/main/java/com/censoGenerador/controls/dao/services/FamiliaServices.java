package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.FamiliaDao;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Familia;
import com.censoGenerador.models.pruebas;

public class FamiliaServices {
    private FamiliaDao familia;

    public FamiliaServices() {
        this.familia = new FamiliaDao();
    }

    public Familia getFamilia() {
        return this.familia.getFamilia();
    }

    public void setFamilia(Familia familia) {
        this.familia.setFamilia(familia);
    }

    public LinkedList getListAll() {
        return this.familia.getListAll();
    }

    public Boolean save() throws Exception {
        return this.familia.save();
    }

    public Boolean update() throws Exception {
        return this.familia.update();
    }

    public Boolean delete() throws Exception {
        return this.familia.delete();
    }

    public Familia get(Integer id) throws Exception {
        return this.familia.get(id);
    }

    public LinkedList orderByShellSort(String attribute, Integer type) throws Exception {
        return this.familia.listAll().orderByShellSort(attribute, type);
    }

    public LinkedList orderByMergeSort(String attribute, Integer type) throws Exception {
        return this.familia.listAll().orderByMergeSort(attribute, type);
    }

    public LinkedList orderByQuickSort(String attribute, Integer type) throws Exception {
        return this.familia.listAll().orderByQuickSort(attribute, type);
    }
}
