package com.censoGenerador.controls.dao.implement;

import com.censoGenerador.list.ListArray;

public interface InterfazDao<T> {
    public void persist(T obj) throws Exception;
    public void merge(T obj, Integer index) throws Exception;
    public ListArray listAll();
    public T get(Integer id) throws Exception;
    public void delete(Integer id) throws Exception;
}
