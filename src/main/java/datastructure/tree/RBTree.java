package datastructure.tree;

public class RBTree {
    public static int RED =1;
    public static int BLACK =2;
    
    
    private Node root;
    
    /**  红黑树添加节点
     *  依据 :
     * 1)添加的节点都是红色 ;2)节点非红即黑 3)根节点为黑色
     * 4) 红色节点 的子节点 双黑 或者  双空
     * 5) 以 任意节点为 根 的树  他的每个叶子节点 黑高度一样
     * 红黑树节点添加时  主要出现一下几种场景
     * 1)父节点 p 为黑色  : 直接插入
     * 2)父节点p为红色  , 此时  祖父节点pp肯定时黑色 , 就需要判断叔父节点 u 是红色  或者 空位黑色
     *
     *  2.1 u是红色   :  父节点 p 和  u 变成黑色  pp节点变成红色  会影响上层节点的颜色变化
     *  2.2 u是空 或者黑色 : ( 以父节点 p = pp.left 为例 )
     *    2.2.1 插入节点 i = p.left  (左左双红)
     *      首先变色  pp变红 p变黑   以pp节点 为轴  右旋  不会影响上层节点的颜色变化
     *    2.2.2 插入节点 i = p.right
     *      首先  以p节点为轴 左旋 (变成左左双红) 接下来按照左左双红处理
     *    2.2.3 右右双红  2.3.4 右左双红   同上面两种情况镜像
     *
     * 红黑树删除节点:
     * 和二叉排序树  的删除一闪
     * 1.被删除节点为叶子节点  比较复杂
     * 2.被删除节点包含1个子节点   p 黑    lchid 红  ; p黑 rchid红 其他情况不可能出现
     *  这种状况 直接删除即可
     * 3.被删除节点包含2个节点  这种 变为删除节点的后继  然后把这个后继的值替换到该节点;这样 被删除的节点包含2个节点就转成前两种情况
     *
     * 分析被删除节点为叶子节点的情况
     * 1.1该节点为红色 可以直接删除
     * 1.2该节点为黑色  从简单到复杂的考虑   性质A:不管 父节点是红是黑  他的兄弟子树  黑高肯定是和他一致的
     *   1.2.1
     *   如果兄弟无子节点,兄弟必为黑色  (根据性质A)
     *   {
     *   父节点红色, 父黑 兄弟红  删除即可,
     *   父亲为黑色,3个黑色节点
     *      此时被删除子树 不能像兄弟树 要一个黑色节点了 只能尝试向父代树借
     *      先把兄弟节点 变红,删除当前节点, 维持 整个以父代为子树 都是减少一个黑色子节点
     *    然后以不删除节点的方式   删除处理父节点
     *   }
     *   如果兄弟有子节点{
     *       1.如果兄弟节点为红色{
     *          此时兄弟肯定2个黑色子节点,父节点为黑色
     *          变换成 父节点 和兄弟节点互相换颜色  旋转变成 父节点为红色  兄弟节点无叶子节点的情况
     *       }else{ 兄弟节点为黑色
     *           如果兄弟节点黑色 有同边 子节点{
     *               兄弟同边子节点变黑  父节点兄弟 颜色互换 一次旋转 删除该节点
     *           }else if(如果兄弟节点黑色没有同边 子节点){
     *               以兄弟节点为轴旋转 子节点变到兄弟节点位置   转换为 兄弟节点同边子节点状态
     *           }
     *       }
     *   }
     *
     *
     *
     *
     */
    public void add(String s){
        if(s==null)return;
        if(root==null) root =new Node(Color.blank,s);
        else{
            Node p = root;
            for (; ; ) {
                if (s.compareTo(p.data) > 0) {
                    if (p.rchild == null) {
                        p.rchild = new Node(Color.red, s, p);
                        p = p.rchild;
                        break;
                    }
                    p = p.rchild;
                } else {
                    if (p.lchild == null) {
                        p.lchild = new Node(Color.red, s, p);
                        p = p.lchild;
                        break;
                    }
                    p = p.lchild;
                }
            }
        insertFixup(p);
        }
    }
    public void insertFixup(Node e){
        Node parent = e.parent;
        if(parent!=null &&  isRed(parent)){  //如果父节点节点为红色 他肯定存在祖父节点 并且肯定祖父节点黑色
            Node gparent = parent.parent;
            if(parent == gparent.lchild){
                Node uncle = gparent.rchild;
                if( !isRed(uncle)){
                    if(e == parent.lchild){
                       // 左左双红
                    }else{
                       //左右双红  转成  左左双红
                       leftRotation(parent);
                       Node tmp =parent;
                       parent = e;
                       e =tmp;
                    }
                    //左左双红
                    setNodeColor(parent, Color.blank);
                    setNodeColor(gparent, Color.red);
                    rightRotation(gparent);
                }else{
                   //父亲 和叔叔全是红色   祖父为黑色  影响祖父之上的节点
                    setNodeColor(parent,Color.blank);
                    setNodeColor(uncle, Color.blank);
                    setNodeColor(gparent, Color.red);
                    insertFixup(gparent);
                }
            }else{
                Node uncle = gparent.lchild;
                if (!isRed(uncle)) {
                    if(e == parent.rchild){
                        //右右双红
                    }else{
                        //右左双红 转成右右双红
                        rightRotation(parent);
                        Node tmp =parent;
                        parent =e;
                        e =parent;
                    }
                    setNodeColor(parent,Color.blank);
                    setNodeColor(gparent, Color.red);
                    leftRotation(gparent);
                }else{
                    //父亲 和叔叔全是红色   祖父为黑色  影响祖父之上的节点
                    setNodeColor(parent, Color.blank);
                    setNodeColor(uncle, Color.blank);
                    setNodeColor(gparent, Color.red);
                    insertFixup(gparent);
                }
            }
        }else{
            //插入不需要调整
        }
    }
    public  boolean insert(String data){
        Node insertNode = new Node(data);
        boolean success =false;
        if(root ==null){
            root = insertNode;
            success =true;
        }else{
            success = insertNode(insertNode);
        }
        return success;
    }
    private boolean insertNode(Node e){
        Node p = root;
        for(;;){
            if(e.data.compareTo(p.data) ==0){
                return false;
            }else if(e.data.compareTo(p.data)>0){
                if(p.rchild == null){
                    p.rchild = e;
                    e.parent = p;
                    break;
                }
                p = p.rchild;
            }else{
                if (p.lchild == null) {
                    p.lchild = e;
                    e.parent = p;
                    break;
                }
                p = p.lchild;
            }
        }
        insertFixup2(e);
        return true;
    }
    
