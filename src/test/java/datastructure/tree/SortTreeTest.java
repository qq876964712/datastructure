package datastructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortTreeTest {
    @Test
    public void BiarySortTest() {
        BinarySortTree<String> tree = new BinarySortTree<>();
        tree.add("E");
        tree.add("B");
        tree.add("C");
        tree.add("F");
        tree.add("G");
        tree.add("A");
        tree.print();
        tree.remove("B");
        tree.print();
        tree.remove("A");
        tree.print();
        tree.remove("E");
        tree.print();
    
    }
    
    @Test
    public void AVLTREETest() {
        AVLTree tree = new AVLTree();

        tree.add("4");
        tree.print();
        tree.add("8");
        tree.print();
        tree.add("9");
        tree.print();
        tree.add("2");
        tree.print();
        tree.add("3");
        tree.print();
        
        tree.remove("9");
        tree.print();

    }
}