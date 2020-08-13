package datastructure.lineartable;

import java.util.Arrays;
import java.util.RandomAccess;

import org.junit.Test;

public class ArrayListTest implements RandomAccess {

	@Test
	public void test() {
		ArrayList<String> list = new ArrayList<String>();
		list.append("111");
		System.out.println(list.elementData.length);
		list.append("112");
		list.append("113");
		list.append("114");
		list.append("115");
		list.append("116");
		list.append("117");
		list.append("118");
		list.append("119");
		list.append("110");
		list.append("111");
		System.out.println(list.elementData.length+":"+Arrays.toString(list.elementData));
		
		
		
		System.out.println(list.contains("111"));
		
		System.out.println(list.get(1));
		
		System.out.println(list.isEmpty());
		
		list.remove(10);
		
		System.out.println(list.elementData.length+":"+Arrays.toString(list.elementData));
		
		list.insert(3, "插入第三个");
		System.out.println(list.elementData.length+":"+Arrays.toString(list.elementData));

		String search = list.search(s->s.equals("111"));
		System.err.println(search);
		
		list.clear();
		
		System.out.println(list.elementData.length+":"+Arrays.toString(list.elementData));

	}

}
