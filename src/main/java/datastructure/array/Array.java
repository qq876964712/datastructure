package datastructure.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class Array<E> {
	
	private Object[] elementData;
	private int size;
	private static final Object[] emptyArr=new Object[] {};
	private static final int DEFAULT_CAPACITY =10;
	public Array() {
		elementData = emptyArr;
	}
	public Array(int initialCapacity) {
		if(initialCapacity>0) {
			elementData = new Object[initialCapacity];
		}else if(initialCapacity == 0) {
			elementData = emptyArr;
		}else {
			throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
		}
	}
	public int length() {
		return this.size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	
	private int calculateCapacity(Object[]elementData,int minCapacity) {
		if(elementData== emptyArr) {
			minCapacity =Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		return minCapacity;
	}
	
	private void growCapacity(int minCapacity) {
		if(minCapacity -  elementData.length > 0) {
			int oldCapacity =  elementData.length;
			int newCapacity = oldCapacity + (oldCapacity>>1);
			if(newCapacity - minCapacity <0) newCapacity  = minCapacity;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	
	private void ensureEnoughCapacity(int minCapacity) {
		growCapacity(calculateCapacity(elementData,minCapacity));
	}

	public void add(E e) {
		ensureEnoughCapacity(size+1);
		elementData[size++] = e;
	}
	public E set(int index,E e) {
		rangeCheck(index);
		E oldElement = elementData(index);
		elementData[index] =e;
		return oldElement;
	}
	public E get(int index) {
		rangeCheck(index);
		return elementData(index);
	}
	public E remove(int index) {
		rangeCheck(index);
		E removeElement = elementData(index);
		int numMoved = size-index -1;
		if(numMoved >0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] =null;
		return removeElement;
	}
	public void add(int index ,E e) {
		rangeCheck4add(index);
		ensureEnoughCapacity(size+1);
		int numMoved = size-1-index;
		if(numMoved>0) {
			System.arraycopy(elementData, index, elementData, index+1, numMoved);
		}
		elementData[index] =e;
		size++;
	}
	public boolean contains(Object o) {
		return indexOf(o)>=0;
	}
	public void rangeCheck(int index) {
		if(index>=size || index<0) 
			throw new IndexOutOfBoundsException("" + index);
	}
	public void rangeCheck4add(int index) {
		if(index>size || index<0) 
			throw new IndexOutOfBoundsException("" + index);
	}
	@SuppressWarnings("unchecked")
	public E elementData(int index) {
        rangeCheck(index);
		return (E)elementData[index];
	}
	public int indexOf(Object o) {
		if(o==null) {
			for(int i=0;i<size;i++) {
				if(elementData[i]==null)
					return i;
			}
		}else {
			for(int i=0;i<size;i++) {
				if(o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	public void clear() {
		for(int i=0;i<size;i++) {
			elementData[i] =null;
		}
		size= 0;
	}
	public void forEach(Consumer<E> consumer) {
		for(int i =0;i<size;i++) {
			consumer.accept(elementData(i));
		}
	}
	@Override
	public String toString() {
		return "Array [elementData=" + Arrays.toString(elementData) +elementData.length+ ", size=" + size + "]";
	}



}


