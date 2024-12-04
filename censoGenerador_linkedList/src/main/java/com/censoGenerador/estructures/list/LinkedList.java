package com.censoGenerador.estructures.list;

import java.lang.reflect.Method;

import javax.ws.rs.core.Link;

import com.censoGenerador.estructures.exception.ListEmptyException;
import com.censoGenerador.models.Familia;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;
    private Boolean isOrder;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
        this.isOrder = false;
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    public Integer getSize() {
        return this.size;
    }

    public Boolean isOrder() {
        return this.isOrder;
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
        this.isOrder = false;
    }

    //METODOS PARA AGREGAR ELEMENTOS
    private void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            last = help;
        } else {
            Node<E> healpHeader = this.header;
            help = new Node<>(dato, healpHeader);
            this.header = help;
        }
        this.size++;
    }

    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            addHeader(info);
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
            this.size++;
        }
    }

    public void add(E info) {
        addLast(info);
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    //OBTENER NODO
    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header;
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    //OBTENER ELEMENTOS
    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }    

    //ELIMINAR ELEMENTOS
    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else {
            E element = header.getInfo();
            Node<E> help = header.getNext();
            header = help;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> help = getNode(size - 2);
            if (help == null) {
                last = null;
                if (size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = help;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E delete(Integer index) throws ListEmptyException{
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");   
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index == 0) {
            return deleteFirst();
        } else if (index == this.size-1){
            return deleteLast();
        } else {
            Node<E> before = getNode(index-1);
            Node<E> actual = getNode(index);

            E element = before.getInfo();
            Node<E> next = actual.getNext();

            before.setNext(actual.getNext());
            actual = null;
            before.setNext(next);
            size--;
            return element;
        }
    }

    //MODIFICAR ELEMENTOS
    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            header.setInfo(info);
        } else if (index.intValue() == (this.size - 1)) {
            last.setInfo(info);
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(info);
        }
    }

    //ORDENAMIENTO POR METODO DE INSERCION
    public LinkedList<E> orderByInsertion(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                for (int i = 1; i < lista.length; i++) {
                    E aux = lista[i]; // valor a ordenar
                    int j = i - 1; // índice anterior
                    while (j >= 0 && atrribute_compare(attribute, lista[j], aux, type)) {
                        lista[j + 1] = lista[j--]; // desplaza elementos hacia la derecha
                    }
                    lista[j + 1] = aux; // inserta el valor en su posición correcta
                }
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    public LinkedList<E> orderByInsertion(Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Number || data instanceof String) {
                E[] lista = this.toArray();
                reset();
                for (int i = 1; i < lista.length; i++) {
                    E aux = lista[i]; // valor a ordenar
                    int j = i - 1; // índice anterior
                    while (j >= 0 && compare(lista[j], aux, type)) {
                        lista[j + 1] = lista[j--]; // desplaza elementos hacia la derecha
                    }
                    lista[j + 1] = aux; // inserta el valor en su posición correcta
                }
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    //ORDENAMIENTO POR SHELLSORT
    public LinkedList<E> orderByShellSort(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                Integer intervalo = lista.length / 2;
                while (intervalo >= 1) {
                    for (int i = 0; i+intervalo < lista.length; i++) {
                        int j = i;
                        while (j >= 0) {
                            int k = j + intervalo;
                            if (atrribute_compare(attribute, lista[j], lista[k], type)) {
                                E aux = lista[j];
                                lista[j] = lista[k];
                                lista[k] = aux;
                                j -= intervalo;
                            } else {
                                j = -1;
                            }
                        }
                    }
                    intervalo = intervalo / 2;  
                }
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    public LinkedList<E> orderByShellSort(Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                Integer intervalo = lista.length / 2;
                while (intervalo >= 1) {
                    for (int i = 0; i+intervalo < lista.length; i++) {
                        int j = i;
                        while (j >= 0) {
                            int k = j + intervalo;
                            if (compare(lista[j], lista[k], type)) {
                                E aux = lista[j];
                                lista[j] = lista[k];
                                lista[k] = aux;
                                j -= intervalo;
                            } else {
                                j = -1;
                            }
                        }
                    }
                    intervalo = intervalo / 2;  
                }
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    //ORDENAMIENTO POR MERGESORT
    public LinkedList<E> orderByMergeSort(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                this.toList(mergeSort(lista, attribute, type));
            }
        }
        this.isOrder = true;
        return this;
    }

    public LinkedList<E> orderByMergeSort(Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Number || data instanceof String) {
                E[] lista = this.toArray();
                reset();
                this.toList(mergeSort(lista, type));
            }
        }
        this.isOrder = true;
        return this;
    }

    //METODOS AUXILIARES PARA MERGE SORT
    private E[] mergeSort(E[] lista, String attribute, Integer type) throws Exception {
        if (lista.length <= 1) {
            return lista;
        } else {
            int middle = lista.length / 2;

            E[] left = (E[])new Object[middle];
            E[] right = (E[])new Object[lista.length - middle];

            for (int i = 0; i < middle; i++) {
                left[i] = lista[i];
            }

            int j = 0;
            for (int k = middle; k < lista.length; k++) {
                right[j] = lista[k];
                j++;
            }

            left = mergeSort(left, attribute, type);
            right = mergeSort(right, attribute, type);
            
            if (left.length >= 1 && right.length >= 1) {
                merge(lista, left, right, attribute, type);
            }
        }
        return lista;
    }

    private void merge(E[] lista, E[] left, E[] right, String attribute, Integer type) throws Exception {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (atrribute_compare(attribute, left[i], right[j], type)) {
                lista[k] = right[j];
                j++;
            } else {
                lista[k] = left[i];
                i++;
            }
            k++;
        }

        while (i < left.length) {
            lista[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            lista[k] = right[j];
            j++;
            k++;
        }
    }

    //PARA NUMEROS
    private E[] mergeSort(E[] lista, Integer type) throws Exception {
        if (lista.length <= 1) {
            return lista;
        } else {
            int middle = lista.length / 2;

            E[] left = (E[])new Object[middle];
            E[] right = (E[])new Object[lista.length - middle];

            for (int i = 0; i < middle; i++) {
                left[i] = lista[i];
            }

            int j = 0;
            for (int k = middle; k < lista.length; k++) {
                right[j] = lista[k];
                j++;
            }

            left = mergeSort(left, type);
            right = mergeSort(right, type);
            
            if (left.length >= 1 && right.length >= 1) {
                merge(lista, left, right, type);
            }
        }
        return lista;
    }

    private void merge(E[] lista, E[] left, E[] right, Integer type) throws Exception {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (compare(left[i], right[j], type)) {
                lista[k] = right[j];
                j++;
            } else {
                lista[k] = left[i];
                i++;
            }
            k++;
        }

        while (i < left.length) {
            lista[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            lista[k] = right[j];
            j++;
            k++;
        }
    }

    //ORDENAMIENTO POR QUICKSORT
    public LinkedList<E> orderByQuickSort(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                quickSort(lista, 0, lista.length-1, attribute, type);
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    public LinkedList<E> orderByQuickSort(Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Number || data instanceof String) {
                E[] lista = this.toArray();
                reset();
                quickSort(lista, 0, lista.length-1, type);
                this.toList(lista);
            }
        }
        this.isOrder = true;
        return this;
    }

    //METODOS AUXILIARES PARA QUICK SORT
    private void quickSort(E[] lista, int low, int high, String attribute, Integer type) throws Exception {
        if (low < high) {
            int pivote = partition(lista, low, high, attribute, type);

            quickSort(lista, low, pivote - 1, attribute, type);
            quickSort(lista, pivote + 1, high, attribute, type);
            
        } 
    }

    private int partition(E[] lista, int low, int high, String attribute, Integer type) throws Exception {
        E pivote = lista[low + (high - low) / 2];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (atrribute_compare(attribute, pivote, lista[j], type)) {
                i++;
                E aux = lista[i];
                lista[i] = lista[j];
                lista[j] = aux;
            }
        }

        E aux = lista[i + 1];
        lista[i + 1] = lista[high];
        lista[high] = aux;

        return i + 1;
    }

    //PARA NUMEROS
    private void quickSort(E[] lista, int low, int high, Integer type) throws Exception {
        if (low < high) {
            int pivote = partition(lista, low, high, type);

            quickSort(lista, low, pivote - 1, type);
            quickSort(lista, pivote + 1, high, type);
            
        } 
    }

    private int partition(E[] lista, int low, int high, Integer type) throws Exception {
        E pivote = lista[low + (high - low) / 2];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(pivote, lista[j], type)) {
                i++;
                E aux = lista[i];
                lista[i] = lista[j];
                lista[j] = aux;
            }
        }

        E aux = lista[i + 1];
        lista[i + 1] = lista[high];
        lista[high] = aux;

        return i + 1;
    }

    //BUSQUEDA LINEAL
    public LinkedList<E> multipleLinealSearch(String attribute, Object value) throws Exception {
        LinkedList<E> lista = new LinkedList<>();
        if (!this.isEmpty()) {
            E[] aux = this.toArray();
            for (int i = 0; i < aux.length; i++) {
                Object attrValue = exist_attribute(aux[i], attribute);
                if (attrValue != null) {
                    String attrValueStr = attrValue.toString();
                    String valueStr = value.toString();
                    if (attrValueStr.equals(valueStr) || attrValueStr.toLowerCase().startsWith(valueStr.toLowerCase())) {
                        lista.add(aux[i]);
                    }
                }
            }
        }
        return lista;
    }

    //PARA NUMEROS
    public LinkedList<E> multipleLinealSearch(Object value) throws Exception {
        LinkedList<E> list = new LinkedList<>();
        if (!this.isEmpty()) {
            E[] aux = this.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].equals(value)) {
                    list.add(aux[i]);
                    break;
                }
            }
        }
        return list;
    }

    public E atomicLinealSearch(String attribute, Object value) throws Exception {
        E obj = null;
        if (!this.isEmpty()) {
            E[] aux = this.toArray();
            for (int i = 0; i < aux.length; i++) {
                Object attrValue = exist_attribute(aux[i], attribute);
                if (attrValue != null) {
                    String attrValueStr = attrValue.toString();
                    String valueStr = value.toString();
                    if (attrValueStr.equals(valueStr) || attrValueStr.toLowerCase().startsWith(valueStr.toLowerCase())) {
                        obj = aux[i];
                    }
                }
            }
        }
        return obj;
    }

    //PARA NUMEROS
    public E atomicLinealSearch(Object value) throws Exception {
        E obj = null;
        if (!this.isEmpty()) {
            E[] aux = this.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].equals(value)) {
                    obj = aux[i];
                    break;
                }
            }
        }
        return obj;
    }

    //BUSQUEDA LINEAR BINARIA
    public LinkedList<E> multipleBinarySearch(String attribute, Object value) throws Exception {
        LinkedList<E> lista = new LinkedList<>();
        if (!this.isEmpty()) {
            this.orderByQuickSort(attribute, 0);
            E[] aux = this.toArray();
            int first = 0;
            int last = aux.length - 1;
            
            while (first <= last) {
                int mid = (first + last) / 2;
                Object attrValue = exist_attribute(aux[mid], attribute);

                if (attrValue != null) {
                    String attrValueStr = attrValue.toString();
                    String valueStr = value.toString();
                    if (attrValueStr.equals(valueStr) || attrValueStr.toLowerCase().startsWith(valueStr.toLowerCase())) {
                        lista.add(aux[mid]);

                        int left = mid - 1;
                        while (left >= 0) {
                            Object leftValue = exist_attribute(aux[left], attribute);
                            if (leftValue != null && leftValue.equals(value)) {
                                lista.addHeader(aux[left]);
                                left--;
                            } else {
                                break;
                            }
                        }

                        int right = mid + 1;
                        while (right < aux.length) {
                            Object rightValue = exist_attribute(aux[right], attribute);
                            if (rightValue != null && rightValue.equals(value)) {
                                lista.add(aux[right]);
                                right++;
                            } else {
                                break;
                            }
                        }

                        break;
                        
                    }

                    if (compare(attrValueStr, valueStr, 0)) {
                        last = mid - 1;
                    } else {
                        first = mid + 1;
                    }
                }
            }
        }
        return lista;
    }

    //PARA NUMEROS
    public LinkedList<E> multipleBinarySearch(Object value) throws Exception {
        LinkedList<E> list = new LinkedList<>();
        if (!this.isEmpty()) {
            this.orderByQuickSort(0);
            E[] aux = this.toArray();
            int first = 0;
            int last = aux.length - 1;
            Boolean find = false;
            while (first <= last && find == false) {
                int mid = (first + last) / 2;
                if (aux[mid].equals(value)) {
                    list.add(aux[mid]);
                    int left = mid - 1;
                    while (left >= 0) {
                        if (aux[left].equals(value)) {
                            list.addHeader(aux[left]);
                            left--;
                        } else {
                            break;
                        }
                    }

                    int right = mid + 1;
                    while (right < aux.length) {
                        if (aux[right].equals(value)) {
                            list.add(aux[right]);
                            right++;
                        } else {
                            break;
                        }
                    }

                    find = true;
                } else {
                    if (compare(aux[mid], value, 0)) {
                        last = mid - 1;
                    } else {
                        first = mid + 1;
                    }
                }
            }
        }
        return list;
    }

    public E atomicBinarySearch(String attribute, Object value) throws Exception {
        E obj = null;
        if (!this.isEmpty()) {
            this.orderByQuickSort(attribute, 0);
            E[] aux = this.toArray();
            int first = 0;
            int last = aux.length - 1;
            Boolean find = false;
            while (first <= last && find == false) {
                int mid = (first + last) / 2;
                Object attrValue = exist_attribute(aux[mid], attribute);
            
                if (attrValue != null) {
                    String attrValueStr = attrValue.toString();
                    String valueStr = value.toString();
                    if (attrValueStr.equals(valueStr) || attrValueStr.toLowerCase().startsWith(valueStr.toLowerCase())) {
                        obj = aux[mid];
                        find = true;
                    } else {
                        if (compare(attrValueStr, valueStr, 0)) {
                            last = mid - 1;
                        } else {
                            first = mid + 1;
                        }
                    }
                    
                }
            }
        }
        return obj;
    }

    //PARA NUMEROS
    public E atomicBinarySearch(Object value) throws Exception {
        E obj = null;
        if (!this.isEmpty()) {
            this.orderByQuickSort(0);
            E[] aux = this.toArray();
            int first = 0;
            int last = aux.length - 1;
            Boolean find = false;
            while (first <= last && find == false) {
                int mid = (first + last) / 2;
                if (aux[mid].equals(value)) {
                    obj = aux[mid];
                    find = true;
                } else {
                    if (compare(aux[mid], value, 0)) {
                        last = mid - 1;
                    } else {
                        first = mid + 1;
                    }
                }
            }
        }
        return obj;
    }

    //OTROS METODOS
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List Data \n");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" -> ");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    public E[] toArray(){
        E[] matrix = null;
        if (!isEmpty()) {
            Class clazz = header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for(int i=0; i<size && aux != null; i++){
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matrix;
    }

    public LinkedList<E> toList(E[] matrix){
        reset();
        for(int i = 0; i < matrix.length; i++){
            this.add(matrix[i]);
        }
        return this;
    }

    //METODOS AUXILIARES PARA ORDENAMIENTO
    private Boolean compare(Object a, Object b, Integer type) {
        switch (type) {
            case 0:
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() > b1.doubleValue();
                } else {
                    // "casa" > "pedro"
                    return (a.toString()).compareTo(b.toString()) > 0;
                }
                // break;

            default:
                // mayor a menor
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() < b1.doubleValue();
                } else {
                    // "casa" > "pedro"
                    return (a.toString()).compareTo(b.toString()) < 0;
                }
                // break;
        }

    }

    // compare class
    private Boolean atrribute_compare(String attribute, E a, E b, Integer type) throws Exception {
        return compare(exist_attribute(a, attribute), exist_attribute(b, attribute), type);
    }

    private Object exist_attribute(E a, String attribute) throws Exception {
        Method method = null;
        attribute = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        attribute = "get" + attribute;
        for (Method aux : a.getClass().getMethods()) {           
            if (aux.getName().contains(attribute)) {
                method = aux;
                break;
            }
        }
        if (method == null) {
            for (Method aux : a.getClass().getSuperclass().getMethods()) {              
                if (aux.getName().contains(attribute)) {
                    method = aux;
                    break;
                }
            }
        }
        if (method != null) {            
            return method.invoke(a);
        }
        
        return null;
    }

    //TODO: FUNCAAAAAAAA
    /*private void merge(E[] list, int left, int mid, int right, String attribute, Integer type) throws Exception {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        E[] leftList = (E[]) new Object[n1];
        E[] rightList = (E[]) new Object[n2];

        for (int i = 0; i < n1; ++i)
            leftList[i] = list[left + i];
        for (int j = 0; j < n2; ++j)
            rightList[j] = list[mid + 1 + j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (atrribute_compare(attribute, leftList[i], rightList[j], type)) {
                list[k] = leftList[i];
                i++;
            } else {
                list[k] = rightList[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            list[k] = leftList[i];
            i++;
            k++;
        }

        while (j < n2) {
            list[k] = rightList[j];
            j++;
            k++;
        }
    }

    private void sort(E[] list, int left, int right, String attribute, Integer type) throws Exception {
        if (left < right) {
            int mid = left + (right - left) / 2;

            sort(list, left, mid, attribute, type);
            sort(list, mid + 1, right, attribute, type);

            merge(list, left, mid, right, attribute, type);
        }
    }*/
}
