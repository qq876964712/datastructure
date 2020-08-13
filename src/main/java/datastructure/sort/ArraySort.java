package datastructure.sort;

import java.util.Arrays;

public class ArraySort {
    
    
    /**
     * 冒泡排序
     *
     * 和相邻的元素比较
     */
    public static void bubbleSort(int[]arr){
        for(int i = arr.length - 1; i>0; i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j+1]^ arr[j];
                    arr[j] = arr[j+1]^arr[j];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    

    
    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 插入排序
     * 认为  往一个有序的 序列中 插入一个数
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > tmp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }
    /**
     *希尔排序
     * 分析:
     * 希尔排序 按照步长为 n/2 n/2/2的步长依次进行插入排序
     * 最后步长为1 进行插入排序
     *
     * 根据分析 不需要按照 开始位置不同进行区分 ,主要按照  index>=step 的元素 都需要 与 index-n*step的元素进行比较并交换
     * 刚开始写的 希尔排序 shellSort4formal
     * 后面看别人写的希尔排序 shellSort
     */
    public static void shellSort(int[] arr) {
        for(int step = arr.length/2 ; step>0;step= step/2){
            for(int i=step; i<arr.length;i++){
                int tmp = arr[i];
                int beforeindex=  i-step;
                for(; beforeindex>=0 && arr[beforeindex]>tmp; beforeindex=beforeindex-step){
                    arr[beforeindex+step] = arr[beforeindex];
                }
                arr[beforeindex+step] = tmp;
            }
        }
    }
    public static void shellSort4formal(int[] arr) {
    
        for (int step = arr.length / 2; step > 0; step = step / 2) {
            for (int startIndex = 0; startIndex < step; startIndex++) {
                for (int k = 1; startIndex + k * step < arr.length ; k++) {
                    int tmp =arr[startIndex + k * step];
                    int m = k-1;
                    for(;m>=0 && arr[startIndex + m * step]>tmp;m--) {
                        arr[startIndex + (m+1) * step] = arr[startIndex + m * step];
                    }
                    arr[startIndex + (m + 1) * step] = tmp;
                
                }
            }
        
        }
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     *快速排序
     */
    public static void fastsort(int arr[]){
        fastSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    
    public static void fastSort(int []arr,int start, int end){
        if(start<end){
            int index = getIndex(arr, start, end);
            fastSort(arr, start, index - 1);
            fastSort(arr, index + 1, end);
        }
    }
    
    public static int getIndex(int[]arr,int left,int right){
        int base = arr[left];
        for(; left< right;){
            for(;arr[right]>= base && left < right;){
                right--;
            }
           arr[left] =arr[right];
            for(;arr[left]<= base && left< right;){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] =base;
        return left;
    }
    
    /**
     *归并排序
     */
    public static void mergesort(int arr[],int left ,int right) {
        if(left<right){
            int mid = (left+right)/2;
            mergesort(arr,left,mid);
            mergesort(arr,mid+1,right);
            
            //此时左部份有序  ,右部分有序 合并两部分
            merge(arr, left, mid, right);
        }
    }
    
    public static void merge(int arr[],int left,int mid ,int right){
        int [] tmp = new int[arr.length];//辅助数组
        //将左右2个有序的区间合并成一个 有序的区间
        int p1 = left;
        int p2 = mid+1;
        int p =left;
        while(p1<=mid && p2<=right){
            if(arr[p1]<=arr[p2]){
                tmp[p++] = arr[p1++];
            }else{
                tmp[p++] = arr[p2++];
            }
        }
        while(p1<=mid){
            tmp[p++]=arr[p1++];
        }
        while (p2 <= right) {
            tmp[p++] = arr[p2++];
        }
        //写回原来的位置
        for(int i = left ;i<=right;i++){
            arr[i] =tmp[i];
        }
    }
    public static void mergesort(int[] arr){
        mergesort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     *基数排序
     * 最容易明白的基数排序
     * 直接使用二维桶来表示
     */
    public static void radixSortSimple(int[]arr){

        int max =arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(max<arr[i])max=arr[i];
        }
    

        
        for(int base = 1; max/base >0; base= base*10){
            int[][] budget = new int[10][arr.length];
            int[] budgetNums = new int[10];
            
            for(int i=0;i<arr.length;i++){
                int index = arr[i]/ base%10;
                budget[index][budgetNums[index]]=arr[i];
                budgetNums[index] +=1;
            }
            int index =0;
            
            for(int m =0;m<budget.length;m++){
                for(int l=0;l<budgetNums[m];l++){
                    if(budget[m][l]!=0){
                        arr[index++] = budget[m][l];
                    }
                }
            }
            
        }
        
    }
    
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }
    
    
        for (int base = 1; max / base > 0; base = base * 10) {
            int []tmp = new int[arr.length];//用来存放临时 数组
            int []budgets =new int [10];
            //按位数上的值排序
            for(int i=0;i<arr.length;i++){
                budgets[arr[i]/base%10]++;
            }
            //确定 arr[i] 按照某一位排序 后  他的下标的区间 在 budgets[arr[i]/base%10] 到budgets[arr[i]/base%10-1]之间
            for(int i=1;i<budgets.length;i++){
                budgets[i] +=budgets[i-1];
            }
            for(int i=tmp.length-1;i>=0;i--){
                tmp[budgets[arr[i] / base % 10] -1 ] =arr[i];
                budgets[arr[i] / base % 10] --;
            }
            for(int i=0;i<arr.length;i++){
                arr[i] =tmp[i];
            }
            
        }
    }
        
    public static void headSort(int []arr){
        if(arr==null)return;
        int len = arr.length;
        buildMaxHeap(arr,len);
        for(int i=len-1;i>0;i--){
            int tmp =arr[0];
            arr[i] = arr[0];
            arr[0]  = tmp;
            buildMaxHeap(arr,--len);
        }
        
    }
    public static void buildMaxHeap(int []arr,int len){
       for(int i=len/2-1;i>=0;i--){ //从最后一个非叶子节点开始 构建堆化
           heapify(arr,i,len);
       }
    }
    
    public static void heapify(int []arr, int i,int len){
        int lchid = 2*i+1;
        int rchid = 2*i+2;
        int max = i;
        if(lchid<len && arr[i] <arr[lchid]){
            max = lchid;
        }
        if (rchid < len && arr[i] < arr[rchid]) {
            max = rchid;
        }
        if(max!=i){
            int tmp = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp;
            heapify(arr,max,len);
        }
        
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        bubbleSort(new int[]{3, 4, 5, 9, 7, 6, 2});
        insertSort(new int[]{3, 4, 5, 9, 7, 6, 2});
        selectSort(new int[]{3, 4, 5, 9, 7, 6, 2});
        shellSort4formal(new int[]{3, 4, 5, 9, 7, 6, 2});
        shellSort(new int[]{3, 4, 5, 9, 7, 6, 2});
        fastsort(new int[]{3, 4, 5, 9, 7, 6, 2});
        mergesort(new int[]{3, 4, 5, 9, 7, 6, 2});
        radixSort(new int[]{3, 4, 5, 9, 7, 6, 2});
        headSort(new int[]{3, 4, 5, 9, 7, 6, 2});
    }
}
