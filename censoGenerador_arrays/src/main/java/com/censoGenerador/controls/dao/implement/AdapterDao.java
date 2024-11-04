package com.censoGenerador.controls.dao.implement;

import com.censoGenerador.list.ListArray;
import com.google.gson.Gson;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class clazz;
    private Gson gson;
    public static String URL = "media/";

    public AdapterDao(Class clazz) {
        this.clazz = clazz;
        gson = new Gson();
    }

    @Override
    public ListArray listAll() {
        ListArray<T> list = new ListArray<>();
        try {
            String data = readFile();
            T[] matrix = (T[]) gson.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass());
            for (T item : matrix) {
                list.add(item); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void persist(T obj) throws Exception {
        ListArray<T> list = listAll();
        list.add(obj);
        String info = gson.toJson(list.toArray());
        saveFile(info);
    }

    @Override
    public void merge(T obj, Integer id) throws Exception {
        ListArray<T> list = listAll();

        for (int i = 0; i < list.getSize(); i++) {
            T objActual = list.get(i);
            Integer objId = (Integer) objActual.getClass().getMethod("getId").invoke(objActual);
            if (objId.equals(id)) {
                list.update(obj, i);
                break;
            }
        }

        String info = gson.toJson(list.toArray());
        saveFile(info);
    }

    @Override
    public T get(Integer id) throws Exception {
        ListArray<T> list = listAll();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.getSize(); i++) {
                T obj = list.get(i);
                Integer objId = (Integer) obj.getClass().getMethod("getId").invoke(obj);
                if (objId == id) {
                    return obj;
                }
            }
        } 
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {
        ListArray<T> list = listAll();
        
        for (int i = 0; i < list.getSize(); i++) {
            T obj = list.get(i);
            Integer objId = (Integer) obj.getClass().getMethod("getId").invoke(obj);
            if (objId == id) {
                list.delete(i);
                break;
            }
        }

        String info = gson.toJson(list.toArray());
        saveFile(info);
    }

    protected String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL + clazz.getSimpleName() + ".json"));
        StringBuilder sb = new StringBuilder();

        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    protected void saveFile(String data) throws Exception {
        File directory = new File(URL);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter file = new FileWriter(URL + clazz.getSimpleName() + ".json");
        file.write(data);
        file.flush();
        file.close();
    }
}
