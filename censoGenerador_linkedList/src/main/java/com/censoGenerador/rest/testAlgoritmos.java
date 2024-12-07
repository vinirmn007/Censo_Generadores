package com.censoGenerador.rest;

import java.util.Random;

import com.censoGenerador.estructures.list.LinkedList;

public class testAlgoritmos {
    static LinkedList<Integer> crearLista(Integer size) {
        Integer[] arr = new Integer[size];
        LinkedList<Integer> lista = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        lista.toList(arr);
        return lista;
    }
    
    static Double testOrder(LinkedList<Integer> lista, String metodo, Integer tipo) throws Exception {
        Long start = System.nanoTime();
        switch (metodo) {
            case "shell":
                lista.orderByShellSort(tipo);
                break;
            case "merge":
                lista.orderByMergeSort(tipo);
                break;
            case "quick":
                lista.orderByQuickSort(tipo);
                break;
            default:
                break;
        }
        Long end = System.nanoTime();
        return (end - start) / 1000000.0;
    }

    static Double testSearch(LinkedList<Integer> lista, String metodo, Integer valor) throws Exception {
        Long start = System.nanoTime();
        switch (metodo) {
            case "m_lineal":
                lista.multipleLinealSearch(valor);
                break;
            case "a_lineal":
                lista.atomicLinealSearch(valor);
                break;
            case "binary":
                lista.binarySearch(valor);
                break;
            default:
                break;
        }
        Long end = System.nanoTime();
        return (end - start)/1000000.0;
    }
     public static void main(String[] args) {
        LinkedList<Integer> lista_orden = crearLista(20000);
        LinkedList<Integer> lista_busqueda = crearLista(20000);
        //System.out.println(lista_busqueda.toString());
        try {
            LinkedList<Integer> sh = lista_orden.clone();
            System.out.println("Shell: " + testOrder(sh, "shell", 0) + " ms");

            LinkedList<Integer> mg = lista_orden.clone();
            System.out.println("Merge: " + testOrder(mg, "merge", 0) + " ms");

            LinkedList<Integer> qs = lista_orden.clone();
            System.out.println("Quick: " + testOrder(qs, "quick", 0) + " ms");

            System.out.println("Multiple Lineal: " + testSearch(lista_busqueda, "m_lineal", 6000) + " ms");
            System.out.println("Atomic Lineal: " + testSearch(lista_busqueda, "a_lineal", 6000) + " ms");
            System.out.println("Binary: " + testSearch(lista_busqueda.orderByQuickSort(0), "binary", 6000) + " ms");
            //System.out.println(lista.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
