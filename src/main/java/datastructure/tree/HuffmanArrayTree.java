package datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HuffmanArrayTree {
    private Node[] elements;
    
    public static HuffmanArrayTree create(Map<String, Double> map) {
    
        HuffmanArrayTree HuffmanArrayTree = new HuffmanArrayTree();
        if (map == null || map.size() < 2) throw new IllegalArgumentException();
        int len = map.size();
    
        HuffmanArrayTree.elements = new Node[len * 2 - 1];
    
        Set<String> keys = map.keySet();
        int index = 0;
        for (String key : keys) {
            HuffmanArrayTree.elements[index++] = new Node(key, map.get(key));
        }
        
        
        for (int m = index; m < HuffmanArrayTree.elements.length; m++) {
            HuffmanArrayTree.createNode(m);
        }
        return HuffmanArrayTree;
    }
    
    
    public  void createNode(int endindex) {
        int thefirstMin = endindex - 1;
        int theSecdMin = -1;
    
        for (int i = 0; i < endindex - 1; i++) {
            Node node = elements[i];
            if (node.parent != -1) continue;
            theSecdMin = i;
            if (theSecdMin != -1) break;
            
        }
        //初始化 最小值的位置为 endindex -1   第二小的值位于第一个 没有父节点的node
        
        for (int i = 0; i < endindex; i++) {
            Node node = elements[i];
            if (node.parent != -1) continue;
            if (elements[thefirstMin].weight > node.weight) {
                if (elements[theSecdMin].weight > elements[thefirstMin].weight) {
                    theSecdMin = thefirstMin;
                    thefirstMin = i;
                }
            } else {
                if (elements[theSecdMin].weight > node.weight) {
                    theSecdMin = i;
                }
            }
            elements[thefirstMin].parent=endindex;
            elements[theSecdMin].parent = endindex;
            elements[endindex] = new Node(null, theSecdMin, thefirstMin, elements[thefirstMin].weight + elements[thefirstMin].weight);
        }
    
    
    }
    
    
}

class Node {
    String data;
    int parent = -1;
    int lchild, rchild;
    double weight;
    
    public Node(String data, double weight) {
        this.data = data;
        this.weight = weight;
    }
    
    public Node(String data, int lchild, int rchild, double weight) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
        this.weight = weight;
    }
}