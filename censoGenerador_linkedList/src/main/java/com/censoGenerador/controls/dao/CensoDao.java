package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.implement.AdapterDao;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.models.Censo;
import com.censoGenerador.models.Familia;
import com.google.gson.Gson;

public class CensoDao extends AdapterDao<Censo> {
    private Censo censo;
    private LinkedList<Familia> familias = new FamiliaServices().getListAll();
    private LinkedList listAll;

    public CensoDao() {
        super(Censo.class);
    }

    public Censo getCenso() {
        if (this.censo == null) {
            this.censo = new Censo();
        }
        return this.censo;
    }

    public void setCenso(Censo censo) {
        this.censo = censo;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    //OPERACIONES
    public void determinarFamiliasConGenerador() {
        try {
            this.getCenso().getFamiliasConGenerador().reset();
            if (!this.familias.isEmpty()) {
                Familia familiaArr[] = this.familias.toArray();
    
                for (Familia familia : familiaArr) {
                    if (familia.getGeneradorId() != 0) {
                        this.getCenso().getFamiliasConGenerador().add(familia);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();}
    }

    public Boolean saveFamiliasConGenerador() throws Exception {
        try {
            LinkedList<Familia> familiasConGenerador = this.getCenso().getFamiliasConGenerador();
            Gson gson = new Gson();
            String data = gson.toJson(familiasConGenerador.toArray());
            saveFile(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}