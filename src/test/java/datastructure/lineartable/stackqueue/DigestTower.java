package datastructure.lineartable.stackqueue;

public class DigestTower {
    
    public static void line(int startNum ,int maxNum){
        System.out.print(String.format("%3d", startNum));
        if(startNum<maxNum){
            line(startNum+1, maxNum);
            System.out.print(String.format("%3d", startNum));
        }
    }
    
    public static void main(String[] args) {
        int n=9;
        for(int i=1;i<=n;i++){
            System.out.print(String.format("%" + 3 * (n - i + 1) + "c", ' '));// 前导空格
            line(1,i);
            System.out.println();
        }

    }
    
}
