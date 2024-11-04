package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.implement.AdapterDao;
import com.censoGenerador.list.ListArray;
import com.censoGenerador.models.Familia;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private ListArray listAll;

    public FamiliaDao() {
        super(Familia.class);
    }

    //GETTERS Y SETTERS
    public Familia getFamilia() {
        if(this.familia == null) {
            this.familia = new Familia("Sin apellido");
        }
        return this.familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public ListArray getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    //OPERACIONES
    public Boolean save() throws Exception{
        Integer id = getListAll().getSize() +1;
        try {
            familia.setId(id);
            this.persist(this.familia);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.familia, this.familia.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.familia.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
