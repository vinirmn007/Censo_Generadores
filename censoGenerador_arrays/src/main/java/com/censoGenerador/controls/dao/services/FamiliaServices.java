package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.FamiliaDao;
import com.censoGenerador.list.ListArray;
import com.censoGenerador.models.Familia;

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

    public ListArray getListAll() {
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
}
