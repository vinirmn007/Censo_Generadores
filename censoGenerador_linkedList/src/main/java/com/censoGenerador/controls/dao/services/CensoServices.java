package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.CensoDao;
import com.censoGenerador.estructures.list.LinkedList;

public class CensoServices {
    private CensoDao censo;

    public CensoServices() {
        this.censo = new CensoDao();
    }

    public void determinarFamiliasConGenerador() {
        this.censo.determinarFamiliasConGenerador();
    }

    public LinkedList getFamiliasConGenerador() {
        return this.censo.getFamiliasConGenerador();
    }
    
    public LinkedList orderByShellSort(String attribute, Integer type) throws Exception {
        return this.censo.getFamiliasConGenerador().orderByShellSort(attribute, type);
    }

    public LinkedList orderByMergeSort(String attribute, Integer type) throws Exception {
        return this.censo.getFamiliasConGenerador().orderByMergeSort(attribute, type);
    }

    public LinkedList orderByQuickSort(String attribute, Integer type) throws Exception {
        return this.censo.getFamiliasConGenerador().orderByQuickSort(attribute, type);
    }

    public Object linealSearch(String attribute, Object value, Integer type) throws Exception {
        return this.censo.getFamiliasConGenerador().linealSearch(attribute, value, type);
    }

    public Object binarySearch(String attribute, Object value, Integer type) throws Exception {
        return this.censo.getFamiliasConGenerador().binarySearch(attribute, value, type);
    }
}
