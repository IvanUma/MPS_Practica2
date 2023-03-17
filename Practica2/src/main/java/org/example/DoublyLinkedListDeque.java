package org.example;

import java.util.Comparator;

public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        DequeNode<T> newNode = new DequeNode(value, null, null);
        if(size == 0){
            last = newNode;
        }else{
            newNode.setNext(first);
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        DequeNode<T> newNode = new DequeNode(value, null, null);
        if(size == 0){
            first = newNode;
        }else{
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() throws DoubleEndedQueueException{
        if(size == 0){
            throw new DoubleEndedQueueException("No se puede eliminar el primer elemento de una cola vacía");
        }else{
            DequeNode aux = first;
            first = aux.getNext();
            first.setPrevious(null);
            aux = null;
            size--;
        }
    }

    @Override
    public void deleteLast() throws DoubleEndedQueueException{
        if(size == 0){
            throw new DoubleEndedQueueException("No se puede eliminar el último elemento de una cola vacía");
        }else{
            DequeNode aux = last;
            last = aux.getPrevious();
            last.setNext(null);
            aux = null;
            size--;
        }
    }

    @Override
    public T first() throws DoubleEndedQueueException{
        if(size == 0){
            throw new DoubleEndedQueueException("No se puede acceder al primer elemento de una lista vacía");
        }
        return first.getItem();
    }

    @Override
    public T last() throws DoubleEndedQueueException{
        if(size == 0){
            throw new DoubleEndedQueueException("No se puede acceder al último elemento de una lista vacía");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("No es posible acceder al índice indicado");
        }
        DequeNode<T> aux;
        if(index + 1 > size / 2){
            aux = last;
            for(int cnt = size - 1 - index; cnt > 0; cnt--){
                aux = aux.getPrevious();
            }
        }else{
            aux = first;
            for(int cnt = 0; cnt < index; cnt++){
                aux = aux.getNext();
            }
        }
        return aux.getItem();
    }

    @Override
    public boolean contains(T value) {

        int cnt = size;
        boolean found = false;
        DequeNode<T> aux = first;
        while (!found && cnt > 0) {
            if(value.equals(aux.getItem())){
                found = true;
            }
            aux = aux.getNext();
            cnt--;
        }
        return found;
    }

    @Override
    public void remove(T value) {
        int cnt = size;
        boolean deleted = false;
        DequeNode<T> aux = first;
        while(!deleted && cnt > 0){
            if(value.equals(aux.getItem()) && cnt == size){
                deleteFirst();
                deleted = true;
            } else if(value.equals(aux.getItem()) && cnt == 1){
                deleteLast();
                deleted = true;
            }else if(value.equals(aux.getItem())){
                deleted = true;
                aux.getNext().setPrevious(aux.getPrevious());
                aux.getPrevious().setNext(aux.getNext());
                aux.setNext(null);
                aux.setPrevious(null);
                aux = null;
                size--;
            }
            if(aux != null){
                aux = aux.getNext();
            }
            cnt--;
        }

    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        DequeNode<T> current, previous, next;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                current = getNode(j);
                previous = current.getPrevious();
                next = current.getNext();
                if (comparator.compare(current.getItem(), next.getItem()) > 0) {
                    swap(current, previous, next);
                }
            }
        }
    }

    private DequeNode<T> getNode(int index){
        DequeNode<T> node = first;
        while(index > 0){
            node = node.getNext();
            index--;
        }
        return node;
    }

    private void swap(DequeNode<T> current, DequeNode<T> prev, DequeNode<T> next){
        current.setNext(next.getNext());
        current.setPrevious(next);
        if(prev != null){
            prev.setNext(next);
        }

        next.setNext(current);
        next.setPrevious(prev);

        if(current.isLastNode()){
            last = current;
        }
        if(next.isFirstNode()){
            first = next;
        }
    }

}
