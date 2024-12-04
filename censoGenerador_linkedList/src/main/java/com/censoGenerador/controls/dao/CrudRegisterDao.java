package com.censoGenerador.controls.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Scanner;

import com.censoGenerador.estructures.stack.Stack;
import com.censoGenerador.models.CrudRegister;
import com.google.gson.Gson;

public class CrudRegisterDao {
    private CrudRegister register;
    private Stack<CrudRegister> stackAll;
    private Gson gson;
    public static String URL = "media/";

    public CrudRegisterDao() {
        this.gson = new Gson();
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

    public Stack<CrudRegister> getStackAll() {
        if (stackAll == null) {
            this.stackAll = listAll();
        }
        return this.stackAll;
    }

    private Stack<CrudRegister> listAll() {
        Stack<CrudRegister> stack = new Stack<>(10000); 
        try {
            String data = readFile();
            CrudRegister[] matrix = gson.fromJson(data, CrudRegister[].class);
            for (int i = matrix.length - 1; i >= 0; i--) {
                stack.push(matrix[i]);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stack;
    }

    public Boolean save() throws Exception{
        Integer id = getStackAll().getSize() +1;
        try {
            register.setId(id);
            this.persist(this.register);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void persist(CrudRegister obj) throws Exception {
        Stack<CrudRegister> stack = listAll();
        stack.push(obj);
        String info = gson.toJson(stack.toArray());
        saveFile(info);
    }

    private String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL + CrudRegister.class.getSimpleName() + ".json"));
        StringBuilder sb = new StringBuilder();

        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String data) throws Exception {
        File directory = new File(URL);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter file = new FileWriter(URL + CrudRegister.class.getSimpleName() + ".json");
        file.write(data);
        file.flush();
        file.close();
    }
}

/*public class CrudRegisterDao extends AdapterDao<CrudRegister>{
    private CrudRegister register;
    private LinkedList listAll;

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
            register.setId(id);
            this.persist(this.register);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}*/