package datastructure.sort;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArraySearch {


    public static  int binarySearch(int []arr,int left ,int right,int  findvalue){
        if(left > right) return -1;
        int mid =(left+right)/2;
        if(arr[mid] == findvalue){
            return mid;
        }else if (arr[mid] > findvalue){
            return binarySearch(arr, left, mid-1, findvalue);
        }else{
            return binarySearch(arr, mid+1, right, findvalue);
        }

    }
    
    public static int binarySearch2(int[] arr, int left, int right, int findvalue) {
        int index = -1;
        while(left <=right){
            int mid = (left+right)/2;
            if(arr[mid]<findvalue){
                left = mid+1;
                continue;
            }else if(arr[mid]>  findvalue){
                right = mid-1;
                continue;
            }else{
                index = mid;
                break;
            }
        }
        return  index;
    }
    public static int []fb= new int[20];
    
    static{
        fb[0]=1;
        fb[1]=1;
        for(int  i=2;i<fb.length;i++){
            fb[i] =fb[i-1] + fb[i-2];
        }
    }
    
    
    //fb(k) -1 = (fb(k-1)-1) + (fb(k-2)-1 )+ 1 中间 mid值
    public static int fabSearch(int []arr,int left ,int right,int findvalue){
        int k=0; //
        int size = right-left+1;
        while(fb[k]-1< size){
            k++;
        }
        
        int[] tmp = Arrays.copyOf(arr,fb[k]);
        for(int i=size ;i<tmp.length;i++ ){
            tmp[i] = tmp[size-1];
        }
        while(left<right){
            int mid = left+ fb[k-1]-1;
            if(tmp[mid] < findvalue){
                left =mid+1;
                k-=2;
            }else if(tmp[mid] > findvalue){
                right= mid-1;
                k--;
            }else{
                if(mid<= arr.length-1){
                    return mid;
                }else{
                    return arr.length - 1;
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,11,22,44,55,68,89,666};
        int i = binarySearch2(arr, 0, arr.length, 11);
        System.out.println(i);
    }
    

}
