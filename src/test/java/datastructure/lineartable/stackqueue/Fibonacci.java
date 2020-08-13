package datastructure.lineartable.stackqueue;

/**
 * 阶乘
 */
public class Fibonacci {
    
    
    public static int fib(int k){
        if(k==1)return 1;
        if(k==2)return 1;
        return fib(k-1)+ fib(k-2);
    }
    
    public static void main(String[] args) {
        System.out.println(fib(3));
    }
}
