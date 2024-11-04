package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.implement.AdapterDao;
import com.censoGenerador.list.LinkedList;
import com.censoGenerador.models.Generador;

public class GeneradorDao extends AdapterDao<Generador> {
    private Generador generador;
    private LinkedList listAll;

    public GeneradorDao() {
        super(Generador.class);
    }

    //GETTERS Y SETTERS
    public Generador getGenerador() {
        if(this.generador == null) {
            this.generador = new Generador();
        }
        return this.generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    //OPERACIONES
    public Boolean save() throws Exception{
        Integer id = getListAll().getSize() +1;
        try {
            generador.setId(id);
            this.persist(this.generador);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.generador, this.generador.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.generador.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
