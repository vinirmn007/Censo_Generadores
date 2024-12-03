package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Familia;

public class CensoDao {
    private LinkedList<Familia> familias = new FamiliaServices().getListAll();
    private LinkedList<Familia> familiasConGenerador;

    public CensoDao() {
        this.familiasConGenerador = new LinkedList<>();
    }

    public LinkedList<Familia> getFamiliasConGenerador() {
        return this.familiasConGenerador;
    }

    //OPERACIONES
    public void determinarFamiliasConGenerador() {
        try {
            if (!this.familias.isEmpty()) {
                Familia familiaArr[] = this.familias.toArray();
    
                for (Familia familia : familiaArr) {
                    if (familia.getGeneradorId() != 0) {
                        this.familiasConGenerador.add(familia);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();}
    }
}