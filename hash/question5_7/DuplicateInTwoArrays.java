package com.algorithm.hash.question5_7;

import java.util.Arrays;
import java.util.HashSet;

/*
给定两个无序的数字数组，检查两个数组中相同的元素
 */
public class DuplicateInTwoArrays {

    /*
    使用排序：
    时间复杂度：O(nlogn),空间复杂度：O(1)
     */
    public static void duplicateInTwoArrays1(int[] A,int[] B){
        if(A==null || B==null){
            throw new IllegalArgumentException("输入数组不能为空");
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int last = 0;
        int i = 0;
        int j = 0;
        while(i<A.length&&j<B.length){
            if(A[i]==B[j]){
                A[last++] = A[i];
                while(i<A.length&&A[i]==B[j]){
                    i++;
                }
            }else if(A[i]<=B[j]){
                i++;
            }else{
                j++;
            }
        }
        if(last<A.length){
            A[last] = '\0';
        }
    }

    /*
    使用哈希表：
    时间复杂度：O(n),空间复杂度：O(n)
     */
    public static void duplicateInTwoArrays2(int[] A,int[] B) {
        if (A == null || B == null) {
            throw new IllegalArgumentException("输入数组不能为空");
        }

        HashSet<Integer> h = new HashSet<>();
        for(int i=0;i<A.length;i++){
            if(!h.contains(A[i])){
                h.add(A[i]);
            }
        }
        int last = 0;
        for(int i=0;i<B.length;i++){
            if(h.contains(B[i])){
                B[last++] = B[i];
                h.remove(B[i]);
            }
        }
        if(last<B.length){
            B[last] = '\0';
        }
    }

    public static void main(String[] args) {
        int[] A = {2,5,6,8,10,2,2,3};
        int[] B = {2,5,5,8,10,5,6};
        //duplicateInTwoArrays1(A,B);
        duplicateInTwoArrays2(B,A);
        for(int i=0;i<A.length&&A[i]!='\0';i++){
            System.out.println(A[i]);
        }
    }
}
