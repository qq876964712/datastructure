package datastructure.tree;

public class GenTable<E> {
    
    private static int TAG_ITEM = 0;//原子节点
    private static int TAG_TABLE =1;//表节点
    
    private Node<E> head;
    
    class Node<E>{
        E data;
        private int tag;
        private Node<E> child;
        private Node<E> next;
    
        public Node() {
        }
        
    }
    
    
    public Node<E> appendTableNode(E data){
       return null;
    }
    
    public Node<E> appendNode(E data){
        if(head==null){
        }
        return null;
    }
}
