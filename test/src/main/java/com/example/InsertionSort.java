package com.example;


import javax.sound.midi.Soundbank;

public class InsertionSort {
    public static void straightInsertionSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i ;
            for (; j >= 1&&temp < arr[j-1]; j--) {
                arr[j]=arr[j-1];
            }
                arr[j ] = temp;
            for (int k=0;k<=i;k++){
                System.out.print(arr[k]+"\t");
            }
            System.out.println("\n===");
        }
    }

    public static void shellInsertionSort(int[] arr) {
           if (arr==null){
               return;
           }
        /*1。得到步长
        * 2.通过步长得到每组的起始元素
        * 3.对每组进行插入排序*/
          for(int step=arr.length/2;step>0;step/=2){
              for (int g=0;g<step;g++){
                  System.out.println(g+"-------   "+step );
                  for (int i=step+g;i<arr.length;i+=step) {
                      int temp=arr[i];
                      System.out.println("temp:"+temp);
                      int j=i;
                      for(;j>=step&&temp<arr[j-step];j-=step){
                          arr[j]=arr[j-step];
                      }
                      arr[j]=temp;
                      for (int k=g;k<arr.length;k+=step){
                          System.out.print(arr[k]+"\t("+k+")    ");
                      }
                      System.out.println();
                  }
              }
          }
    }
}
