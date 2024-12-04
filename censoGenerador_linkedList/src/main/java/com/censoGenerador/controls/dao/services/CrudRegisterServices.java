package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.CrudRegisterDao;
import com.censoGenerador.estructures.list.LinkedList;
import com.censoGenerador.estructures.stack.Stack;
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

    public Stack getListAll() {
        return this.register.getStackAll();
    }

    public Boolean save() throws Exception {
        return this.register.save();
    }

}
