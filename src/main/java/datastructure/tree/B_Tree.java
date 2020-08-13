package datastructure.tree;

import com.sun.webkit.BackForwardList;
import datastructure.tree.util.TreePrintUtil;

import javax.crypto.SealedObject;
import java.net.BindException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * m阶B树性质:
 *
 * 非叶子节点 最多有m棵子树  即最多m-1个关键字
 * 非叶子节点  子树个数 = 拥有关键字个数 +1
 *
 * 根节点 要么为空  如果不为空 最少有2棵子树
 * 其他非叶子节点 最少 子节点[m/2] 个节点
 * 所有叶子节点都在同一层
 *
 * 非叶子节点
 * 叶子节点 只存在关键字  没有孩子  孩子节点为空 如果说 b树种有n个关键字 ,
 * 那叶子节点 就相当于 查询过程中 这n个关键字的区间  即叶子节点的的孩子null上)
 * 对于m阶 h层  n个关键字的树满足
 * h最小的化 每个节点关键字 都最多 一共有的关键字  n<= ( m-1) (1+m+m^(h-1)) = m^h-1
 * h最大的情况  节点内关键字个数最少,可以理解为 在这n个关键字中查询数据  , 但是都没查询到  公有n+1个区间  指向最后一层的叶子节点
 * 根节点最少有2棵子树 其他非叶子节点 最少 子节点[m/2] 个节点;  叶子节点个数 = 2*  [m/2]^(h-1) <=n+1
 *
 *
 *
 * 节点数据  要插入节点关键字   entry数组 长度 m   index= m-1没有key
 * 4阶 满节点
 *| A | B | C | - | - |
 *| ↓ | ↓ | ↓ | ↓ | - |
 *  ↓   ↓   ↓   x>C
 *  ↓   ↓ B<x<C
 *  ↓  A<x<B
 * x<A
 * 插入操作:
 * 实际插入都是往终端节点插入
 * 插入规则:
 *
 * 如果是叶子节点 :
 *   进行插入操作:
 *   插入后  nextNum 大于 order 进行分裂
 *    分裂 : 在父节点 插入entry  判断父节点是否需要进行分裂 ,继续向上 直到不需要分裂
 *   否则不需要分裂
 *  如果非叶子节点: 继续向下寻找插入位置 ,并且可能得到来自 下层分裂上来的关键字
 *
 *  删除规则
 *  如果删除的是 叶子节点 先直接删除掉
 *  然后 判断 当前 孩子数目
 *  [order/2]
 *  >  直接删除
 *  =  左兄弟节点  大于 order/2 向左兄弟借 同理 向右兄弟借
 *  不够借的情况下  合并 并向上平衡
 *  未实现 只是 给出一些未实现的代码   root的位置 没有考虑 
 */
public class B_Tree {

    private int order;
    private static final int DEFAULT_ORDER =4;
    
    
    private int getMinChildrenNum(){
        return order/2;
    }
    
    private int getMaxChildrenNum() {
        return order ;
    }
    private int size; //关键字的个数
    
    
     Node root;
    
    public static B_Tree create(int order){
        if(order<=0)throw new IllegalArgumentException();
        B_Tree b_tree = new B_Tree();
        b_tree.order = order;
        return b_tree;
    }
    public B_Tree(){
        order =DEFAULT_ORDER;
    }
    
    
    public void printString(){
        if(root==null) return ;
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Node> nodes2 = new LinkedList<>();
        nodes.offer(root);
        Node p ;
        while(!nodes.isEmpty()  || !nodes2.isEmpty() ){
            if(nodes.isEmpty()){
                LinkedList<Node> tmp = nodes;
                nodes= nodes2;
                nodes2 = tmp;
                System.out.println("下一层:");
            }
           p= nodes.poll();
            System.out.print("节点:"+ p+"  大小:"+(p.nextNum-1));
            for(int i=0;i<p.nextNum;i++){
                if(p.children[i].key!=null){
                    System.out.print(",");
                    System.out.print(p.children[i].key);
                }
                if(p.children[i].next!=null) nodes2.offer(p.children[i].next);
            }
            System.out.print("+++");

        }
    
    }