    private void insertFixup2(Node e) {
    
        Node parent,gparent;
        while( ( parent = e.parent) !=null && isRed(parent)){
            //红色节点肯定有父节点
            gparent = parent.parent;
            boolean uncleInRight = gparent.lchild == parent?true:false;
            Node uncle = uncleInRight?gparent.rchild: gparent.lchild;
            if(!isRed(uncle)){
                //双红
                if(uncleInRight){
                    if(e == parent.lchild){
                    
                    }else{
                        leftRotation(parent);
                        Node tmp = parent;
                        parent = e;
                        e  = tmp;
                    }
                    setNodeColor(parent,Color.blank);
                    setNodeColor(gparent, Color.red);
                    rightRotation(parent);
                    
                   // e = e.parent;
                    break;//二者一样的
                }else{
                    if(e == parent.rchild){
                    
                    }else{
                        rightRotation(parent);
                        Node tmp = parent;
                        parent = e;
                        e = tmp;
                    }
                    setNodeColor(parent, Color.blank);
                    setNodeColor(gparent, Color.red);
                    leftRotation(parent);
    
                    // e = e.parent;
                    break;//二者一样的
                }
            }else{
                setNodeColor(uncle,Color.blank);
                setNodeColor(parent, Color.blank);
                setNodeColor(gparent, Color.red);
                e = gparent;
            }
        }
    }
    
    public void remove(String data){
        Node node = getNode(data);
        if(node==null)return;
        deleteNode(node);
    
    }
    public Node getNode(String data){
        Node p = root;
        for(;p==null || data.compareTo(p.data)==0;){
            if(data.compareTo(p.data)>0){
                p = p.rchild;
            }else{
                p = p.lchild;
            }
        }
        return p;
    }
    
