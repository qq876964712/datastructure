package datastructure.lineartable.stackqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RecursionAndStackTest {
    @Test
    public void HanoiTest(){
        Hanoi h =new Hanoi();
        h.hanoi(10,"A","B","C");
    }
    
    @Test
    public void MazeTest() {
        Maze maze = new Maze();
        maze.getPath1();
        System.out.println(maze.printPath1());

    }
    
    @Test
    public void MazeTest2() {
        Maze maze = new Maze();
        maze.getPath2();
        System.out.println(maze.printPath2());

    }
    
    @Test
    public void queens8() {
        Eightqueens q = new Eightqueens();
        q.place(1);
        System.out.println(q.num);
    }
}
class Hanoi{
    
    /**
     * 有三根杆子A，B，C。A杆上有N个(N>1)穿孔圆盘，盘的尺寸由下到上依次变小。
     * 要求按下列规则将所有圆盘移至C杆： 每次只能移动一个圆盘； 大盘不能叠在小盘上面。
     * 提示：可将圆盘临时置于B杆，也可将从A杆移出的圆盘重新移回A杆，但都必须遵循上述两条规则。
     *
     *   问：如何移？最少要移动多少次？
     * ————————————————
     */
    public void hanoi(int  n ,String from, String buffer ,String to){
        if(n==0)return;
        if(n==1){
            System.out.println("从:"+from +" 移动到 :"+to);
            return;
        }
        hanoi(n-1,from,to,buffer);
        hanoi(1,from,buffer,to);
        hanoi(n-1, buffer, from, to);
    }
    

}


/**
 * 迷宫问题
 */
class Maze{
    //getpath1 回溯
    //getpath2 递归调用
    
    class Dot {
        private int x;
        private int y;
        
        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
    }
    class Block extends Dot {
        private int dir;
        
        public Block(int x, int y) {
            super(x, y);
        }
        
        public int getDir() {
            return dir;
        }
        
        public void changeDir() {
            dir++;
        }
    
