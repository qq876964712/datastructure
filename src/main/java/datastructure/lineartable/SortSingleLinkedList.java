package datastructure.lineartable;



import java.util.LinkedList;

public class SortSingleLinkedList<T extends Comparable<T>> extends SingleLinkedList<T> {

    @Override
    public void insert(int index, T element) {
        super.insert(index, element);
    }
    @Override
    public void append(T element) {
        super.append(element);
    }

    @Override
    public T set(int index, T element) {


  LinkedList list =null;

  list.poll();
        return super.set(index, element);

    }

}