    public void deleteNode(Node delete){
        if(delete.lchild==null && delete.rchild==null){
            if(isRed(delete)){
                delete_red_leaf(delete,true);
            }else{
                delete_blank_leaf(delete, true);
            }
        }else if(delete.lchild == null || delete.rchild == null){
            boolean hasRight = delete.lchild==null ? true:false;
            if(hasRight){
                delete.data = delete.rchild.data;
                delete.rchild =null;
            }else{
                delete.data = delete.lchild.data;
                delete.lchild =null;
            }
        }else{
            Node successor = getSuccessor(delete);
            deleteNode(successor);
            delete.data = successor.data;
        }
    }
    private void delete_red_leaf(Node node,boolean needDel){
        Node parent = node.parent;
        boolean deleteInLeft = parent.lchild == node ? true : false;
        if (deleteInLeft) {
            parent.lchild = null;
        } else {
            parent.rchild = null;
        }
    }
    private void delete_blank_leaf(Node node, boolean needDel) {
        Node parent = node.parent;
        if(parent!=null){
            boolean nodeInLeft = parent.lchild == node ? true:false;
            //该节点肯定有兄弟    不确定是红黑   红色  有2个非空黑色 子节点;  黑色  确定子节点
            Node sibling = nodeInLeft ? parent.rchild: parent.lchild;
            Node remoteNephew = null == sibling ? null : (nodeInLeft ? sibling.rchild : sibling.lchild);
            Node nearNephew = null == sibling ? null : (nodeInLeft ? sibling.lchild : sibling.rchild);
            if(isRed(sibling)){
                //拥有双黑 子节点
                delete_blank_leaf_sibling_red(node);
                
            }else if(remoteNephew !=null && isRed(remoteNephew)){
                delete_blank_leaf_remoteNephew_red(node);
            }else if(nearNephew !=null && isRed(nearNephew)){
                delete_blank_leaf_nearNephew_red(node);
            }else{
                //sibling 是一个叶子节点
                if(isRed(parent)){
                    delete_blank_leaf_parent_red_nochild_sibling(node);
                }else{
                    setNodeColor(sibling,Color.red);
                    delete_blank_leaf(parent,false);
                }
            }
        }
        
        if(needDel){
            if(parent==null){
                root =null;
            }else{
                if(parent.lchild == node){
                    parent.lchild =null;
                }else{
                    parent.rchild = null;
                }
            }
        }
    }
    private void delete_blank_leaf_sibling_red(Node node){
        Node parent = node.parent;
        boolean nodeInLeft = parent.lchild == node ? true : false;
        Node sibling = nodeInLeft ? parent.rchild : parent.lchild;
        Node remoteNephew = null == sibling ? null : (nodeInLeft ? sibling.rchild : sibling.lchild);
        Node nearNephew = null == sibling ? null : (nodeInLeft ? sibling.lchild : sibling.rchild);
        setNodeColor(parent,Color.red);
        setNodeColor(sibling, Color.blank);
        if(nodeInLeft){
            leftRotation(parent);
        }else{
            rightRotation(parent);
        }
        //此时 父节点 为红色 兄弟节点为黑色 无子节点
        delete_blank_leaf_parent_red_nochild_sibling(node);
    }
    private void delete_blank_leaf_remoteNephew_red(Node node){
        Node parent = node.parent;
        boolean nodeInLeft = parent.lchild == node ? true : false;
        Node sibling = nodeInLeft ? parent.rchild : parent.lchild;
        Node remoteNephew = null == sibling ? null : (nodeInLeft ? sibling.rchild : sibling.lchild);
        setNodeColor(remoteNephew,Color.blank);
        setNodeColor(sibling,parent.c);
        setNodeColor(parent, Color.blank);
        if(nodeInLeft){
            leftRotation(parent);
        }else{
            rightRotation(parent);
        }
    }
    private void delete_blank_leaf_nearNephew_red(Node node){
        Node parent = node.parent;
        boolean nodeInLeft = parent.lchild == node ? true : false;
        Node sibling = nodeInLeft ? parent.rchild : parent.lchild;
        Node nearNephew = null == sibling ? null : (nodeInLeft ? sibling.lchild : sibling.rchild);
        setNodeColor(nearNephew, Color.blank);
        setNodeColor(sibling, Color.red);
        if(nodeInLeft){
            rightRotation(sibling);
        }else{
            leftRotation(sibling);
        }
        //兄弟和近端侄子 互换颜色 并以sibling 旋转  转换成   blank_leaf_sibling_red
        delete_blank_leaf_sibling_red(node);
    }
    private void delete_blank_leaf_parent_red_nochild_sibling(Node node){
        Node parent = node.parent;
        boolean nodeInLeft = parent.lchild == node ? true : false;
        Node sibling = nodeInLeft ? parent.rchild : parent.lchild;
        setNodeColor(parent,Color.blank);
        setNodeColor(sibling, Color.red);
    }
    public Node getSuccessor(Node node) {
        if(node==null)return null;
        Node p = node.rchild;
        if(p!=null){
            while(p.lchild!=null){
                p = p.lchild;
            }
            return p;
        }else{
            Node parent =node.parent;
            while( parent!=null  &&  p == parent.rchild ){
                 p = parent;
                 parent =parent.parent;
            }
            return parent;
        }
    }
    
    public void setNodeColor(Node e,Color c){
        if(e==null)return;
        e.c  =c;
    }
    public boolean isRed(Node e){
        if( e!=null && e.c == Color.red)return true;
        return false;
    }
    
    private void leftRotation(Node a){
        Node b = a.rchild;
        a.rchild = b.lchild;
        if(b.lchild !=null) {
            b.lchild.parent = a;
        }
        b.parent = a.parent;
        if(a.parent ==null){
            root = b;
        }else if(a.parent.lchild == a){
            a.parent.lchild =b;
        }else{
            a.parent.rchild = b;
        }
        a.parent = b;
        b.lchild = a;
    }
    
    private void rightRotation(Node a) {
        Node b = a.lchild;
        a.lchild = b.rchild;
        if (b.rchild != null) {
            b.rchild.parent = a;
        }
        b.parent = a.parent;
        if (a.parent == null) {
            root = b;
        } else if (a.parent.lchild == a) {
            a.parent.lchild = b;
        } else {
            a.parent.rchild = b;
        }
        a.parent = b;
        b.rchild = a;
    }
    
    
    
    
    
    
    
    
    
    enum Color{
        red, blank
    }
    
    class Node{
        Color c;
        String data;
        Node lchild,rchild;
        Node parent;
    
        public Node(String data) {
            this.c = Color.red;
            this.data = data;
        }
    
        public Node(Color c ,String data) {
            this.c = c;
            this.data = data;
        }
        public Node(Color c, String data, Node parent) {
            this.c = c;
            this.data = data;
            this.parent = parent;
        }
    }
    
}
