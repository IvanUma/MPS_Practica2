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
}
