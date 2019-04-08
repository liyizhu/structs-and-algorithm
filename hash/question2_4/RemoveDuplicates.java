package com.algorithm.hash.question2_4;


import java.util.Arrays;
import java.util.HashSet;

/*
给出一个在给定字符数组中删除重复字符的算法
 */
public class RemoveDuplicates {

    /*
    第一种解法
    时间复杂度：O(n²),空间复杂度：O(1)
     */
    public static void removeDuplicates2(char[] s){
        if(s==null){
            throw new IllegalArgumentException("输入的字符数组不能为空");
        }

        int len = s.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;){
                if(s[i]==s[j]){
                    s[j] = s[--len];
                }else{
                    j++;
                }
            }
        }
        if(len<s.length){
            s[len] = '\0';
        }
    }

    /*
    另一种解法：
    时间复杂度：O(n²),空间复杂度：O(1)
     */
    public static void removeDuplicates3(char[] s){
        if(s==null){
            throw new IllegalArgumentException("输入的字符数组不能为空");
        }
        int len = s.length;
        if(len<2){
            return;
        }
        int tail = 1;
        for(int i=1;i<len;i++){
            int j=0;
            for(;j<tail;j++){
                if(s[i]==s[j]){
                    break;
                }
            }
            if(j==tail){
                s[tail] = s[i];
                tail++;
            }
        }
        if(tail<s.length){
            s[tail] = '\0';
        }
    }

    /*
    使用StringBuilder：
    时间复杂度：O(n²),空间复杂度：O(n)
     */
    public static String removeDuplicates4(String s){
        if(s==null){
            throw new IllegalArgumentException("输入的字符数组不能为空");
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            String si = s.substring(i,i+1);
            if(sb.indexOf(si)==-1){
                sb.append(si);
            }
        }
        return sb.toString();
    }

    /*
    使用排序：
    时间复杂度：O(nlogn),空间复杂度：O(1)
     */
    public static String removeDuplicates5(String s){
        if(s==null){
            throw new IllegalArgumentException("输入的字符数组不能为空");
        }

        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        int last = 0;
        for(int i=0;i<chars.length;){
            chars[last++] = chars[i];
            int j = i+1;
            while(j<chars.length&&chars[i]==chars[j]){
                j++;
            }
            i = j;
        }
        chars[last] = '\0';
        String sorted = new String(chars,0,last);
        return sorted;
    }

    /*
    使用哈希表：
    时间复杂度：O(n),空间复杂度：O(n)
     */
    public static void removeDuplicates6(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("输入的字符数组不能为空");
        }
        HashSet<Character> h = new HashSet<>();
        int current = 0;
        int last = 0;
        for(;current<s.length;current++){
            if(!h.contains(s[current])){
                s[last++] = s[current];
                h.add(s[current]);
            }
        }
        if(last<s.length){
            s[last] = '\0';
        }
    }

    public static void main(String[] args) {
        char[] s = {'a','b','c','d','a','b','b'};
        //removeDuplicates2(s);
        //removeDuplicates3(s);
        removeDuplicates6(s);
        for(int i=0;i<s.length&&s[i]!='\0';i++){
            System.out.println(s[i]);
        }

        /*String s = "abcdabb";
        System.out.println(removeDuplicates4(s));
        System.out.println(removeDuplicates5(s));*/
    }
}
