package com.censoGenerador.list;

public class ListArray<E> {
    private E[] data;
    private Integer size;
    private Integer capacity;

    public ListArray() {
        this.capacity = 0;
        this.data = (E[]) new Object[this.capacity];
        this.size = 0;
    }
    public ListArray(Integer capacity) {
        this.capacity = capacity;
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public Boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public void reset() {
        this.data = (E[]) new Object[this.capacity];
        this.size = 0;
    }

    public void add(E element) {
        if (this.size == this.capacity) {
            resize();
        }
        this.data[this.size++] = element;
    }

    public void add(E element, Integer index) throws ListArrayException {
        if (index >= 0 && index <= this.size) {
            if (this.size == this.capacity) {
                resize();
            }
            if (index != this.size) {
                System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
            }
            this.data[index] = element;
            this.size++;
        } else {
            throw new ListArrayException("El indice esta fuera de rango");
        }
    }

    public E get(Integer index) throws ListArrayException {
        if (index >= 0 && index < this.size) {
            return this.data[index];
        } else {
            throw new ListArrayException("El indice esta fuera de rango");
        }
    }

    public void update(E element, Integer index) throws ListArrayException {
        if (index >= 0 && index < this.size) {
            this.data[index] = element;
        } else {
            throw new ListArrayException("El indice esta fuera de rango");
        }
    }

    public void delete(Integer index) throws ListArrayException {
        if (index >= 0 && index < this.size) {
            System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
            this.data[--size] = null;
            this.size--;
        } else {
            throw new ListArrayException("Índice fuera de los límites");
        }
    }

    private void resize() {
        capacity += 1;
        E[] help = (E[]) new Object[capacity];
        System.arraycopy(this.data, 0, help, 0, this.size);
        data = help;
    }

    public  E[] toArray(){
        E[] array = (E[]) new Object[this.size];
        System.arraycopy(this.data, 0, array, 0, this.size);
        return array;
    }   

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.data[i]);
            if (i < this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
