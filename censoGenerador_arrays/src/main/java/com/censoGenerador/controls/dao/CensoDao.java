package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.implement.AdapterDao;
import com.censoGenerador.list.ListArray;
import com.censoGenerador.models.Censo;
import com.censoGenerador.models.Familia;
import com.google.gson.Gson;

public class CensoDao extends AdapterDao<Censo> {
    private Censo censo;
    private ListArray listAll;

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

    public ListArray getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    //OPERACIONES
    public Boolean saveFamiliasConGenerador() throws Exception {
        if (this.censo == null) {
            this.censo = new Censo();
        }
        try {
            ListArray<Familia> familiasConGenerador = this.censo.getFamiliasConGenerador();
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