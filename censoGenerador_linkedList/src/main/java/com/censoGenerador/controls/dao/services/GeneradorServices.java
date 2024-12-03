package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.GeneradorDao;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Familia;
import com.censoGenerador.models.Generador;

public class GeneradorServices {
    private GeneradorDao generador;

    public GeneradorServices() {
        this.generador = new GeneradorDao();
    }

    public Generador getGenerador() {
        return this.generador.getGenerador();
    }

    public void setGenerador(Generador generador) {
        this.generador.setGenerador(generador);
    }

    public LinkedList getListAll() {
        return this.generador.getListAll();
    }

    public Boolean save() throws Exception {
        return this.generador.save();
    }

    public Boolean update() throws Exception {
        return this.generador.update();
    }

    public Boolean delete() throws Exception {
        return this.generador.delete();
    }

    public Generador get(Integer id) throws Exception {
        return this.generador.get(id);
    }

    public LinkedList orderByShellSort(String attribute, Integer type) throws Exception {
        return this.generador.listAll().orderByShellSort(attribute, type);
    }

    public LinkedList orderByMergeSort(String attribute, Integer type) throws Exception {
        return this.generador.listAll().orderByMergeSort(attribute, type);
    }

    public LinkedList orderByQuickSort(String attribute, Integer type) throws Exception {
        return this.generador.listAll().orderByQuickSort(attribute, type);
    }

    public Object linealSearch(String attribute, Object value, Integer type) throws Exception {
        return this.generador.listAll().linealSearch(attribute, value, type);
    }

    public Object binarySearch(String attribute, Object value, Integer type) throws Exception {
        return this.generador.listAll().binarySearch(attribute, value, type);
    }
}
