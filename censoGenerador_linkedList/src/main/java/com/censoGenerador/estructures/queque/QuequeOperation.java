package com.censoGenerador.estructures.queque;

import com.censoGenerador.estructures.exception.ListEmptyException;
import com.censoGenerador.estructures.exception.OverFlowException;
import com.censoGenerador.estructures.list.LinkedList;

public class QuequeOperation<E> extends LinkedList<E> {
    private Integer top;

    public QuequeOperation(Integer _top) {
        this.top = _top;
    }

    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }

    public void queque(E info) throws Exception {
        if (verify()) {
            add(info, getSize());
        } else {
            throw new OverFlowException("Cola llena");
        }
    }

    public E dequeque() throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Cola vacía en dequeque");
        } else {
            return (E) deleteFirst();
        }
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