    /**
     * 节点 有num个关键字   list.size  个孩子   最后一个孩子没有关键字
     */
    private class Node {
        int nextNum;
        Entry[] children = new Entry[order+1];
        Node (int num){
            this.nextNum = num;
        }
    
        Node parent;
        
        public int getIndexOfKey(Entry entry){
            for(int i=0;i<nextNum;i++){
                if(entry== children[i])return i;
            }
            
            return -1;
        }
        
        public void setKey(int index,Comparable key){
            children[index].key = key;
        }
    
        public Comparable getKey(int index) {
            return children[index].key;
        }
        public int getIndexOfChild(Node chidNode) {
            for (int i = 0; i < nextNum; i++) {
                if (chidNode == children[i].next) return i;
            }
        
            return -1;
        }
        public Node getChildNode(int index){
            if(index<0|| index>nextNum) throw new IllegalArgumentException();
            return children[index].next;
        }
        public boolean removEntry(Entry entry){
            int index = getIndexOfKey(entry);
            if(index<0 || index >=nextNum)return false;
            for(int i = index;i<nextNum-1;i++){
                children[i] = children[i+1];
            }
            nextNum--;
            return true;
        }
        
        public void  addEntry(Entry entry){
            int i = 0;
            for(;i<nextNum-1;i++){
                if(less(entry.key,children[i].key)){
                    break;
                }
            }
            for (int m = nextNum - 1; m >= i; m--) {
                children[m + 1] = children[m];
            }
            children[i] = entry;
        }
    }
    private static class Entry{
        private Comparable key;
        private Node theNode;
        private Node next;
        public Entry(Comparable key) {
            this.key = key;
        }
    
        public Entry(Comparable key, Node next) {
            this.key = key;
            this.next = next;
        }
        
        
    }

    public Comparable search(Comparable key){
        if(root==null)return null;
        Entry entry = search(root, key);
        if(entry==null) return null;
        return entry.key;
    }
    private class SerachResult{
        boolean isFind;
        int index;
        Node node;
    
        public SerachResult(boolean isFind, int index, Node node) {
            this.isFind = isFind;
            this.index = index;
            this.node = node;
        }
    }
    public Entry search(Node node,Comparable key) {
    
  
        int i = 0;
        for(;i<node.nextNum-1;i++){
            if(!less(key,node.children[i].key))break;
        }
        
        if(isLeafNode(node)){
            if(i==node.nextNum-1){
                return null;
            }else{
                if(eq(key,node.children[i].key)){
                    return node.children[i];
                }
                else return null;
            }
        }else{
            if (i == node.nextNum - 1) {
                search(node.children[i].next, key);
            }else{
                if (eq(key, node.children[i].key)) return node.children[i];

                else search(node.children[i].next, key);
            }
        }
        return null;
    }
    /**
     *插入实际上 就是往 终端节点插入 ()
     */
    public void  insert(Comparable key){
        if(key==null) throw new IllegalArgumentException();
        Node t = inserEntry(root,key);
        size++;
        if(t==null) return;
        root = t;
        
        for(int i=0;i<root.nextNum;i++){
            root.children[i].next.parent = root;
        }
    }
    
    
    public Node inserEntry(Node node , Comparable key){
        Entry data =new Entry(key);
        //所有节点 都包含一个空的节点  不存放关键字 只存放next
        if(root ==null){
            root =new Node(2);
            data.theNode = root;
            root.children[0] = data;
            
            root.children[1] = new Entry(null,null);
            return null;
        }
    
    
        int i=0;
        if(isLeafNode(node)){
            for(;i<node.nextNum;i++){
                if(i == node.nextNum-1) break;
                if(less( key, node.children[i].key ))break;
            }
        }else{
            for(;i<node.nextNum;i++){
                if ( i==node.nextNum-1  ||  less(key, node.children[i].key)){
                    Node splitParetEntry = inserEntry(node.children[i].next, key);
                    if(splitParetEntry!=null){
                        data.key = splitParetEntry.children[0].key;
                        data.next = splitParetEntry.children[0].next;
                        break;
                    }
                    else return null;
                }
            }
        }
        for (int m = node.nextNum - 1; m >= i; m--) {
            node.children[m + 1] = node.children[m];
        }
        node.children[i] = data;
        data.theNode =node;
        node.nextNum++;
        //是否进行分裂
        if (node.nextNum <= order) {
            return null;
        } else {
            return split(node);
        }
    }
    
