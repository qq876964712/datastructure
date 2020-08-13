package datastructure.lineartable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayList<E> implements LinearList<E>{
	
	public Object[] elementData;
	private static final int default_capacity =10;
	private static final Object[]emptyElements = {};
	private int size;

	

	public ArrayList() {
		elementData =emptyElements;
	}

	
	public ArrayList(int initalCapacity) {
		if(initalCapacity>0) {
			elementData = new Object[] {initalCapacity};
		}else if(initalCapacity ==0) {
			elementData = emptyElements;
		}else {
			throw new IllegalArgumentException();
		}
	}


	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return elementData(index);
	}

	@Override
	public void insert(int index, E element) {
		rangeCheck4add(index);
		ensureEnoughCapacity(size+1);
		
		int numMoved = size-1-index;
		if(numMoved > 0) {
			System.arraycopy(elementData, index,elementData, index+1, numMoved);
		}
		elementData[index]=element;
		size++;
	}

	@Override
	public void append(E element) {
		insert(size, element);
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E oldElement = elementData(index);
		elementData[index] = element;
		return oldElement;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		E oldElement = elementData(index);
		int numMoved = size -1 -index;
		if(numMoved>0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size]=null;
		return oldElement;
	}

	@Override
	public E search(Predicate<E> predicate) {
		Objects.requireNonNull(predicate);
		for(int i=0;i<size;i++) {
			E theElement = elementData(i);
			if(theElement!=null  && predicate.test(theElement) ) return theElement;
		}
		return null;
	}

	@Override
	public void clear() {
		for(int i=0;i<size;i++) {
			elementData[i]=null;
		}
		size=0;
	}
	
	
	@Override
	public boolean contains(Object o) {
		return indexOf(o)>=0;
	}
	
	int indexOf(Object o) {
		if(o==null) {
			for(int i=0;i<size;i++) {
				if(elementData[i]==null)return i;
			}
		}else {
			for(int i=0;i<size;i++) {
				if(o.equals(elementData[i]))return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	E elementData(int index) {
		   return (E)elementData[index];
	}
	private void rangeCheck(int index) {
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException(""+index);
	}
	private void rangeCheck4add(int index) {
		if(index>size || index < 0) 
			throw new IndexOutOfBoundsException(""+index);
	}
	private int caculateMinCapacity(Object[]elementData,int minCapacity) {
		if(elementData == emptyElements) {
			minCapacity = Math.max(default_capacity, minCapacity);
		}
		return minCapacity;
	}
	private void ensureEnoughCapacity(int minCapacity) {
		growCapacity(caculateMinCapacity(elementData, minCapacity));
	}
	private void growCapacity(int minCapacity) {
		if(elementData.length  - minCapacity <0) {
			int oldCapacity = elementData.length;
			int newCapacity = oldCapacity + (oldCapacity >>1 );
			if(newCapacity - minCapacity <0) newCapacity = minCapacity;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	
}
