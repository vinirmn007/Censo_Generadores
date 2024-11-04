package com.censoGenerador.controls.dao;

import com.censoGenerador.controls.dao.implement.AdapterDao;
import com.censoGenerador.list.ListArray;
import com.censoGenerador.models.CrudRegister;

public class CrudRegisterDao extends AdapterDao<CrudRegister>{
    private CrudRegister register;
    private ListArray listAll;

    public CrudRegisterDao() {
        super(CrudRegister.class);
    }

    //GETTERS Y SETTERS
    public CrudRegister getRegister() {
        if (this.register == null) {
            this.register = new CrudRegister();
        }
        return this.register;
    }

    public void setRegister(CrudRegister register) {
        this.register = register;
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
            register.setId(id);
            this.persist(this.register);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
