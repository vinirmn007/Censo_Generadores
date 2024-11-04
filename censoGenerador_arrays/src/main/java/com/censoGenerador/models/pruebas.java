package com.censoGenerador.models;

import com.censoGenerador.controls.dao.services.CrudRegisterServices;
import com.google.gson.Gson;

public class pruebas {
    public static void main(String[] args) throws Exception {
        CrudRegisterServices crs = new CrudRegisterServices();
        crs.getRegister().setDetalle("faefaee");
        crs.getRegister().setOperacion("qteqteqtqe");
        crs.save();
        
        System.out.println(crs.getRegister().toString());
    }
}
