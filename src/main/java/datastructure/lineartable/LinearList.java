package datastructure.lineartable;

import java.util.function.Predicate;

/**
 * 线性表的一组操作
 * @author 87696
 *
 */
public interface LinearList<E>{


	int size();
	boolean isEmpty();
	/**
	 * 按位查找操作。获取表中第 i 个位置的元素的值 
	 * @param index
	 * @return
	 */
	E get(int index);
	/**
	 * 插入操作。在表中的第 i 个位置之前插入指定元素 e
	 * @param index
	 * @param element
	 */
	void insert(int index ,E element);
	/**
	 * 在表中 最后加入指定元素e
	 * @param element
	 */
	void append(E element);
	/**
	 * 设置表中第i个位置为指定元素e
	 * @param index
	 * @param element
	 */
	E set(int index, E element);// 设置第i个元素值为x
	/**
	 *  删除操作。删除表 L 中第 i 个位置的元素，并返回删除元素的值
	 * @param index
	 * @return
	 */
	E remove(int index);
	/**
	 * e.compareto  ==0 返回第一个满足的
	 * @param e
	 * @return
	 */
	E search(Predicate<E> e);
	
	boolean contains(Object o);
	
	void clear();

}
