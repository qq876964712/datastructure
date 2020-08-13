package datastructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class B_TreeTest {
    @Test
    public void test1(){
        B_Tree b_tree =  B_Tree.create(4);
        b_tree.insert("3");
        b_tree.insert("5");
        b_tree.insert("8");
        b_tree.insert("9");
        b_tree.insert("7");
        b_tree.insert("6");
        b_tree.insert("2");
        b_tree.insert("4");
        b_tree.insert("1");
        b_tree.printString();
        System.out.println();
        System.out.println("分割线");
        b_tree.insert("0");
        b_tree.printString();
    
    }
}