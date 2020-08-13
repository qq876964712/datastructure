package datastructure.tree;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HuffmanTree<E> {
    
    
    Node<E> root;
    
    public static <E> HuffmanTree<E> create(List<Node<E>>  nodes){
        if(nodes==null || nodes.size()<2)throw new IllegalArgumentException();
        for(int i=nodes.size()-1;i>=1;i--){
            quickSort(nodes,0,nodes.size()-1);
            Node<E> thefirChild = nodes.remove(i);
            Node<E> thesecChild  = nodes.remove(i-1);
    
            Node<E> parent = new Node<E>();
            parent.weight = thefirChild.weight+thesecChild.weight;
            if(thefirChild.weight < thesecChild.weight){ //保证 有孩子的都在右子树
                parent.rchild = thefirChild;
                parent.lchild = thesecChild;
            }else{
                parent.rchild = thesecChild;
                parent.lchild = thefirChild;
    
            }
            nodes.add(parent);
        }
        HuffmanTree<E> eHuffmanTree = new HuffmanTree<>();
        eHuffmanTree.root = nodes.get(0);
        return eHuffmanTree;
    }
    
    static class  Node<E>{
        double weight;
        E data;
        Node<E> rchild,lchild;
    
        public Node() {
        }
    
        public Node(double weight, E data, Node<E> rchild, Node<E> lchild) {
            this.weight = weight;
            this.rchild = rchild;
            this.lchild = lchild;
            this.data =data;
        }
        
        
    }
    
    /**
     *快速排序,将权值最小的放在后面
     */
    public static<E> void quickSort(List<Node<E>> nodes, int left ,int right  ){
        if(left>right)return;
        int index = getIndex(nodes,left,right);
        quickSort(nodes,left,index-1);
        quickSort(nodes,index+1,right);

    }
    public static <E> int getIndex(List<Node<E>> nodes, int left, int right){
        Node<E> eNode = nodes.get(left);
        for(; left< right;){
            for(; left < right && nodes.get(right).weight <= eNode.weight; right--){
            
            }
            nodes.set(left,nodes.get(right));
            for (; left < right && nodes.get(left).weight >= eNode.weight; left++) {
        
            }
            nodes.set(right,nodes.get(left));
        }
        nodes.set(left,eNode);
        return left;
    }
    
    public String toString() {
        LinkedList<Node<E>> parentQueue = new LinkedList<>();
        LinkedList<Node<E>> childQueue = new LinkedList<>();
        StringBuffer buffer = new StringBuffer();
        childQueue.offer(root);
        while(!parentQueue.isEmpty() || !childQueue.isEmpty()){
            if(!parentQueue.isEmpty()){
                Node<E> poll = parentQueue.poll();
                String format = MessageFormat.format("该节点 weight :{0} ,data : {1} ", poll.weight, poll.data);
                buffer.append(format + "   ");
                if (poll.lchild != null) {
                    childQueue.offer(poll.lchild);
                }
                if (poll.rchild != null) {
                    childQueue.offer(poll.rchild);
                }
            }else{
                buffer.append("\n");
                LinkedList<Node<E>> tmp = parentQueue;
                parentQueue = childQueue;
                childQueue = tmp;
            }
        }
        
        return buffer.toString();
    }
    
    
    public HashMap<E,String> map = new HashMap<>();
    
    public HashMap<E, String> getMap() {
        return map;
    }
    
    public void collectionNodeCode(){
        collectionNodeCode(root.lchild , "0" ,new StringBuffer());
        collectionNodeCode(root.rchild, "1", new StringBuffer());
    
    }
    public void collectionNodeCode(Node<E> node , String code ,StringBuffer buffer){
        if(node==null)return;
        buffer = new StringBuffer(buffer).append(code);
        if(node.data==null){
            collectionNodeCode(node.lchild,"0",buffer);
            collectionNodeCode(node.rchild, "1", buffer);
        }else{
            map.put(node.data,buffer.toString());
        }
        
    }
    public static void main(String[] args) {
 
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        nodes.add(new Node<String>(9," ",null,null));
        nodes.add(new Node<String>(5, "a", null, null));
        nodes.add(new Node<String>(5, "i", null, null));
        nodes.add(new Node<String>(4, "e", null, null));
        nodes.add(new Node<String>(4, "k", null, null));
        nodes.add(new Node<String>(4, "l", null, null));
        nodes.add(new Node<String>(2, "o", null, null));
        nodes.add(new Node<String>(2, "v", null, null));
        nodes.add(new Node<String>(2, "j", null, null));
        nodes.add(new Node<String>(1, "u", null, null));
        nodes.add(new Node<String>(1, "y", null, null));
        nodes.add(new Node<String>(1, "d", null, null));
    
        HuffmanTree<String> stringHuffmanTree = HuffmanTree.create(nodes);
        System.out.println(stringHuffmanTree);
    System.out.println("====");
        stringHuffmanTree.collectionNodeCode();
    System.out.println(stringHuffmanTree.getMap());
    }
}
