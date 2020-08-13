package datastructure.tree;

import java.util.LinkedList;
import java.util.List;

public class MulXTree<E> {
    
    Node<E> root;

    static class Node<E>{
        Node<E> parent;
        E data;
        List<Node<E>> children = new LinkedList<>();
    }
}