        @Override
        public String toString() {
            return "(" +
                    "x=" + super.x +
                    ", y=" + super.y +
                    ')';
        }
    }
   static int[][]map = {                           //迷宫地图,1代表墙壁，0代表通路
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//0
        {1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
        {1, 0, 0, 1, 1, 0, 0, 1, 0, 1},//2
        {1, 0, 1, 0, 0, 1, 1, 0, 0, 1},//3
        {1, 0, 1, 1, 1, 0, 0, 0, 0, 1},//4
        {1, 0, 0, 0, 1, 0, 1, 0, 0, 1},//5
        {1, 0, 1, 0, 0, 0, 1, 0, 0, 1},//6
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    /**(x=1, y=1)(x=2, y=1)(x=2, y=2)(x=1, y=2)(x=1, y=3)(x=1, y=4)(x=1, y=5)(x=2, y=5)(x=3, y=5)(x=3, y=6)(x=4, y=6)(x=5, y=6)(x=5, y=5)(x=5, y=4)(x=6, y=4)(x=7, y=4)(x=7, y=3)(x=8, y=3)(x=8, y=4)(x=8, y=5)(x=7, y=5)(x=7, y=6)(x=8, y=6)(x=8, y=7)(x=8, y=8)(x=9, y=8)
     * (x=1, y=1)(x=2, y=1)(x=2, y=2)(x=1, y=2)(x=1, y=3)(x=1, y=4)(x=1, y=5)(x=2, y=5)(x=3, y=5)(x=3, y=6)(x=4, y=6)(x=5, y=6)(x=5, y=5)(x=5, y=4)(x=6, y=4)(x=7, y=4)(x=7, y=3)(x=8, y=3)(x=8, y=4)(x=8, y=5)(x=7, y=5)(x=7, y=6)(x=8, y=6)(x=8, y=7)(x=8, y=8)(x=9, y=8)
     */
    static int[][]mark=new int[10][10];  //表示已经走过
    static int maxX= 10-1;
    static int maxY= 10-1;
    static int startX =1;
    static int startY =1;
    
    public static LinkedStack<Block> stack = new LinkedStack<>(); //记录路径
    
    public void getPath1(){
        if (startX > maxX || startY > maxY || startX < 0 || startY < 0) return;
        if(map[startY][startX]!=0)return;
    
        Block block = new Block(startX, startY);
        stack.push(block);
        while(!stack.isEmpty()){
            Block curBlock = stack.peek();
            int x = curBlock.getX();
            int y = curBlock.getY();
            int dir = curBlock.getDir(); // 0 1 2 3 左 上 右 下
            mark[y][x]=1;
            if(x==maxX || y==maxY || x==0 || y==0) {
                
                return; //可以选择 继续 不结束只记录
            };
            
            switch (dir){
                case 0:
                    if(x-1>=0 && map[y][x-1] ==0 && mark[y][x-1]==0){
                        stack.push(new Block(x - 1,y));
                    }
                    curBlock.changeDir();
                    continue;
                case 1:
                    if (y - 1 >= 0 && map[y - 1][x] == 0 && mark[y - 1][x] == 0) {
                        stack.push(new Block(x, y-1));
                    }
                    curBlock.changeDir();
                    continue;
                case 2:
                    if (x + 1 <=maxX && map[y][x + 1] == 0 && mark[y][x + 1] == 0) {
                        stack.push(new Block(x+1, y));
                    }
                    curBlock.changeDir();
                    continue;
                case 3:
                    if (y + 1 <=maxY && map[y+1][x] == 0 && mark[y+1][x] == 0) {
                        stack.push(new Block(x, y+1));
                    }
                    curBlock.changeDir();
                    continue;
            }
            mark[y][x] =0; //标记为变成0
            stack.pop();
        }
    }
    
    public String printPath1(){
        String s="";
        while(!stack.isEmpty()){
            s = stack.pop().toString()+s;
        }
        return s;
    }
    
    public static LinkedStack<Block> stack2 = new LinkedStack<>(); //记录路径
    public boolean success =false;
    public static ArrayList<String> paths =new ArrayList<>();
    public void getPath2(){
        if (startX > maxX || startY > maxY || startX < 0 || startY < 0) return;//第一次调用的时候
        if(map[startY][startX]!=0)return;
        deepFirst(startX,startY);
    }
    /**
     * 递归 和深度优先遍历
     */
    public void deepFirst(int x, int y) {
        Block block = new Block(x, y);
        stack2.push(block);
        mark[y][x] = 1;
        
        
        if (x == maxX || y == maxY || x == 0 || y == 0) {
            //只记录 不结束 可以选择这样
            return;
        }

        if (x - 1 >= 0 && map[y][x - 1] == 0 && mark[y][x - 1] == 0) {
            deepFirst(x - 1, y);
        }
        if (y - 1 >= 0 && map[y - 1][x] == 0 && mark[y - 1][x] == 0) {
            deepFirst(x, y - 1);
        }
        if (x + 1 <= maxX && map[y][x + 1] == 0 && mark[y][x + 1] == 0) {
            deepFirst(x + 1, y);
        }
        if (y + 1 <= maxY && map[y + 1][x] == 0 && mark[y + 1][x] == 0) {
            deepFirst(x, y + 1);
        }
        if (!stack2.isEmpty()) {
            stack2.pop();
            mark[y][x] = 0;
        }
        
    }
    
    public String printPath2() {
        String s = "";
        while (!stack2.isEmpty()) {
            s = stack2.pop().toString() + s;
        }
        return s;
    }
    
    
    class WideBlock  extends Dot{
        WideBlock parent;
        public WideBlock(int x, int y,WideBlock parent) {
            super(x, y);
            this.parent =parent;
        }
    }
    
    public static LinkedStack<Block> stack3 = new LinkedStack<>(); //记录路径
    Queue<WideBlock> queue = new LinkedList<>();
    /**
     * 广度优先遍历
     */
    public void getPath3(){
        if (startX > maxX || startY > maxY || startX < 0 || startY < 0) return;//第一次调用的时候
        if(map[startY][startX]!=0)return;
        queue.offer(new WideBlock(startX,startY,null));
        mark[startY][startX] = 1;
    
        while(queue.peek()!=null){
            WideBlock block = queue.poll();
            int x = block.getX();
            int y = block.getY();
    
            if (x == maxX || y == maxY || x == 0 || y == 0) {
                //只记录 不结束 可以选择这样
                return;
            }
    
            if (x - 1 >= 0 && map[y][x - 1] == 0 && mark[y][x - 1] == 0) {
                queue.offer(new WideBlock(x-1,y, block));
                mark[y][x-1] = 1;
            }
            if (y - 1 >= 0 && map[y - 1][x] == 0 && mark[y - 1][x] == 0) {
                queue.offer(new WideBlock(x, y-1, block));
                mark[y-1][x] = 1;
            }
            if (x + 1 <= maxX && map[y][x + 1] == 0 && mark[y][x + 1] == 0) {
                queue.offer(new WideBlock(x+1, y, block));
                mark[y][x+1] = 1;
            }
            if (y + 1 <= maxY && map[y + 1][x] == 0 && mark[y + 1][x] == 0) {
                queue.offer(new WideBlock(x, y+1, block));
                mark[y+1][x] = 1;
            }
            
        }
    }
}
/**
 * 八皇后回溯
 */
class Eightqueens{
    
    private static int  maxQueue = 8;
    public int num = 0;
    
    private int []arr = new int[maxQueue]; //8个皇后 必定分布不同的行   值代表皇后所在的列
    
    public void place(int n){
        if(n> maxQueue){
            num++;
            return;
        }
        for(int i = 0; i< maxQueue; i++){
            arr[n-1] = i;
            if(check(n)){
                place(n+1);
            }
        }
    }
    public boolean check(int n){
        int x = n-1;
        int y = arr[n-1];
        for(int i =0;i<x;i++){
            if(arr[i]==y)return false; //存在列 相等  行不可能相等
            if( Math.abs(y - arr[i])== Math.abs(x - i))return false;
        }
        return true;
    }

    
}
