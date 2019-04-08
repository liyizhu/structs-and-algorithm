package com.algorithm.hash.question1;

public class HashTableOperations {
    public static void main(String[] args) {
        System.out.println("初始化HashTable：");
        HashTable h = new HashTable(18);
        System.out.println("HashTable元素个数："+h.getCount());
        System.out.println("是否存在元素10："+h.hashSearch(10));
        System.out.println("是否添加元素10成功："+h.hashInsert(10));
        System.out.println("HashTable元素个数："+h.getCount());
        System.out.println("是否存在元素10："+h.hashSearch(10));
        System.out.println("是否删除元素10成功："+h.hashDelete(10));
        System.out.println("HashTable元素个数："+h.getCount());
        System.out.println("是否存在元素10："+h.hashSearch(10));

        System.out.println("===============================");

        for(int i=0;i<45;i++){
            System.out.println("是否添加元素"+ i +"成功："+h.hashInsert(i));
        }

        System.out.println("HashTable元素个数："+h.getCount());

    }
}
