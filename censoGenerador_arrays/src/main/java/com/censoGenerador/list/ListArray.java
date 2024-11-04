package com.censoGenerador.list;

public class ListArray<E> {
    private E[] data;
    private int size;

    public ListArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public void add(E element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
        size++;
    }

    public E get(int index) throws IllegalArgumentException {
        if (index < 0 || index >= size) {
            throw IllegalArgumentException("El indice esta fuera de rango");
        }
        return data[index];
    }

    public void resize() {
        E[] help = (E[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            help[i] = data[i];
        }
        data = help;
    }

    public void remove(int index) throws IllegalArgumentException {
        if (index < 0 || index >= size) {
            throw IllegalArgumentException("El indice esta fuera de rango");
        }
        
        size--;
    }
}
