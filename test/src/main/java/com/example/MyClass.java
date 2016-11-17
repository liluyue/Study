package com.example;

import java.util.Random;

public class MyClass {
    public static void main(String[] s){
//        test1();
        final int Max=8;
        int arr[]=new int[Max];
        Random random=new Random(System.currentTimeMillis());
        for (int i=0;i<Max;i++){
            arr[i]=random.nextInt(10);
        }
        print(arr);
//        InsertionSort.straightInsertionSort(arr);
        InsertionSort.shellInsertionSort(arr);
        print(arr);
    }

    private static void print(int[] arr) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println("\n-----------");
    }

    private static void test1() {
        String a=null;
        System.out.println(a instanceof String);
        a="a";
        System.out.println(a instanceof String);
        System.out.println(int.class.isAssignableFrom(int.class));
        System.out.println(new Integer(1).equals(new Integer(1)));
        System.out.println(new Integer(1)==new Integer(1));
    }


}
