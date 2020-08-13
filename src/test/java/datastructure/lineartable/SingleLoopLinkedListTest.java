package datastructure.lineartable;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLoopLinkedListTest {
    @Test
    public void test1(){
         SingleLoopLinkedList<String> s = new  SingleLoopLinkedList<>();
         s.append("11");
         s.append("22");
         s.insert(0,"00");
         System.out.println(s +"head : "+s.head.data+"   rear"+ s.rear.data+"");
         s.remove(2);
        s.remove(1);
        System.out.println(s + "head : " + s.head.data + "   rear" + s.rear.data + "");
    
    }
    @Test
    public void testJosephu(){
        SingleLoopLinkedList<String> s = new SingleLoopLinkedList<>();
        for(int i=1;i<21;i++){
            s.append(""+i);
        }
        
        System.out.println(s);
        //s.josePhu(10);
        s.josePhu2(10);
        
    }
    
    @Test
    public void test2() {
        SingleLoopLinkedList2<String> s = new SingleLoopLinkedList2<>();
//        s.insert(0,"11");
//        s.insert(1, "22");
//        s.insert(0, "00");
        s.append("11");
        s.append("22");
        s.append("33");
    
        s.remove(2);
        System.out.println(s.toString());
    
        s.remove(1);
        System.out.println(s.toString());
        s.remove(0);
        System.out.println(s.toString()+"--");
    
    }
    
    @Test
    public void testJosephu3() {
        SingleLoopLinkedList2<String> s = new SingleLoopLinkedList2<>();
        for (int i = 1; i < 21; i++) {
            s.append("" + i);
        }
        
        System.out.println(s);
        s.Josephu(10);
        
    }
}