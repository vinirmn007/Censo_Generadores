package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.CrudRegisterDao;
import com.censoGenerador.list.LinkedList;
import com.censoGenerador.models.CrudRegister;

public class CrudRegisterServices {
    private CrudRegisterDao register;

    public CrudRegisterServices() {
        this.register = new CrudRegisterDao();
    }

    public CrudRegister getRegister() {
        return this.register.getRegister();
    }

    public void setRegister(CrudRegister register) {
        this.register.setRegister(register);
    }

    public LinkedList getListAll() {
        return this.register.getListAll();
    }

    public Boolean save() throws Exception {
        return this.register.save();
    }

    public CrudRegister get(Integer id) throws Exception {
        return this.register.get(id);
    }
}