    /**
     *非叶子节点  一定有子节点  并且 children[0].nexxt !=null
     */
    public boolean isLeafNode(Node e){
        if(e==null || e.children == null || e.children[0]==null || e.children[0].next!=null)return false;
        else return true;
    }
    
    private Node split(Node node){
       Node silbingNode =  new Node(order/2+1);

//        *|* | * | T | - | - |  父节点
//        *|↓ | ↓ | ↓ | ↓ | - |
//                  ↓
//                  ↓
//         ____________________
//        *|A | B | C | D | - |  4阶为例  数组容量为5   把C节点取出来 作为父节点  放到T节点前面 AB作为C的孩子
//        *|↓ | ↓ | ↓ | ↓ | ↓ |

//                  |C|                      T
//                   ↓   num=3               ↓    num =2
//        *|A | B | C | - | - |   |D | - | C | D | - |
//        *|↓ | ↓ | ↓ | - | - |   |↓ | ↓ | ↓ | ↓ | ↓ |
        
        for(int i=0;i<order/2+1;i++){
            silbingNode.children[i] = node.children[i];
            silbingNode.children[i].theNode = silbingNode;
        }
    
      
        Node splitParentNode = new Node(2);
        splitParentNode.children[0] =new Entry(null,null);
        splitParentNode.children[0].key = node.children[order/2].key;
        splitParentNode.children[0].next = silbingNode;
        splitParentNode.children[0].theNode = splitParentNode;
        splitParentNode.children[1] = new Entry(null, node );
    
       int length =0;
       for(int i = order / 2 + 1; i<node.nextNum ; i++){
           node.children[i- (order / 2 + 1)] = node.children[i];
           length++;
       }
       node.nextNum = length;
    
        silbingNode.parent = node.parent;

        return splitParentNode;
    }
    
    public void delete(Comparable key){
        Entry entry = search(root, key);
        
        if(entry==null)return;
    
        deleteNotNull(entry);
    }
    
    public void deleteNotNull(Entry theEntry){
        Node theNode = theEntry.theNode;
        int index = theNode.getIndexOfKey(theEntry);
        if(index==-1)throw new IllegalArgumentException();
        if(isLeafNode(theEntry.theNode)){
            //3种情况
            // 孩子节点 个数> order/2
            // 孩子节点 个数 = order/2    :
            // 兄弟可借
            // 兄弟不可借  合并
            if (theNode.nextNum > order / 2) {
                //直接删除
                theNode.removEntry(theEntry);
            } else {
                theNode.removEntry(theEntry);
                //调整
                balance(theNode);
            }
        }else{
            Entry successor = getSuccessor(theNode, index);
            deleteNotNull(successor);
            theEntry.key = successor.key;
        }
    }
    public void balance(Node theNode){
        Node parent = theNode.parent;
        int theIndex = parent.getIndexOfChild(theNode);
        Node pre, next;
        //兄弟可借
        if (theIndex - 1 >= 0 && (pre = parent.getChildNode(theIndex - 1)).nextNum > order / 2) {
            //左兄弟可借
            leftRotation(theNode);
    
        } else if (theIndex + 1 < parent.nextNum && parent.getChildNode(theIndex + 1).nextNum > order / 2) {
           //借右兄弟t
            rightRotation(theNode);
        } else {
           //合并combine
            combine(theNode);
            //调整父节点
            if(parent.nextNum >order/2){
            
            }else{
                balance(parent);
            }
            
        }
    }
    public void combine(Node  theNode ){
    
    }
    public void leftRotation(Node theNode){
    
    }
    
    public void rightRotation(Node theNode) {

    
    }
    public Entry getSuccessor(Node node ,int index){
        return null;
    }
    public Entry  getPrecursor(Node node ,int index){
        return null;
    }
    
  
    
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }
    
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
    
}
