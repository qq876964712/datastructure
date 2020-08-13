package datastructure.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;


public class SparseArray {
    
    protected static  final Logger log = LoggerFactory.getLogger(SparseArray.class);

    public static void main(String[] args) {
        int[][] arr = initArray();
        int[][] sparseArr = convertSparseArr(arr);
        SparseArrconvertArr(sparseArr);
    }
    public static    int[][]  SparseArrconvertArr(int[][] sparseArr){
       int sumrow = sparseArr[0][0];
       int sumcol = sparseArr[0][0];
       int [][]arr = new int[sumrow][sumcol];

       for(int i =1 ;i<sparseArr.length;i++){
           arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
       }
        log.info("从稀疏数组中读回数据 :");
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++)
                System.out.print(arr[i][j]+"  ");
            System.out.println();
        }

        return arr;
    }
    public static    int[][]  convertSparseArr( int[][] arr){

        int  num  =10;
        int sumrow =10;
        int sumcol =10;

        int [][]sparseArr = new int[num+1][3];

        sparseArr[0][0] = sumrow;
        sparseArr[0][1] = sumcol;
        sparseArr[0][2] = num;

        int record =0;
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr[i].length;j++)
                if(arr[i][j]!=0 && record<sparseArr.length-1){
                    record++;
                    sparseArr[record][0] =i;
                    sparseArr[record][1] =j;
                    sparseArr[record][2] =arr[i][j];
                }

        log.info("转换为稀疏数组是 :");
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<sparseArr[i].length;j++)
                System.out.print(sparseArr[i][j]+"  ");
            System.out.println();
        }


        return sparseArr;
    }
    public static    int[][]  initArray(){
        int length =10;

        int[][]  arr = new int[length][length];

        LinkedHashSet<Integer> col =new LinkedHashSet<>();
        LinkedHashSet<Integer> row =new LinkedHashSet<>();

        Random random = new Random();


        while(col.size()<length){
            col.add(random.nextInt(length));
        }
        System.out.println(col.toString());
        while(row.size()<length){
            row.add(random.nextInt(length));
        }
        System.out.println(row.toString());
        Iterator<Integer> rowItr = row.iterator();
        Iterator<Integer> colItr = col.iterator();

        while(rowItr.hasNext()  && colItr.hasNext()){
            Integer colNext = colItr.next();
            Integer rowNext = rowItr.next();
            int value = 1+ random.nextInt(2);
            arr[rowNext][colNext] = value;
            log.info("row :  {} col :  {} value :  {}",rowNext,colNext,value);
        }
        log.info("初始数组是 :");
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++)
                System.out.print(arr[i][j]+"  ");
            System.out.println();
        }





        return arr;
    }
}
